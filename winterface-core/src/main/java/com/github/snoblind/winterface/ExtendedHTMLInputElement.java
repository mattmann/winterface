package com.github.snoblind.winterface;

import org.w3c.dom.html.HTMLInputElement;

public interface ExtendedHTMLInputElement extends HTMLInputElement, ExtendedHTMLElement {

	void setType(String type);
}
