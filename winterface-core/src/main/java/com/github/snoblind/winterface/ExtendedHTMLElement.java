package com.github.snoblind.winterface;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.html.HTMLElement;

public interface ExtendedHTMLElement extends HTMLElement, EventTarget, GlobalEventHandlers, NodeEventHandlers, TouchEventHandlers {
	
	String getInnerHTML();
	void setInnerHTML(String innerHTML);

	String getOuterHTML();
	void setOuterHTML(String outerHTML);

	Element querySelector(String selectors);
	NodeList querySelectorAll(String selectors);

	CSSStyleDeclaration getStyle();
}