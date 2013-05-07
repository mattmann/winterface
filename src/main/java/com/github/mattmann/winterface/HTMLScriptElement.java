package com.github.mattmann.winterface;

public interface HTMLScriptElement extends org.w3c.dom.html.HTMLScriptElement, HTMLElement {

	boolean isAsync();
	void setAsync(boolean async);
}
