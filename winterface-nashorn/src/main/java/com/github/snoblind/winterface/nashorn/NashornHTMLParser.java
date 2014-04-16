package com.github.snoblind.winterface.nashorn;

import com.github.snoblind.winterface.spi.HTMLParser;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.jsoup.parser.Parser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import static org.apache.commons.lang.Validate.isTrue;

public class NashornHTMLParser implements HTMLParser {

	private TransformerFactory transformerFactory = TransformerFactory.newInstance();
	
	public Document parse(String html, String baseUri) {
		final org.jsoup.nodes.Document d1 = Parser.parse(html, baseUri);
		final NashornDocument d2 = new NashornDocument();
		d2.setBaseURI(baseUri);
		d2.appendChild(convert(d1.child(0), d2));
		return d2;
	}

	private Node convert(final org.jsoup.nodes.Element e1, final NashornDocument document) {
		final Element e2 = document.createElement(e1.tagName());
		for (org.jsoup.nodes.Attribute attribute: e1.attributes()) {
			e2.setAttribute(attribute.getKey(), attribute.getValue());
		}
		for (org.jsoup.nodes.Node childNode: e1.childNodes()) {
			e2.appendChild(convert(childNode, document));
		}
		return e2;
	}

	private Node convert(final org.jsoup.nodes.Node node, final NashornDocument document) {
		if (node instanceof org.jsoup.nodes.Element) {
			return convert((org.jsoup.nodes.Element) node, document);
		}
		if (node instanceof org.jsoup.nodes.TextNode) {
			return document.createTextNode(((org.jsoup.nodes.TextNode) node).text());
		}
		if (node instanceof org.jsoup.nodes.DataNode) {
			return document.createCDATASection(((org.jsoup.nodes.DataNode) node).getWholeData());
		}
		if (node instanceof org.jsoup.nodes.Comment) {
			return document.createComment(((org.jsoup.nodes.Comment) node).getData());
		}
		throw new IllegalArgumentException(node.getClass().getName());
	}

	private org.jsoup.nodes.Node convert(final Node node) {
		final String html;
		try {
			final Writer writer = new StringWriter();
			final Transformer transformer = transformerFactory.newTransformer();
			transformer.transform(new DOMSource(node), new StreamResult(writer));
			html = writer.toString();
		}
		catch (TransformerException x) {
			throw new RuntimeException(x);
		}
		final List<org.jsoup.nodes.Node> results = Parser.parseFragment(html, null, ((NashornDocument) node.getOwnerDocument()).getBaseURI());
		isTrue(1 == results.size());
		return results.get(0);
	}
	
	public String getInnerText(final Node node) {
		return ((org.jsoup.nodes.Element) convert(node)).text();
	}

	public void setInnerText(Node node, String innerText) {
		throw new UnsupportedOperationException();
	}

	public String getInnerHTML(Element element) {
		throw new UnsupportedOperationException();
	}

	public void setInnerHTML(Element element, String innerHTML) {
		throw new UnsupportedOperationException();
	}

	public String getOuterHTML(Element element) {
		throw new UnsupportedOperationException();
	}

	public Node setOuterHTML(Element element, String outerHTML) {
		throw new UnsupportedOperationException();
	}
}
