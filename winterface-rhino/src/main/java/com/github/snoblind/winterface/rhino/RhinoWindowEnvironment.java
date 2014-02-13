package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.event.MapEventDispatcher;
import com.github.snoblind.winterface.spi.HTMLParser;
import com.github.snoblind.winterface.spi.QuerySelector;
import com.github.snoblind.winterface.GlobalEventHandlers;
import com.github.snoblind.winterface.Location;
import com.github.snoblind.winterface.NodeAdapterFactory;
import com.github.snoblind.winterface.WindowEnvironment;
import com.github.snoblind.winterface.WindowEventHandlers;
import com.github.snoblind.winterface.XMLHttpRequest;
import java.io.IOException;
import java.util.Timer;
import org.apache.commons.collections4.Factory;
import org.springframework.beans.factory.annotation.Required;
import org.w3c.dom.Node;
import static com.github.snoblind.winterface.required.RequiredProperties.assertRequiredProperties;
import static org.apache.commons.lang.Validate.isTrue;
import static org.apache.commons.lang.Validate.notNull;

public class RhinoWindowEnvironment implements Cloneable, WindowEnvironment {

	private Console console;
	private Factory<HTMLParser> parserFactory;
	private Factory<XMLHttpRequest> xmlHttpRequestFactory;
	private GlobalEventHandlers globalEventHandlers;
	private NodeAdapterFactory<Node> nodeAdapterFactory;
	private QuerySelector querySelector;
	private Timer timer;
	private WindowEventHandlers windowEventHandlers;

	private RhinoWindowEnvironment() {
	}

	@Required
	public QuerySelector getQuerySelector() {
		return querySelector;
	}

	@Required
	public NodeAdapterFactory<Node> getNodeAdapterFactory() {
		return nodeAdapterFactory;
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

	public RhinoWindow open(String url, String target, String features, boolean replace) throws IOException {
		notNull(url);
		isTrue(target == null && features == null && replace == false);
		final RhinoWindow window  = RhinoWindow.builder()
				.console(console)
				.eventDispatcher(new MapEventDispatcher())
				.globalEventHandlers(globalEventHandlers)
				.nodeAdapterFactory(nodeAdapterFactory)
				.parserFactory(parserFactory)
				.querySelector(querySelector)
				.timer(timer)
				.windowEventHandlers(windowEventHandlers)
				.xmlHttpRequestFactory(xmlHttpRequestFactory)
				.build();
		final Location location = new RhinoLocation(window);
		location.setHref(url);
		window.setLocation(location);
		return window;
	}

	public RhinoWindowEnvironment clone() {
		try {
			return (RhinoWindowEnvironment) super.clone();
		}
		catch (CloneNotSupportedException x) {
			throw new RuntimeException(x);
		}
	}

	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {

		private final RhinoWindowEnvironment environment = new RhinoWindowEnvironment();

		private Builder() {
		}

		public RhinoWindowEnvironment build() {
			assertRequiredProperties(environment);
			return environment.clone();
		}

		public Builder querySelector(QuerySelector querySelector) {
			environment.querySelector = querySelector;
			return this;
		}

		public Builder parserFactory(Factory<HTMLParser> parserFactory) {
			environment.parserFactory = parserFactory;
			return this;
		}
		
		public Builder timer(Timer timer) {
			environment.timer = timer;
			return this;
		}

		public Builder console(Console console) {
			environment.console = console;
			return this;
		}

		public Builder globalEventHandlers(GlobalEventHandlers globalEventHandlers) {
			environment.globalEventHandlers = globalEventHandlers;
			return this;
		}

		public Builder windowEventHandlers(WindowEventHandlers windowEventHandlers) {
			environment.windowEventHandlers = windowEventHandlers;
			return this;
		}

		public Builder xmlHttpRequestFactory(Factory<XMLHttpRequest> xmlHttpRequestFactory) {
			environment.xmlHttpRequestFactory = xmlHttpRequestFactory;
			return this;
		}

		public Builder nodeAdapterFactory(NodeAdapterFactory<Node> nodeAdapterFactory) {
			environment.nodeAdapterFactory = nodeAdapterFactory;
			return this;
		}
	}
}