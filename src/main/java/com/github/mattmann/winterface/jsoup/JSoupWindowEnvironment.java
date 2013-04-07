package com.github.mattmann.winterface.jsoup;

import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import com.github.mattmann.winterface.GlobalEventHandlers;
import com.github.mattmann.winterface.Window;
import com.github.mattmann.winterface.WindowEnvironment;
import com.github.mattmann.winterface.WindowEventHandlers;
import static org.apache.commons.lang.Validate.isTrue;
import static org.apache.commons.lang.Validate.notNull;

public class JSoupWindowEnvironment implements WindowEnvironment {
	
	protected final GlobalEventHandlers globalEventHandlers;
	protected final WindowEventHandlers windowEventHandlers;

	public JSoupWindowEnvironment(GlobalEventHandlers globalEventHandlers, WindowEventHandlers windowEventHandlers) {
		notNull(this.globalEventHandlers = globalEventHandlers);
		notNull(this.windowEventHandlers = windowEventHandlers);
	}

	public Window open(CharSequence url, CharSequence target, CharSequence features, boolean replace) throws IOException {
		notNull(url);
		isTrue(target == null && features == null && replace == false);
		Connection connection = Jsoup.connect(url.toString());
		JSoupLocation location = new JSoupLocation(connection);
		JSoupDocument document = new JSoupDocument(connection.get(), new JSoupEventDispatcher());
		JSoupWindow window  = new JSoupWindow(globalEventHandlers, windowEventHandlers, location, document);
		document.setDefaultView(window);
		return window;
	}
}
