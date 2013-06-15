package com.github.mattmann.winterface.jsoup;

import com.github.mattmann.winterface.GlobalEventHandlers;
import com.github.mattmann.winterface.ExtendedHTMLDocument;
import com.github.mattmann.winterface.ExtendedHTMLImageElement;
import com.github.mattmann.winterface.Window;
import com.github.mattmann.winterface.WindowEventHandlers;
import com.github.mattmann.winterface.jsoup.JSoupWindowEnvironment;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.w3c.dom.NodeList;
import static org.junit.Assert.assertNotNull;
import static org.mockito.MockitoAnnotations.initMocks;

public class JSoupWindowEnvironmentTest {

	private JSoupWindowEnvironment environment;
	
	@Mock private GlobalEventHandlers globalEventHandlers;
	@Mock private WindowEventHandlers windowEventHandlers;

	@Before
	public void setUp() {
		initMocks(this);
		environment = new JSoupWindowEnvironment(globalEventHandlers, windowEventHandlers);
	}
	
	@Test
	public void testOpen() throws IOException {
		Window window = environment.open("https://www.google.com/", null, null, false);
		assertNotNull(window);
		
		ExtendedHTMLDocument document = (ExtendedHTMLDocument)window.getDocument();
		assertNotNull(document);
		
		NodeList images = document.querySelectorAll("img");
		for (int i = 0; i < images.getLength(); i++) {
			ExtendedHTMLImageElement image = (ExtendedHTMLImageElement)images.item(i);
			assertNotNull(image.getSrc());
		}
	}
	
	@After
	public void tearDown() {
		environment = null;
	}
}
