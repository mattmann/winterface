package com.github.mattmann.winterface;

import org.w3c.dom.NodeList;

public interface HTMLDocument extends org.w3c.dom.html.HTMLDocument, EventTarget {

	HTMLElement querySelector(String selectors);
	NodeList querySelectorAll(String selectors);

	NodeList getElementsByName(String elementName);
	
	Window getDefaultView();

	HTMLElement getBody();
}
