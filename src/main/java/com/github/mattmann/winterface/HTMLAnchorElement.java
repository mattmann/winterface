package com.github.mattmann.winterface;

public interface HTMLAnchorElement extends org.w3c.dom.html.HTMLAnchorElement, HTMLElement {

	void click();
	String getMedia();
	void setMedia(String media);
}
