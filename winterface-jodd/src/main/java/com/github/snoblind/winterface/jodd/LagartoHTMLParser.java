package com.github.snoblind.winterface.jodd;

import com.github.snoblind.winterface.spi.HTMLParser;
import jodd.lagarto.dom.LagartoDOMBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class LagartoHTMLParser implements HTMLParser {

	private LagartoDOMBuilder parser = new LagartoDOMBuilder();
	
	public Document parse(String html, String baseUri) {
		return new LagartoDocument(parser.parse(html));
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
