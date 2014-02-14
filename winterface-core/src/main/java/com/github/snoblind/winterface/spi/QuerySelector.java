package com.github.snoblind.winterface.spi;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public interface QuerySelector {
	Element querySelector(Document document, String query);
	Element querySelector(Element element, String query);
	NodeList querySelectorAll(Document document, String query);
	NodeList querySelectorAll(Element element, String query);
}
