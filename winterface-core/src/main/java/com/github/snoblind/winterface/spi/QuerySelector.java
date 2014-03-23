package com.github.snoblind.winterface.spi;

import com.github.snoblind.winterface.ExtendedHTMLCollection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface QuerySelector {
	Element querySelector(Document document, String query);
	Element querySelector(Element element, String query);
	ExtendedHTMLCollection querySelectorAll(Document document, String query);
	ExtendedHTMLCollection querySelectorAll(Element element, String query);
}
