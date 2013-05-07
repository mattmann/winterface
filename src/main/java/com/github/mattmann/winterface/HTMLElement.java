package com.github.mattmann.winterface;

import org.w3c.dom.NodeList;

public interface HTMLElement extends org.w3c.dom.html.HTMLElement, EventTarget, GlobalEventHandlers, NodeEventHandlers, TouchEventHandlers {
	
	String getInnerHTML();
	void setInnerHTML(String innerHTML);

	String getOuterHTML();
	void setOuterHTML(String outerHTML);

	HTMLElement querySelector(String selectors);
	NodeList querySelectorAll(String selectors);
}