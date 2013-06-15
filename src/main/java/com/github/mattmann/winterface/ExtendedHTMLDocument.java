package com.github.mattmann.winterface;

import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLDocument;

public interface ExtendedHTMLDocument extends HTMLDocument, EventTarget {

	ExtendedHTMLElement querySelector(String selectors);
	NodeList querySelectorAll(String selectors);

	NodeList getElementsByName(String elementName);
	
	Window getDefaultView();

	ExtendedHTMLElement getBody();
}
