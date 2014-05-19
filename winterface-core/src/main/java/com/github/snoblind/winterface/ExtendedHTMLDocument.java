package com.github.snoblind.winterface;

import org.w3c.dom.Element;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.html.HTMLDocument;
import org.w3c.dom.views.DocumentView;

public interface ExtendedHTMLDocument extends DocumentView, EventTarget, HTMLDocument {

	Element querySelector(String selectors);
	ExtendedHTMLCollection querySelectorAll(String selectors);
	ExtendedHTMLCollection getElementsByName(String elementName);
	Window getDefaultView();
}