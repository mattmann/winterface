package com.github.snoblind.winterface;

import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLElement;

public interface ExtendedHTMLElement extends HTMLElement, EventTarget, GlobalEventHandlers, NodeEventHandlers, TouchEventHandlers {
	
	String getInnerHTML();
	void setInnerHTML(String innerHTML);

	String getOuterHTML();
	void setOuterHTML(String outerHTML);

	HTMLElement querySelector(String selectors);
	NodeList querySelectorAll(String selectors);
}