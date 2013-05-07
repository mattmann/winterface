package com.github.mattmann.winterface;

import org.w3c.dom.html.HTMLCollection;

public interface HTMLMapElement extends HTMLElement {
	
	HTMLCollection getAreas();

	String getName();
	void setName(String name);
}
