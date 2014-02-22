package com.github.snoblind.winterface.demo;

import com.github.snoblind.winterface.Event;
import com.github.snoblind.winterface.GlobalEventHandlers;
import com.github.snoblind.winterface.Navigator;
import com.github.snoblind.winterface.WindowEventHandlers;
import com.github.snoblind.winterface.XMLHttpRequest;
import com.github.snoblind.winterface.event.EventDispatcher;
import com.github.snoblind.winterface.event.EventImpl;
import com.github.snoblind.winterface.jsoup.JSoupHTMLParser;
import com.github.snoblind.winterface.rhino.Console;
import com.github.snoblind.winterface.rhino.PrintStreamConsole;
import com.github.snoblind.winterface.rhino.RhinoNavigator;
import com.github.snoblind.winterface.rhino.RhinoWindow;
import com.github.snoblind.winterface.rhino.RhinoWindowEnvironment;
import com.github.snoblind.winterface.spi.HTMLParser;
import com.github.snoblind.winterface.spi.QuerySelector;
import com.github.snoblind.winterface.xmlhttp.ApacheCommonsXMLHttpRequest;
import java.io.IOException;
import java.util.Timer;
import org.apache.commons.collections4.Factory;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mozilla.javascript.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	private static Logger LOGGER = LoggerFactory.getLogger(Main.class);

	private static final String INITIAL_URL = "http://www.nytimes.com/";

	private static final Answer<Object> ANSWER = new Answer<Object>() {
		public Object answer(InvocationOnMock invocation) throws Throwable {
			final java.lang.reflect.Method method = invocation.getMethod();
			final Object[] arguments = invocation.getArguments();
			if (arguments.length == 0) {
				if (method.getName().equals("finalize")) {
					return null;
				}
				if (method.getName().equals("toString")) {
					return invocation.getMock().getClass().getName();
				}
			}
			System.err.println(method);
			System.err.println(java.util.Arrays.toString(arguments));
			throw new UnsupportedOperationException(method.toString());
		}
	};
	
	public static void main(String[] args) throws IOException {
		final Timer timer = new Timer();
		final HttpClient httpClient = new DefaultHttpClient();
		final GlobalEventHandlers globalEventHandlers = Mockito.mock(GlobalEventHandlers.class, ANSWER);
		final WindowEventHandlers windowEventHandlers = Mockito.mock(WindowEventHandlers.class, ANSWER);
		final EventDispatcher eventDispatcher = Mockito.mock(EventDispatcher.class, ANSWER);
		final QuerySelector querySelector = Mockito.mock(QuerySelector.class, ANSWER);
		final HttpClient client = new DefaultHttpClient();
		final Factory<Event> eventFactory = new Factory<Event>() {
			public Event create() {
				return new EventImpl();
			}
		};
		final Factory<HTMLParser> parserFactory = new Factory<HTMLParser>() {
			public HTMLParser create() {
				return new JSoupHTMLParser(eventDispatcher);
			}
		};
		final Factory<XMLHttpRequest> xmlHttpRequestFactory = new Factory<XMLHttpRequest>() {
			public XMLHttpRequest create() {
				return ApacheCommonsXMLHttpRequest.builder()
						.client(client)
						.eventFactory(eventFactory)
						.parserFactory(parserFactory)
						.build();
			}
		};
		final Navigator navigator = new RhinoNavigator();
		final Console console = new PrintStreamConsole();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				exitContext();
				shutdown(httpClient);
				cancel(timer);
			}
		});
		try {
			RhinoWindowEnvironment environment = RhinoWindowEnvironment.builder()
					.console(console)
					.globalEventHandlers(globalEventHandlers)
					.parserFactory(parserFactory)
					.querySelector(querySelector)
					.timer(timer)
					.windowEventHandlers(windowEventHandlers)
					.xmlHttpRequestFactory(xmlHttpRequestFactory)
					.build();
			RhinoWindow window = environment.open(INITIAL_URL, null, null, false);
			try {
				Demo.builder().console(console).httpClient(httpClient).navigator(navigator).window(window).build().call();
			}
			finally {
				exitContext();
			}
		}
		catch (Throwable x) {
			x.printStackTrace();
		}
		finally {
			shutdown(httpClient);
			cancel(timer);
		}
	}

	private static void exitContext() {
		if (Context.getCurrentContext() == null) {
			LOGGER.debug("No current Rhino context. Doing nothing.");
			return;
		}
		try {
			LOGGER.debug("Exiting Rhino context.");
			Context.exit();
			LOGGER.debug("Exited Rhino context.");
		}
		catch (Exception x) {
			x.printStackTrace();
		}
	}

	private static void shutdown(HttpClient httpClient) {
		try {
			LOGGER.debug("Shutting down HTTP connection manager.");
			httpClient.getConnectionManager().shutdown();
			LOGGER.debug("Shutdown of HTTP connection manager completed without incident.");
		}
		catch (Exception x) {
			x.printStackTrace();
		}
	}

	private static void cancel(Timer timer) {
		try {
			LOGGER.debug("Cancelling timer.");
			timer.cancel();
			LOGGER.debug("Cancelled timer.");
		}
		catch (Exception x) {
			x.printStackTrace();
		}
	}
}