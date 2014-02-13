package com.github.snoblind.winterface;

import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLDocument;
import org.w3c.dom.html.HTMLElement;

public interface ExtendedHTMLDocument extends HTMLDocument, EventTarget {

	HTMLElement querySelector(String selectors);

	NodeList querySelectorAll(String selectors);

	NodeList getElementsByName(String elementName);
	
	Window getDefaultView();

	HTMLElement getBody();
}
