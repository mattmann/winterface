package com.github.snoblind.winterface.jsoup;

import com.github.snoblind.winterface.ExtendedHTMLElement;
import com.github.snoblind.winterface.spi.HTMLParser;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class JSoupHTMLParser implements HTMLParser {

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

	public String getInnerHTML(ExtendedHTMLElement element) {
		return ((JSoupElement) element).node.html();
	}

	public void setInnerHTML(ExtendedHTMLElement element, String html) {
		((JSoupElement) element).node.html(html);
	}

	public String getOuterHTML(ExtendedHTMLElement element) {
		return ((JSoupElement) element).node.outerHtml();
	}

	public Node setOuterHTML(ExtendedHTMLElement element, String outerHTML) {
		throw new UnsupportedOperationException();
	}

}
