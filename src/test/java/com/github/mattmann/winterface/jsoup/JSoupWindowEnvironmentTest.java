package com.github.mattmann.winterface.jsoup;

import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.github.mattmann.winterface.GlobalEventHandlers;
import com.github.mattmann.winterface.HTMLDocument;
import com.github.mattmann.winterface.HTMLImageElement;
import com.github.mattmann.winterface.NodeList;
import com.github.mattmann.winterface.Window;
import com.github.mattmann.winterface.WindowEventHandlers;
import com.github.mattmann.winterface.jsoup.JSoupWindowEnvironment;

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
		
		HTMLDocument document = (HTMLDocument)window.getDocument();
		assertNotNull(document);
		
		NodeList images = document.querySelectorAll("img");
		for (int i = 0; i < images.getLength(); i++) {
			HTMLImageElement image = (HTMLImageElement)images.item(i);
			assertNotNull(image.getSrc());
		}
	}
	
	@After
	public void tearDown() {
		environment = null;
	}
}
