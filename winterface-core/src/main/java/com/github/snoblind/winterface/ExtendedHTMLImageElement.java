package com.github.snoblind.winterface;

import org.w3c.dom.html.HTMLImageElement;

public interface ExtendedHTMLImageElement extends HTMLImageElement, ExtendedHTMLElement {

	boolean isComplete();
	
	EventListener getOnabort();
	void setOnabort(EventListener handler);
	
	OnErrorEventHandler getOnerror();
	void setOnerror(OnErrorEventHandler handler);
	
	EventListener getOnload();
	void setOnload(EventListener handler);
}