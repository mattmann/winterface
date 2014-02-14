package com.github.snoblind.winterface.spi;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public abstract class AbstractQuerySelector implements QuerySelector {

	public Element querySelector(Document document, String query) {
		final NodeList nodes = querySelectorAll(document, query);
		return nodes.getLength() == 0 ? null : (Element) nodes.item(0);
	}

	public Element querySelector(Element element, String query) {
		final NodeList nodes = querySelectorAll(element, query);
		return nodes.getLength() == 0 ? null : (Element) nodes.item(0);
	}
}
