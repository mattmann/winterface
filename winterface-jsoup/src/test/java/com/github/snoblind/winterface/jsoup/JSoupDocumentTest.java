package com.github.snoblind.winterface.jsoup;

import java.io.IOException;
import org.jsoup.parser.Parser;
import org.junit.Before;
import org.mockito.Mock;
import com.github.snoblind.winterface.ExtendedHTMLDocument;
import com.github.snoblind.winterface.HTMLDocumentTest;
import com.github.snoblind.winterface.Location;
import com.github.snoblind.winterface.Window;
import com.github.snoblind.winterface.event.MapEventDispatcher;
import com.github.snoblind.winterface.jsoup.JSoupDocument;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

public class JSoupDocumentTest extends HTMLDocumentTest {

	@Mock private Window window;
	@Mock private Location location;

	@Before
	public void setUp() throws IOException {
		initMocks(this);
		doReturn(location).when(window).getLocation();
		super.setUp();
	}

	protected ExtendedHTMLDocument parseDocument(String html, String baseURI) throws IOException {
		JSoupDocument document = new JSoupDocument(Parser.htmlParser().parseInput(html, baseURI), new MapEventDispatcher());
		document.setDefaultView(window);
		return document;
	}
}