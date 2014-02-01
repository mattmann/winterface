package com.github.snoblind.winterface.jsoup;

import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import com.github.snoblind.winterface.GlobalEventHandlers;
import com.github.snoblind.winterface.Window;
import com.github.snoblind.winterface.WindowEnvironment;
import com.github.snoblind.winterface.WindowEventHandlers;
import com.github.snoblind.winterface.event.MapEventDispatcher;
import static org.apache.commons.lang.Validate.isTrue;
import static org.apache.commons.lang.Validate.notNull;

public class JSoupWindowEnvironment implements WindowEnvironment {
	
	protected final GlobalEventHandlers globalEventHandlers;
	protected final WindowEventHandlers windowEventHandlers;

	public JSoupWindowEnvironment(GlobalEventHandlers globalEventHandlers, WindowEventHandlers windowEventHandlers) {
		notNull(this.globalEventHandlers = globalEventHandlers);
		notNull(this.windowEventHandlers = windowEventHandlers);
	}

	public Window open(String url, String target, String features, boolean replace) throws IOException {
		notNull(url);
		isTrue(target == null && features == null && replace == false);
		Connection connection = Jsoup.connect(url.toString());
		JSoupLocation location = new JSoupLocation(connection);
		JSoupDocument document = new JSoupDocument(connection.get(), new MapEventDispatcher());
		JSoupWindow window  = new JSoupWindow(globalEventHandlers, windowEventHandlers, location, document);
		document.setDefaultView(window);
		return window;
	}
}
