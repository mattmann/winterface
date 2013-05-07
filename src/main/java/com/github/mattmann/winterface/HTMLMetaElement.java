package com.github.mattmann.winterface;

public interface HTMLMetaElement extends org.w3c.dom.html.HTMLMetaElement, HTMLElement {

	String getCharset();
	void setCharset(String charset);
}
