package com.github.snoblind.winterface.spi;

import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLDocument;
import org.w3c.dom.html.HTMLElement;

public interface QuerySelector {
	HTMLElement querySelector(HTMLDocument document, String query);
	HTMLElement querySelector(HTMLElement element, String query);
	NodeList querySelectorAll(HTMLDocument document, String query);
	NodeList querySelectorAll(HTMLElement element, String query);
}
