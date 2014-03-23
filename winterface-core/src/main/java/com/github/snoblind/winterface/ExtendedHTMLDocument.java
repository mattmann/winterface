package com.github.snoblind.winterface;

import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLDocument;

public interface ExtendedHTMLDocument extends HTMLDocument, EventTarget {

	Element querySelector(String selectors);
	ExtendedHTMLCollection querySelectorAll(String selectors);
	ExtendedHTMLCollection getElementsByName(String elementName);
	Window getDefaultView();
}
