package com.github.snoblind.winterface.nashorn;

import com.github.snoblind.winterface.ExtendedHTMLCollection;
import com.github.snoblind.winterface.event.EventDispatcher;
import com.github.snoblind.winterface.spi.CSSParser;
import com.github.snoblind.winterface.spi.QuerySelector;
import com.github.snoblind.winterface.util.NodeListUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLAnchorElement;
import org.w3c.dom.html.HTMLBRElement;
import org.w3c.dom.html.HTMLBodyElement;
import org.w3c.dom.html.HTMLButtonElement;
import org.w3c.dom.html.HTMLDListElement;
import org.w3c.dom.html.HTMLDivElement;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLFieldSetElement;
import org.w3c.dom.html.HTMLFontElement;
import org.w3c.dom.html.HTMLFormElement;
import org.w3c.dom.html.HTMLHRElement;
import org.w3c.dom.html.HTMLHeadElement;
import org.w3c.dom.html.HTMLHeadingElement;
import org.w3c.dom.html.HTMLHtmlElement;
import org.w3c.dom.html.HTMLIFrameElement;
import org.w3c.dom.html.HTMLImageElement;
import org.w3c.dom.html.HTMLInputElement;
import org.w3c.dom.html.HTMLLIElement;
import org.w3c.dom.html.HTMLLabelElement;
import org.w3c.dom.html.HTMLLinkElement;
import org.w3c.dom.html.HTMLMetaElement;
import org.w3c.dom.html.HTMLOListElement;
import org.w3c.dom.html.HTMLOptionElement;
import org.w3c.dom.html.HTMLParagraphElement;
import org.w3c.dom.html.HTMLScriptElement;
import org.w3c.dom.html.HTMLSelectElement;
import org.w3c.dom.html.HTMLStyleElement;
import org.w3c.dom.html.HTMLTableCellElement;
import org.w3c.dom.html.HTMLTableElement;
import org.w3c.dom.html.HTMLTableRowElement;
import org.w3c.dom.html.HTMLTextAreaElement;
import org.w3c.dom.html.HTMLTitleElement;
import org.w3c.dom.html.HTMLUListElement;
import static com.github.snoblind.winterface.mock.Answers.UNSUPPORTED;
import static com.github.snoblind.winterface.mock.MockitoAnnotations.initMocks;
import static com.github.snoblind.winterface.required.RequiredProperties.assertRequiredProperties;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.w3c.dom.Node.ELEMENT_NODE;


public class NashornDocumentTest {

	private static final String XHTML_NAMESPACE_URI = "http://www.w3.org/1999/xhtml";
	
	private NashornDocument document;

	@Mock private CSSParser cssParser;
	@Mock private Element element;
	@Mock private EventDispatcher eventDispatcher;
	@Mock private ExtendedHTMLCollection collection;
	@Mock private QuerySelector querySelector;
	
	@Before
	public void setUp() {
		initMocks(this, UNSUPPORTED);
		document = new NashornDocument();
		document.setCssParser(cssParser);
		document.setEventDispatcher(eventDispatcher);
		document.setQuerySelector(querySelector);
		assertRequiredProperties(document);
		assertSame(querySelector, document.getQuerySelector());
	}

	private Element mockElement(final String namespaceURI, final String localName) {
		final Element element = mock(Element.class, UNSUPPORTED);
		doReturn(ELEMENT_NODE).when(element).getNodeType();
		doReturn(namespaceURI).when(element).getNamespaceURI();
		doReturn(localName).when(element).getLocalName();
		return element;
	}
	
	@Test
	public void getElementsByTagNameNS() {
		final Element e1 = mockElement(XHTML_NAMESPACE_URI, "applet");
		final Element e2 = mockElement(XHTML_NAMESPACE_URI, "button");
		final Element e3 = mockElement(XHTML_NAMESPACE_URI, "center");
		doReturn(NodeListUtils.toHTMLCollection(e1, e2, e3)).when(querySelector).querySelectorAll(document, "*");
		final NodeList elements = document.getElementsByTagNameNS(XHTML_NAMESPACE_URI, "*");
		assertEquals(3, elements.getLength());
		assertSame(e1, elements.item(0));
		assertSame(e2, elements.item(1));
		assertSame(e3, elements.item(2));
	}
	
	@Test
	public void querySelector() {
		final String query = "script[type = \"text/javaScript\"]";
		doReturn(element).when(querySelector).querySelector(document, query);
		assertSame(element, document.querySelector(query));
	}

	@Test
	public void querySelectorAll() {
		final String query = "script[type = \"text/javaScript\"]";
		doReturn(collection).when(querySelector).querySelectorAll(document, query);
		assertSame(collection, document.querySelectorAll(query));
	}

	@Test
	public void getElementsByName() {
		final String name = "img";
		doReturn(collection).when(querySelector).querySelectorAll(document, String.format("[name=\"%s\"]", name));
		assertSame(collection, document.getElementsByName(name));
	}

	@Test
	public void getElementsByTagName() {
		final String tagName = "input";
		doReturn(collection).when(querySelector).querySelectorAll(document, tagName);
		assertSame(collection, document.getElementsByTagName(tagName));
	}

	@Test
	public void getElementById() {
		final String id = "id";
		doReturn(element).when(querySelector).querySelector(document, String.format("[id=\"%s\"]", id));
		assertSame(element, document.getElementById(id));
	}
	
	@Test
	public void createElement() {
		assertTrue(document.createElement("a") instanceof HTMLAnchorElement);
		assertTrue(document.createElement("body") instanceof HTMLBodyElement);
		assertTrue(document.createElement("br") instanceof HTMLBRElement);
		assertTrue(document.createElement("button") instanceof HTMLButtonElement);
		assertTrue(document.createElement("div") instanceof HTMLDivElement);
		assertTrue(document.createElement("dl") instanceof HTMLDListElement);
		assertTrue(document.createElement("fieldset") instanceof HTMLFieldSetElement);
		assertTrue(document.createElement("font") instanceof HTMLFontElement);
		assertTrue(document.createElement("form") instanceof HTMLFormElement);
		assertTrue(document.createElement("h1") instanceof HTMLHeadingElement);
		assertTrue(document.createElement("h2") instanceof HTMLHeadingElement);
		assertTrue(document.createElement("h3") instanceof HTMLHeadingElement);
		assertTrue(document.createElement("h4") instanceof HTMLHeadingElement);
		assertTrue(document.createElement("h5") instanceof HTMLHeadingElement);
		assertTrue(document.createElement("h6") instanceof HTMLHeadingElement);
		assertTrue(document.createElement("head") instanceof HTMLHeadElement);
		assertTrue(document.createElement("hr") instanceof HTMLHRElement);
		assertTrue(document.createElement("html") instanceof HTMLHtmlElement);
		assertTrue(document.createElement("iframe") instanceof HTMLIFrameElement);
		assertTrue(document.createElement("img") instanceof HTMLImageElement);
		assertTrue(document.createElement("input") instanceof HTMLInputElement);
		assertTrue(document.createElement("label") instanceof HTMLLabelElement);
		assertTrue(document.createElement("li") instanceof HTMLLIElement);
		assertTrue(document.createElement("link") instanceof HTMLLinkElement);
		assertTrue(document.createElement("meta") instanceof HTMLMetaElement);
		assertTrue(document.createElement("ol") instanceof HTMLOListElement);
		assertTrue(document.createElement("option") instanceof HTMLOptionElement);
		assertTrue(document.createElement("p") instanceof HTMLParagraphElement);
		assertTrue(document.createElement("script") instanceof HTMLScriptElement);
		assertTrue(document.createElement("select") instanceof HTMLSelectElement);
		assertTrue(document.createElement("style") instanceof HTMLStyleElement);
		assertTrue(document.createElement("table") instanceof HTMLTableElement);
		assertTrue(document.createElement("textarea") instanceof HTMLTextAreaElement);
		assertTrue(document.createElement("title") instanceof HTMLTitleElement);
		assertTrue(document.createElement("td") instanceof HTMLTableCellElement);
		assertTrue(document.createElement("tr") instanceof HTMLTableRowElement);
		assertTrue(document.createElement("ul") instanceof HTMLUListElement);
		assertTrue(document.createElement("u") instanceof HTMLElement);
	}
}
