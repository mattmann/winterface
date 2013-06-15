package com.github.mattmann.winterface;

import org.w3c.dom.html.HTMLAnchorElement;

public interface ExtendedHTMLAnchorElement extends HTMLAnchorElement, ExtendedHTMLElement {

	void click();
	String getMedia();
	void setMedia(String media);
}
