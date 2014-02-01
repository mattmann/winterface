package com.github.snoblind.winterface;

import org.w3c.dom.html.HTMLScriptElement;

public interface ExtendedHTMLScriptElement extends HTMLScriptElement, ExtendedHTMLElement {

	boolean isAsync();
	void setAsync(boolean async);
}
