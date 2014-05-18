package com.github.snoblind.winterface;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.html.HTMLAnchorElement;
import org.w3c.dom.html.HTMLAppletElement;
import org.w3c.dom.html.HTMLBodyElement;
import org.w3c.dom.html.HTMLElement;
import com.github.snoblind.winterface.ExtendedHTMLAnchorElement;
import com.github.snoblind.winterface.ExtendedHTMLDocument;
import com.github.snoblind.winterface.ExtendedHTMLElement;
import com.github.snoblind.winterface.ExtendedHTMLFormElement;
import com.github.snoblind.winterface.ExtendedHTMLImageElement;
import java.io.IOException;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLCollection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public abstract class HTMLDocumentTest {
	
	protected ExtendedHTMLDocument htmlDocument;

	@Before
	public void setUp() throws IOException {
		final URL resource = getClass().getResource("/HTMLDocumentTest.html");
		final String html = IOUtils.toString(resource);
		htmlDocument = parseDocument(html, resource.toExternalForm());
	}

	protected abstract ExtendedHTMLDocument parseDocument(String html, String baseURI) throws IOException;
	
	@Test
	public void test() {
		Element textField = htmlDocument.querySelector("input[type=text]");
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
		ExtendedHTMLFormElement form = (ExtendedHTMLFormElement)forms.item(0);
		HTMLCollection formElements = form.getElements();
		assertEquals(4, formElements.getLength());
		HTMLCollection images = htmlDocument.getImages();
		assertEquals(3, images.getLength());
		for (int i = 0; i < images.getLength(); i++) {
			Node image = images.item(i);
			assertTrue(image instanceof ExtendedHTMLImageElement);
			test((ExtendedHTMLImageElement)image);
		}
	}

	protected void test(ExtendedHTMLImageElement image) {
		image.getAlign();
		image.getAlt();
		image.getBorder();
		image.isComplete();
		image.getHeight();
		image.getHspace();
		image.getLongDesc();
		image.getLowSrc();
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

	protected void test(ExtendedHTMLFormElement form) {
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
		((ExtendedHTMLElement) applet).getOuterHTML();
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
		((ExtendedHTMLElement) anchor).getOuterHTML();
		((ExtendedHTMLElement) anchor).setOnclick(listener);
		((ExtendedHTMLAnchorElement) anchor).click();
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