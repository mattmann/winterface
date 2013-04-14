package com.github.mattmann.winterface.rhino;

import com.github.mattmann.winterface.GlobalEventHandlers;
import com.github.mattmann.winterface.WindowEnvironment;
import com.github.mattmann.winterface.WindowEventHandlers;
import com.github.mattmann.winterface.jsoup.JSoupDocument;
import com.github.mattmann.winterface.jsoup.JSoupEventDispatcher;
import com.github.mattmann.winterface.jsoup.JSoupLocation;
import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import static org.apache.commons.lang.Validate.isTrue;
import static org.apache.commons.lang.Validate.notNull;

public class RhinoWindowEnvironment implements WindowEnvironment {

	protected final Console console;
	protected final GlobalEventHandlers globalEventHandlers;
	protected final WindowEventHandlers windowEventHandlers;

	public RhinoWindowEnvironment(Console console, GlobalEventHandlers globalEventHandlers, WindowEventHandlers windowEventHandlers) {
		notNull(this.console = console);
		notNull(this.globalEventHandlers = globalEventHandlers);
		notNull(this.windowEventHandlers = windowEventHandlers);
	}

	public RhinoWindow open(CharSequence url, CharSequence target, CharSequence features, boolean replace) throws IOException {
		notNull(url);
		isTrue(target == null && features == null && replace == false);
		Connection connection = Jsoup.connect(url.toString());
		JSoupLocation location = new JSoupLocation(connection);
		JSoupDocument document = new JSoupDocument(connection.get(), new JSoupEventDispatcher());
		RhinoWindow window  = new RhinoWindow(console, globalEventHandlers, windowEventHandlers, location, document);
		document.setDefaultView(window);
		return window;
	}
}