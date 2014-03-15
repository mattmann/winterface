package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.event.EventDispatcher;
import com.github.snoblind.winterface.spi.HTMLParser;
import com.github.snoblind.winterface.spi.QuerySelector;
import com.github.snoblind.winterface.GlobalEventHandlers;
import com.github.snoblind.winterface.Navigator;
import com.github.snoblind.winterface.WindowEnvironment;
import com.github.snoblind.winterface.WindowEventHandlers;
import com.github.snoblind.winterface.XMLHttpRequest;
import java.io.IOException;
import java.net.CookieStore;
import java.util.Timer;
import org.apache.commons.collections4.Factory;
import org.springframework.beans.factory.annotation.Required;
import static com.github.snoblind.winterface.required.RequiredProperties.assertRequiredProperties;
import static org.apache.commons.lang.Validate.isTrue;
import static org.apache.commons.lang.Validate.notNull;

public class RhinoWindowEnvironment implements Cloneable, WindowEnvironment {

	private Console console;
	private CookieStore cookieStore;
	private Factory<HTMLParser> parserFactory;
	private Factory<XMLHttpRequest> xmlHttpRequestFactory;
	private GlobalEventHandlers globalEventHandlers;
	private QuerySelector querySelector;
	private Timer timer;
	private WindowEventHandlers windowEventHandlers;
	private EventDispatcher eventDispatcher;
	private Navigator navigator;

	private RhinoWindowEnvironment() {
	}

	@Required
	public CookieStore getCookieStore() {
		return cookieStore;
	}

	@Required
	public QuerySelector getQuerySelector() {
		return querySelector;
	}

	@Required
	public Console getConsole() {
		return console;
	}

	@Required
	public Factory<HTMLParser> getParserFactory() {
		return parserFactory;
	}

	@Required
	public Factory<XMLHttpRequest> getXmlHttpRequestFactory() {
		return xmlHttpRequestFactory;
	}

	@Required
	public GlobalEventHandlers getGlobalEventHandlers() {
		return globalEventHandlers;
	}

	@Required
	public Timer getTimer() {
		return timer;
	}

	@Required
	public WindowEventHandlers getWindowEventHandlers() {
		return windowEventHandlers;
	}

	@Required
	public EventDispatcher getEventDispatcher() {
		return eventDispatcher;
	}

	@Required
	public Navigator getNavigator() {
		return navigator;
	}

	public RhinoWindow open(String url, String target, String features, boolean replace) throws IOException {
		notNull(url);
		isTrue(target == null && features == null && replace == false);
		final RhinoWindow window  = RhinoWindow.builder()
				.console(console)
				.cookieStore(cookieStore)
				.eventDispatcher(eventDispatcher)
				.globalEventHandlers(globalEventHandlers)
				.navigator(navigator)
				.parserFactory(parserFactory)
				.querySelector(querySelector)
				.timer(timer)
				.windowEventHandlers(windowEventHandlers)
				.xmlHttpRequestFactory(xmlHttpRequestFactory)
				.build();
		final RhinoLocation location = new RhinoLocation(window);
		location.setHref(url);
		window.setLocation(location);
		return window;
	}

	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {

		private RhinoWindowEnvironment environment;

		private Builder() {
		}

		public RhinoWindowEnvironment build() {
			assertRequiredProperties(environment);
			final RhinoWindowEnvironment environment = this.environment;
			this.environment = null;
			return environment;
		}

		private RhinoWindowEnvironment getEnvironment() {
			if (environment == null) {
				environment = new RhinoWindowEnvironment();
			}
			return environment;
		}

		public Builder navigator(Navigator navigator) {
			getEnvironment().navigator = navigator;
			return this;
		}
		
		public Builder querySelector(QuerySelector querySelector) {
			getEnvironment().querySelector = querySelector;
			return this;
		}

		public Builder parserFactory(Factory<HTMLParser> parserFactory) {
			getEnvironment().parserFactory = parserFactory;
			return this;
		}
		
		public Builder timer(Timer timer) {
			getEnvironment().timer = timer;
			return this;
		}

		public Builder console(Console console) {
			getEnvironment().console = console;
			return this;
		}

		public Builder globalEventHandlers(GlobalEventHandlers globalEventHandlers) {
			getEnvironment().globalEventHandlers = globalEventHandlers;
			return this;
		}

		public Builder windowEventHandlers(WindowEventHandlers windowEventHandlers) {
			getEnvironment().windowEventHandlers = windowEventHandlers;
			return this;
		}

		public Builder xmlHttpRequestFactory(Factory<XMLHttpRequest> xmlHttpRequestFactory) {
			getEnvironment().xmlHttpRequestFactory = xmlHttpRequestFactory;
			return this;
		}

		public Builder eventDispatcher(EventDispatcher eventDispatcher) {
			getEnvironment().eventDispatcher = eventDispatcher;
			return this;
		}

		public Builder cookieStore(CookieStore cookieStore) {
			getEnvironment().cookieStore = cookieStore;
			return this;
		}
	}
}