package com.github.snoblind.winterface.jsoup;

import com.github.snoblind.winterface.event.EventDispatcher;
import com.github.snoblind.winterface.spi.HTMLParser;
import org.jsoup.parser.Parser;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import static org.apache.commons.lang.Validate.notNull;

public class JSoupHTMLParser implements HTMLParser {

	private final EventDispatcher eventDispatcher;
	
	public JSoupHTMLParser(EventDispatcher eventDispatcher) {
		notNull(this.eventDispatcher = eventDispatcher);
	}

	public Document parse(String html, String baseUri) {
		return new JSoupDocument(Parser.parse(html, baseUri), eventDispatcher);
	}

	public String getInnerText(Node node) {
		if (node instanceof CharacterData) {
			return ((CharacterData)node).getData();
		}
		final StringBuilder builder = new StringBuilder();
		final NodeList childNodes = node.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			JSoupNode<?> child = (JSoupNode<?>) childNodes.item(i);
			builder.append(child.getInnerText());
		}
		return builder.toString();
	}

	public void setInnerText(Node node, String innerText) {
		throw new UnsupportedOperationException();
	}

	public String getInnerHTML(Element element) {
		return ((JSoupElement) element).node.html();
	}

	public void setInnerHTML(Element element, String html) {
		((JSoupElement) element).node.html(html);
	}

	public String getOuterHTML(Element element) {
		return ((JSoupElement) element).node.outerHtml();
	}

	public Node setOuterHTML(Element element, String outerHTML) {
		throw new UnsupportedOperationException();
	}
}
