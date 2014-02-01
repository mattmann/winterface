package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.GlobalEventHandlers;
import com.github.snoblind.winterface.WindowEnvironment;
import com.github.snoblind.winterface.WindowEventHandlers;
import com.github.snoblind.winterface.event.EventDispatcher;
import com.github.snoblind.winterface.event.MapEventDispatcher;
import com.github.snoblind.winterface.jsoup.JSoupLocation;
import java.io.IOException;
import java.util.Timer;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import static org.apache.commons.lang.Validate.isTrue;
import static org.apache.commons.lang.Validate.notNull;

public class RhinoWindowEnvironment implements WindowEnvironment {

	protected final Timer timer;
	protected final Console console;
	protected final GlobalEventHandlers globalEventHandlers;
	protected final WindowEventHandlers windowEventHandlers;

	private RhinoWindowEnvironment(Timer timer, Console console, GlobalEventHandlers globalEventHandlers, WindowEventHandlers windowEventHandlers) {
		notNull(this.timer = timer);
		notNull(this.console = console);
		notNull(this.globalEventHandlers = globalEventHandlers);
		notNull(this.windowEventHandlers = windowEventHandlers);
	}

	public RhinoWindow open(String url, String target, String features, boolean replace) throws IOException {
		notNull(url);
		isTrue(target == null && features == null && replace == false);
		final Connection connection = Jsoup.connect(url.toString());
		final JSoupLocation location = new JSoupLocation(connection);
		final EventDispatcher eventDispatcher = new MapEventDispatcher();
		final RhinoDocument document = new RhinoDocument(connection.get(), eventDispatcher);
		final RhinoWindow window  = RhinoWindow.builder()
				.timer(timer).console(console).globalEventHandlers(globalEventHandlers)
				.windowEventHandlers(windowEventHandlers).location(location).document(document).build();
		return window;
	}

	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {

		private Timer timer;
		private Console console;
		private GlobalEventHandlers globalEventHandlers;
		private WindowEventHandlers windowEventHandlers;

		private Builder() {
		}

		public RhinoWindowEnvironment build() {
			return new RhinoWindowEnvironment(timer, console, globalEventHandlers, windowEventHandlers);
		}

		public Builder timer(Timer timer) {
			this.timer = timer;
			return this;
		}

		public Builder console(Console console) {
			this.console = console;
			return this;
		}

		public Builder globalEventHandlers(GlobalEventHandlers globalEventHandlers) {
			this.globalEventHandlers = globalEventHandlers;
			return this;
		}

		public Builder windowEventHandlers(WindowEventHandlers windowEventHandlers) {
			this.windowEventHandlers = windowEventHandlers;
			return this;
		}
	}
}