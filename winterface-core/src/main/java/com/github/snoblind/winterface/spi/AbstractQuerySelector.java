package com.github.snoblind.winterface.spi;

import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLDocument;
import org.w3c.dom.html.HTMLElement;

public abstract class AbstractQuerySelector implements QuerySelector {

	public HTMLElement querySelector(HTMLDocument document, String query) {
		final NodeList nodes = querySelectorAll(document, query);
		return nodes.getLength() == 0 ? null : (HTMLElement) nodes.item(0);
	}

	public HTMLElement querySelector(HTMLElement element, String query) {
		final NodeList nodes = querySelectorAll(element, query);
		return nodes.getLength() == 0 ? null : (HTMLElement) nodes.item(0);
	}
}
