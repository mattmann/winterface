package com.github.mattmann.winterface;

public interface HTMLFormElement extends org.w3c.dom.html.HTMLFormElement, HTMLElement {

	EventListener getOnsubmit();
	void setOnsubmit(EventListener onsubmit);
	
	EventListener getOnreset();
	void setOnreset(EventListener onreset);
}
