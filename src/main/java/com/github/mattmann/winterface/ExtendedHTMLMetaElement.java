package com.github.mattmann.winterface;

import org.w3c.dom.html.HTMLMetaElement;

public interface ExtendedHTMLMetaElement extends HTMLMetaElement, ExtendedHTMLElement {

	String getCharset();
	void setCharset(String charset);
}
