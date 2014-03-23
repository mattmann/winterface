package com.github.snoblind.winterface.demo;

import com.github.snoblind.winterface.Event;
import com.github.snoblind.winterface.GlobalEventHandlers;
import com.github.snoblind.winterface.Navigator;
import com.github.snoblind.winterface.WindowEventHandlers;
import com.github.snoblind.winterface.XMLHttpRequest;
import com.github.snoblind.winterface.event.EventDispatcher;
import com.github.snoblind.winterface.event.EventImpl;
import com.github.snoblind.winterface.event.MapEventDispatcher;
import com.github.snoblind.winterface.jodd.JoddQuerySelector;
import com.github.snoblind.winterface.jsoup.JSoupHTMLParser;
import com.github.snoblind.winterface.rhino.Console;
import com.github.snoblind.winterface.rhino.PrintStreamConsole;
import com.github.snoblind.winterface.rhino.RhinoNavigator;
import com.github.snoblind.winterface.rhino.RhinoWindow;
import com.github.snoblind.winterface.rhino.RhinoWindowEnvironment;
import com.github.snoblind.winterface.spi.HTMLParser;
import com.github.snoblind.winterface.spi.QuerySelector;
import com.github.snoblind.winterface.xmlhttp.ApacheCommonsXMLHttpRequest;
import com.github.snoblind.winterface.xmlhttp.CookieStoreAdapter;
import java.util.Timer;
import org.apache.commons.collections4.Factory;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.mockito.Mockito;
import org.mozilla.javascript.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	private static Logger LOGGER = LoggerFactory.getLogger(Main.class);

	private static final String INITIAL_URL = "about:blank";

	public static void main(String[] args) {
		final Timer timer = new Timer();
		final CookieStore cookieStore = new BasicCookieStore();
		final HttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		final GlobalEventHandlers globalEventHandlers = Mockito.mock(GlobalEventHandlers.class);
		final WindowEventHandlers windowEventHandlers = Mockito.mock(WindowEventHandlers.class);
		final QuerySelector querySelector = new JoddQuerySelector();
		final EventDispatcher eventDispatcher = new MapEventDispatcher();
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
						.client(httpClient)
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
					.cookieStore(new CookieStoreAdapter(cookieStore))
					.eventDispatcher(eventDispatcher)
					.globalEventHandlers(globalEventHandlers)
					.navigator(navigator)
					.parserFactory(parserFactory)
					.querySelector(querySelector)
					.timer(timer)
					.windowEventHandlers(windowEventHandlers)
					.xmlHttpRequestFactory(xmlHttpRequestFactory)
					.build();
			RhinoWindow window = environment.open(INITIAL_URL, null, null, false);
			try {
				Demo.builder().console(console).htmlParserFactory(parserFactory).httpClient(httpClient).navigator(navigator).window(window).build().call();
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

	@SuppressWarnings("deprecation")
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