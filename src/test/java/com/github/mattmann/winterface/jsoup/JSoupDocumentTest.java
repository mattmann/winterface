package com.github.mattmann.winterface.jsoup;

import java.io.IOException;
import org.jsoup.parser.Parser;
import org.junit.Before;
import org.mockito.Mock;
import com.github.mattmann.winterface.HTMLDocument;
import com.github.mattmann.winterface.HTMLDocumentTest;
import com.github.mattmann.winterface.Location;
import com.github.mattmann.winterface.Window;
import com.github.mattmann.winterface.jsoup.JSoupDocument;
import com.github.mattmann.winterface.jsoup.JSoupEventDispatcher;
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

	protected HTMLDocument parseDocument(String html, String baseURI) throws IOException {
		JSoupDocument document = new JSoupDocument(Parser.htmlParser().parseInput(html, baseURI), new JSoupEventDispatcher());
		document.setDefaultView(window);
		return document;
	}
}