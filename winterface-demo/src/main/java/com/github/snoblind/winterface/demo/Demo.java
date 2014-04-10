package com.github.snoblind.winterface.demo;

import com.github.snoblind.winterface.Event;
import com.github.snoblind.winterface.XMLHttpRequest;
import com.github.snoblind.winterface.event.DefaultEvent;
import com.github.snoblind.winterface.Navigator;
import com.github.snoblind.winterface.rhino.Console;
import com.github.snoblind.winterface.rhino.RhinoWindow;
import com.github.snoblind.winterface.rhino.XMLHttpRequestConstructor;
import com.github.snoblind.winterface.spi.HTMLParser;
import com.github.snoblind.winterface.xmlhttp.ApacheCommonsXMLHttpRequest;
import java.io.IOException;
import java.util.concurrent.Callable;
import jline.console.ConsoleReader;
import org.apache.commons.collections4.Factory;
import org.apache.http.client.HttpClient;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.EcmaError;
import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.WrappedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import static com.github.snoblind.winterface.required.RequiredProperties.assertRequiredProperties;
import static org.apache.commons.collections4.functors.InstantiateFactory.instantiateFactory;
import static org.mozilla.javascript.ScriptableObject.DONTENUM;
import static org.mozilla.javascript.ScriptableObject.defineProperty;
import static org.mozilla.javascript.ScriptableObject.putProperty;

public class Demo implements Callable<Void> {

	private static Logger LOGGER = LoggerFactory.getLogger(Demo.class);

	private static final String EXIT_FUNCTION = "function exit(returnCode) { java.lang.System.exit(returnCode == null ? 0 : returnCode); }";

	private Console console;
	private HttpClient httpClient;
	private Navigator navigator;
	private RhinoWindow window;
	private Factory<? extends HTMLParser> parserFactory;

	private Demo() {}

	public Void call() throws IOException {
        final Factory<? extends Event> eventFactory = instantiateFactory(DefaultEvent.class, null, null);
        final Factory<? extends XMLHttpRequest> xmlHttpRequestFactory = new Factory<XMLHttpRequest>() {
			public XMLHttpRequest create() {
				return ApacheCommonsXMLHttpRequest.builder()
						.client(httpClient)
						.eventFactory(eventFactory)
						.parserFactory(parserFactory)
						.build();
			}
        };
		final XMLHttpRequestConstructor requestConstructor = new XMLHttpRequestConstructor(xmlHttpRequestFactory);
        final ConsoleReader jline = new ConsoleReader();
        final Context context = Context.enter();
		LOGGER.debug("Rhino Context entered.");
		context.initStandardObjects(window);
		LOGGER.debug("Standard objects initialized.");
		putProperty(window, "console", console);
		putProperty(window, "err", System.err);
		putProperty(window, "navigator", navigator);
		putProperty(window, "out", System.out);
		putProperty(window, "window", window);
		defineProperty(window, "XMLHttpRequest", requestConstructor, DONTENUM);
		LOGGER.debug("Defined console, err, navigator, out, window, and XMLHttpRequest.");
		context.evaluateString(window, EXIT_FUNCTION, null, 0, null);
		LOGGER.debug("Defined exit() function.");
		StringBuilder builder = new StringBuilder();
		String source;
		while ((source = jline.readLine()) != null) {
			LOGGER.debug("Read line: \"{}\".", source);
			if (source.endsWith(".")) {
				builder.append(source.substring(0, source.length() - 1));
				source = builder.toString();
				builder.setLength(0);
				try {
					Object returnValue = context.evaluateString(window, source, null, 0, null);
					if (returnValue == null) {
					}
					else if (returnValue instanceof Undefined) {
					}
					else if (returnValue instanceof NativeJavaObject) {
						jline.println(Context.jsToJava(returnValue, String.class).toString());
						jline.flush();
					}
					else {
						jline.println(returnValue.toString());
						jline.flush();
					}
				}
				catch (EcmaError x) {
					System.err.println(x.getMessage());
					x.printStackTrace();
				}
				catch (WrappedException x) {
					System.err.println(x.getWrappedException());
					x.printStackTrace();
				}
				catch (EvaluatorException x) {
					System.err.println(x.getMessage());
					x.printStackTrace();
				}
			}
			else {
				builder.append(source);
			}
		}
		return null;
	}

	@Required
	public Console getConsole() {
		return console;
	}

	@Required
	public HttpClient getHttpClient() {
		return httpClient;
	}

	@Required
	public Navigator getNavigator() {
		return navigator;
	}

	@Required
	public RhinoWindow getWindow() {
		return window;
	}

	@Required
	public Factory<? extends HTMLParser> getParserFactory() {
		return parserFactory;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private Demo demo;
		
		private Builder() {}

		public Demo build() {
			assertRequiredProperties(demo);
			final Demo demo = this.demo;
			this.demo = null;
			return demo;
		}

		public Builder console(Console console) {
			if (demo == null) {
				demo = new Demo();
			}
			demo.console = console;
			return this;
		}

		public Builder httpClient(HttpClient httpClient) {
			if (demo == null) {
				demo = new Demo();
			}
			demo.httpClient = httpClient;
			return this;
		}

		public Builder navigator(Navigator navigator) {
			if (demo == null) {
				demo = new Demo();
			}
			demo.navigator = navigator;
			return this;
		}

		public Builder window(RhinoWindow window) {
			if (demo == null) {
				demo = new Demo();
			}
			demo.window = window;
			return this;
		}

		public Builder htmlParserFactory(Factory<HTMLParser> parserFactory) {
			if (demo == null) {
				demo = new Demo();
			}
			demo.parserFactory = parserFactory;
			return this;
		}
	}
}