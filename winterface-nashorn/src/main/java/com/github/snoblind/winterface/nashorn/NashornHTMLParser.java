package com.github.snoblind.winterface.nashorn;

import com.github.snoblind.winterface.spi.HTMLParser;
import org.jsoup.parser.Parser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class NashornHTMLParser implements HTMLParser {

	public Document parse(String html, String baseUri) {
		final org.jsoup.nodes.Document d1 = Parser.parse(html, baseUri);
		final NashornDocument d2 = new NashornDocument();
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
	
	public String getInnerText(Node node) {
		throw new UnsupportedOperationException();
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
