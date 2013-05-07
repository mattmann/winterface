package com.github.mattmann.winterface;

public interface HTMLImageElement extends org.w3c.dom.html.HTMLImageElement, HTMLElement {

	boolean isComplete();
	
	EventListener getOnabort();
	void setOnabort(EventListener handler);
	
	OnErrorEventHandler getOnerror();
	void setOnerror(OnErrorEventHandler handler);
	
	EventListener getOnload();
	void setOnload(EventListener handler);
}