package com.github.snoblind.winterface.jsoup;

import java.io.IOException;
import java.net.URL;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.junit.Before;
import org.mockito.Mock;
import com.github.snoblind.winterface.ExtendedHTMLFormElement;
import com.github.snoblind.winterface.GlobalEventHandlers;
import com.github.snoblind.winterface.HTMLFormElementTest;
import com.github.snoblind.winterface.WindowEventHandlers;
import com.github.snoblind.winterface.event.MapEventDispatcher;
import com.github.snoblind.winterface.jsoup.JSoupDocument;
import com.github.snoblind.winterface.jsoup.JSoupLocation;
import com.github.snoblind.winterface.jsoup.JSoupWindow;
import static org.junit.Assert.assertNotNull;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.jsoup.Connection.Request;
import static org.jsoup.Connection.Response;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.any;

public class JSoupFormElementTest extends HTMLFormElementTest {

	@Mock private GlobalEventHandlers globalEventHandlers;
	@Mock private WindowEventHandlers windowEventHandlers;
	@Mock private Connection connection;
	@Mock private Request request;
	@Mock private Response response;
	
	@Before
	public void setUp() throws Exception {
		initMocks(this);
		super.setUp();
	}

	@SuppressWarnings("unused")
	protected ExtendedHTMLFormElement createHTMLFormElement() throws IOException {
		URL url = new URL("http://www.google.com/");
		Document jsoupDocument = Parser.parse("<html><body><form/></body></html>", url.toExternalForm());
		doReturn(request).when(connection).request();
		doReturn(response).when(connection).response();
		doReturn(url).when(request).url();
		doReturn(request).when(request).url(any(URL.class));
		doReturn(request).when(request).method(any(Method.class));
		doReturn(response).when(connection).execute();
		doReturn(jsoupDocument).when(response).parse();
		JSoupLocation location = new JSoupLocation(connection);
		JSoupDocument document = new JSoupDocument(jsoupDocument, new MapEventDispatcher());
		JSoupWindow window = new JSoupWindow(globalEventHandlers, windowEventHandlers, location, document);
		ExtendedHTMLFormElement form = (ExtendedHTMLFormElement)document.querySelector("form");
		assertNotNull(form);
		return form;
	}
}
