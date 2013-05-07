package com.github.mattmann.winterface;

public interface HTMLMapElement extends HTMLElement {
	
	HTMLCollection getAreas();

	String getName();
	void setName(String name);
}
