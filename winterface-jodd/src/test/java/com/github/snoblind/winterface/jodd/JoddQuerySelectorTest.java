package com.github.snoblind.winterface.jodd;

import com.github.snoblind.winterface.spi.QuerySelector;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import static com.github.snoblind.winterface.util.NodeListUtils.iterator;
import static org.apache.commons.lang.Validate.notNull;
import static org.junit.Assert.assertEquals;

public class JoddQuerySelectorTest {
	
	private static final URL RESOURCE = JoddQuerySelectorTest.class.getResource("/JoddQuerySelectorTest.html");

	private static DocumentBuilderFactory documentBuilderFactory;
	private static String content;

	@BeforeClass
	public static void setUpClass() throws IOException {
		notNull(RESOURCE);
		content = IOUtils.toString(RESOURCE);
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
	}
	
	private QuerySelector querySelector;
	private Document document;

	@Before
	public void setUp() throws IOException, SAXException, ParserConfigurationException {
		querySelector = new JoddQuerySelector();
		document = documentBuilderFactory.newDocumentBuilder().parse(new InputSource(new StringReader(content)));
	}

	@Test
	public void querySelectorAll_Element() {
		final Element element = querySelector.querySelector(document.getDocumentElement(), "body > form > button[type=\"submit\"]");
		assertEquals("button", element.getTagName());
		assertEquals("submit", element.getAttribute("type"));
	}

	@Test
	public void querySelectorAll_Document() {
		final String query = "button[type=\"submit\"], img[src^=\"image1\"], input, a[name$=\"3\"], meta[charset], .c, div > p:first-child, #identifier";
		final NodeList nodes = querySelector.querySelectorAll(document, query);
		assertEquals(9, nodes.getLength());
		final Iterator<Node> iterator = iterator(nodes);
		assertEquals("button", ((Element) iterator.next()).getTagName());
		assertEquals("img", ((Element) iterator.next()).getTagName());
		assertEquals("input", ((Element) iterator.next()).getTagName());
		assertEquals("a", ((Element) iterator.next()).getTagName());
		assertEquals("meta", ((Element) iterator.next()).getTagName());
		assertEquals("a", ((Element) iterator.next()).getTagName());
		assertEquals("a", ((Element) iterator.next()).getTagName());
		assertEquals("p", ((Element) iterator.next()).getTagName());
		assertEquals("div", ((Element) iterator.next()).getTagName());
	}
}
