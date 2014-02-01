package com.github.snoblind.winterface.jsoup;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.w3c.dom.NodeList;
import com.github.snoblind.winterface.ExtendedHTMLDocument;
import com.github.snoblind.winterface.ExtendedHTMLElement;
import com.github.snoblind.winterface.event.EventDispatcher;
import com.github.snoblind.winterface.jsoup.JSoupDocument;
import java.io.IOException;
import java.net.URL;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.initMocks;

public class JSoupQuerySelectorTest {

	private JSoupQuerySelector querySelector;

	@Mock EventDispatcher eventDispatcher;
	
	@Before
	public void setUp() {
		initMocks(this);
		querySelector = new JSoupQuerySelector();
	}

	@Test
	public void querySelectorAll() throws IOException {
		final URL resource = getClass().getResource("/HTMLDocumentTest.html");
		final String html = IOUtils.toString(resource);
		final JSoupDocument document = new JSoupDocument(Jsoup.parse(html), eventDispatcher);
		final JSoupFormElement form = (JSoupFormElement) querySelector.querySelector(document, "form");
		final NodeList nodes = querySelector.querySelectorAll(form, "button[type=submit], button[type=reset]");
		assertEquals(2, nodes.getLength());
		final JSoupElement button1 = (JSoupElement) nodes.item(0);
		final JSoupElement button2 = (JSoupElement) nodes.item(1);
		assertEquals("<button type=\"reset\">reset</button>", button1.getOuterHTML());
		assertEquals("<button type=\"submit\">submit</button>", button2.getOuterHTML());
	}

	@Test(expected = IllegalArgumentException.class)
	public void querySelectorAll_NotJSoupElement() {
		querySelector.querySelectorAll(mock(ExtendedHTMLElement.class), "body");
	}

	@Test(expected = IllegalArgumentException.class)
	public void querySelectorAll_NotJSoupDocument() {
		querySelector.querySelectorAll(mock(ExtendedHTMLDocument.class), "body");
	}
}
