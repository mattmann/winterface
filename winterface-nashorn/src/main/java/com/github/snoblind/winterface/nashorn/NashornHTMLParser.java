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
import static org.apache.commons.lang.StringUtils.isWhitespace;
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
		for (org.jsoup.nodes.Node c1: e1.childNodes()) {
			final Node c2 = convert(c1, document);
			if (c2 != null) {
				e2.appendChild(c2);
			}
		}
		return e2;
	}
	
	private Node convert(final org.jsoup.nodes.Node node, final NashornDocument document) {
		if (node instanceof org.jsoup.nodes.Element) {
			return convert((org.jsoup.nodes.Element) node, document);
		}
		if (node instanceof org.jsoup.nodes.TextNode) {
			final String text = ((org.jsoup.nodes.TextNode) node).text();
			return isWhitespace(text) ? null : document.createTextNode(text);
		}
		if (node instanceof org.jsoup.nodes.DataNode) {
			return document.createCDATASection(((org.jsoup.nodes.DataNode) node).getWholeData());
		}
		if (node instanceof org.jsoup.nodes.Comment) {
			return document.createComment(((org.jsoup.nodes.Comment) node).getData());
		}
		throw new IllegalArgumentException(node.getClass().getName());
	}

	private org.jsoup.nodes.Element convert(final Element element) {
		return (org.jsoup.nodes.Element) convert((Node) element);
	}

	private String getBaseURI(final Node node) {
		return ((NashornDocument) node.getOwnerDocument()).getBaseURI();
	}
	
	private org.jsoup.nodes.Node convert(final Node node) {
		final String html = toString(node);
		final List<org.jsoup.nodes.Node> results = Parser.parseFragment(html, null, getBaseURI(node));
		isTrue(1 == results.size());
		return results.get(0);
	}
	
	private String toString(final Node node) {
		try {
			final Writer writer = new StringWriter();
			final Transformer transformer = transformerFactory.newTransformer();
			transformer.transform(new DOMSource(node), new StreamResult(writer));
			return writer.toString();
		}
		catch (TransformerException x) {
			throw new RuntimeException(x);
		}
	}
	
	public String getInnerText(final Node node) {
		return ((org.jsoup.nodes.Element) convert(node)).text();
	}

	public void setInnerText(Node node, String innerText) {
		throw new UnsupportedOperationException();
	}

	public String getInnerHTML(Element element) {
		return convert(element).html();
	}

	public void setInnerHTML(final Element element, final String html) {
		final NashornDocument document = (NashornDocument) element.getOwnerDocument();
		final String baseURI = getBaseURI(element);
		final List<org.jsoup.nodes.Node> childNodes = Parser.parseXmlFragment(html, baseURI);
		while (element.getFirstChild() != null) {
			element.removeChild(element.getFirstChild());
		}
		for (org.jsoup.nodes.Node childNode: childNodes) {
			element.appendChild(convert(childNode, document));
		}
	}

	public String getOuterHTML(Element element) {
		return toString(element);
	}

	public Node setOuterHTML(Element element, String outerHTML) {
		throw new UnsupportedOperationException();
	}
}
