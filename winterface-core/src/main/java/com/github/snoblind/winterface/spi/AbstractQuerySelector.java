package com.github.snoblind.winterface.spi;

import com.github.snoblind.winterface.ExtendedHTMLDocument;
import com.github.snoblind.winterface.ExtendedHTMLElement;
import org.w3c.dom.NodeList;

public abstract class AbstractQuerySelector implements QuerySelector {

	public ExtendedHTMLElement querySelector(ExtendedHTMLDocument document, String query) {
		final NodeList nodes = querySelectorAll(document, query);
		return nodes.getLength() == 0 ? null : (ExtendedHTMLElement)nodes.item(0);
	}

	public ExtendedHTMLElement querySelector(ExtendedHTMLElement element, String query) {
		final NodeList nodes = querySelectorAll(element, query);
		return nodes.getLength() == 0 ? null : (ExtendedHTMLElement)nodes.item(0);
	}
}
