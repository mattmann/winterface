package com.github.mattmann.winterface;

import com.github.mattmann.winterface.Event;
import com.github.mattmann.winterface.EventListener;
import com.github.mattmann.winterface.HTMLAnchorElement;
import com.github.mattmann.winterface.HTMLAppletElement;
import com.github.mattmann.winterface.HTMLBodyElement;
import com.github.mattmann.winterface.HTMLDocument;
import com.github.mattmann.winterface.HTMLElement;
import com.github.mattmann.winterface.HTMLFormElement;
import com.github.mattmann.winterface.HTMLImageElement;
import java.io.IOException;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLCollection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public abstract class HTMLDocumentTest {
	
	private HTMLDocument htmlDocument;

	@Before
	public void setUp() throws IOException {
		final URL resource = getClass().getResource("/HTMLDocumentTest.html");
		final String html = IOUtils.toString(resource);
		htmlDocument = parseDocument(html, resource.toExternalForm());
	}

	protected abstract HTMLDocument parseDocument(String html, String baseURI) throws IOException;
	
	@Test
	public void test() {
		HTMLElement textField = htmlDocument.querySelector("input[type=text]");
		assertNotNull(textField);
		NodeList paragraphs = htmlDocument.getElementsByTagName("p");
		assertEquals(1, paragraphs.getLength());
		NodeList textAreas = htmlDocument.getElementsByTagName("textarea");
		assertEquals(1, textAreas.getLength());
		NodeList buttons = htmlDocument.getElementsByTagName("button");
		assertEquals(2, buttons.getLength());
		HTMLElement body = htmlDocument.getBody();
		assertTrue(body instanceof HTMLBodyElement);
		test((HTMLBodyElement)body);
		HTMLCollection anchors = htmlDocument.getAnchors();
		assertEquals(3, anchors.getLength());
		for (int i = 0; i < anchors.getLength(); i++) {
			Node anchor = anchors.item(i);
			assertTrue(anchor instanceof HTMLAnchorElement);
			test((HTMLAnchorElement)anchor);
		}
		HTMLCollection links = htmlDocument.getLinks();
		assertEquals(3, links.getLength());
		for (int i = 0; i < links.getLength(); i++) {
			Node link = links.item(i);
			assertTrue(link instanceof HTMLAnchorElement);
			test((HTMLAnchorElement)link);
		}
		HTMLCollection applets = htmlDocument.getApplets();
		assertEquals(1, applets.getLength());
		for (int i = 0; i < applets.getLength(); i++) {
			Node applet = applets.item(i);
			assertTrue(applet instanceof HTMLAppletElement);
			test((HTMLAppletElement)applet);
		}
		HTMLCollection forms = htmlDocument.getForms();
		assertEquals(1, forms.getLength());
		HTMLFormElement form = (HTMLFormElement)forms.item(0);
		HTMLCollection formElements = form.getElements();
		assertEquals(4, formElements.getLength());
		HTMLCollection images = htmlDocument.getImages();
		assertEquals(3, images.getLength());
		for (int i = 0; i < images.getLength(); i++) {
			Node image = images.item(i);
			assertTrue(image instanceof HTMLImageElement);
			test((HTMLImageElement)image);
		}
	}

	protected void test(HTMLImageElement image) {
		image.getAlign();
		image.getAlt();
		image.getBorder();
		image.isComplete();
		image.getHeight();
		image.getHspace();
		image.getLongDesc();
		image.getLowsrc();
		image.getName();
		image.getSrc();
		image.getUseMap();
		image.getVspace();
		image.getWidth();
		image.getOnabort();
		image.getOnerror();
		image.getOnload();
		image.getOuterHTML();
	}

	protected void test(HTMLFormElement form) {
		form.getAcceptCharset();
		form.getAction();
		form.getEnctype();
		form.getLength();
		form.getMethod();
		form.getName();
		form.getTarget();
//		form.reset();
//		form.submit();
		form.getOuterHTML();
	}

	protected void test(HTMLAppletElement applet) {
		applet.getAlign();
		applet.getAlt();
		applet.getArchive();
		applet.getCode();
		applet.getCodeBase();
		applet.getHeight();
		applet.getHspace();
		applet.getName();
		applet.getObject();
		applet.getVspace();
		applet.getWidth();
		applet.getOuterHTML();
	}

	protected void test(HTMLAnchorElement anchor) {
		anchor.getAccessKey();
		anchor.getCharset();
		anchor.getCoords();
		anchor.getHref();
		anchor.getHreflang();
		anchor.getName();
		anchor.getRel();
		anchor.getRev();
		anchor.getShape();
		anchor.getTabIndex();
		anchor.getTarget();
		anchor.getType();
//		anchor.blur();
//		anchor.focus();
		EventListener listener = Mockito.mock(EventListener.class);
		anchor.getOuterHTML();
		anchor.setOnclick(listener);
		anchor.click();
		Mockito.verify(listener).handleEvent(Mockito.any(Event.class));
	}
	
	protected void test(HTMLBodyElement body) {
		assertEquals(body.getTagName().toString().toLowerCase(), "body");
		assertEquals(body.getBackground(), "background.gif");
		assertEquals(body.getALink(), "red");
		assertEquals(body.getBgColor(), "orange");
		assertEquals(body.getLink(), "yellow");
		assertEquals(body.getText(), "green");
		assertEquals(body.getVLink(), "blue");
	}
}