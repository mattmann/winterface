package com.github.mattmann.winterface.jsoup;

import java.io.IOException;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class JSoupScriptElementTest {
	
	private static final String BASE_URI = "about:blank";
	private static final String SRC = "script.js";
	private static final String TEXT = "window.alert('Hello World!');";
	private static final Tag SCRIPT = Tag.valueOf("script");

	private JSoupScriptElement scriptElement;
	
	@Mock private JSoupDocument ownerDocument;

	@Before
	public void setUp() throws IOException {
		initMocks(this);
		scriptElement = new JSoupScriptElement(new Element(SCRIPT, BASE_URI, new Attributes()), ownerDocument);
	}

	@Test
	public void test() {
		scriptElement.setAsync(true);
		assertEquals(scriptElement.isAsync(), true);

		scriptElement.setCharset("UTF-8");
		assertEquals(scriptElement.getCharset(), "UTF-8");

		scriptElement.setDefer(true);
		assertEquals(scriptElement.isDefer(), true);

		scriptElement.setSrc(SRC);
		assertEquals(scriptElement.getSrc(), SRC);

		scriptElement.setType("text/javascript");
		assertEquals(scriptElement.getType(), "text/javascript");
		
		scriptElement.setText(TEXT);
		assertEquals(scriptElement.getText(), TEXT);
	}
}
