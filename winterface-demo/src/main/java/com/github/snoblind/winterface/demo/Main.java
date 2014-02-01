package com.github.snoblind.winterface.demo;

import com.github.snoblind.winterface.GlobalEventHandlers;
import com.github.snoblind.winterface.Navigator;
import com.github.snoblind.winterface.WindowEventHandlers;
import com.github.snoblind.winterface.rhino.Console;
import com.github.snoblind.winterface.rhino.PrintStreamConsole;
import com.github.snoblind.winterface.rhino.RhinoNavigator;
import com.github.snoblind.winterface.rhino.RhinoWindow;
import com.github.snoblind.winterface.rhino.RhinoWindowEnvironment;
import java.io.IOException;
import java.util.Timer;
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
	
	private static final Answer<Object> ANSWER_UNSUPPORTED_OPERATION = new Answer<Object>() {
		public Object answer(InvocationOnMock invocation) throws Throwable {
			throw new UnsupportedOperationException(invocation.getMethod().toString());
		}
	};
	
	public static void main(String[] args) throws IOException {
		final Timer timer = new Timer();
		final HttpClient httpClient = new DefaultHttpClient();
		final GlobalEventHandlers globalEventHandlers = Mockito.mock(GlobalEventHandlers.class, ANSWER_UNSUPPORTED_OPERATION);
		final WindowEventHandlers windowEventHandlers = Mockito.mock(WindowEventHandlers.class, ANSWER_UNSUPPORTED_OPERATION);
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
					.timer(timer).console(console).globalEventHandlers(globalEventHandlers).windowEventHandlers(windowEventHandlers).build();
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