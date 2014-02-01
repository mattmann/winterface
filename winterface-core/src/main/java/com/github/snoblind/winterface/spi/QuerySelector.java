package com.github.snoblind.winterface.spi;

import com.github.snoblind.winterface.ExtendedHTMLDocument;
import com.github.snoblind.winterface.ExtendedHTMLElement;
import org.w3c.dom.NodeList;

public interface QuerySelector {
	ExtendedHTMLElement querySelector(ExtendedHTMLDocument document, String query);
	ExtendedHTMLElement querySelector(ExtendedHTMLElement element, String query);
	NodeList querySelectorAll(ExtendedHTMLDocument document, String query);
	NodeList querySelectorAll(ExtendedHTMLElement element, String query);
}
