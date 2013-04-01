package com.github.mattmann.winterface;

public interface HTMLMapElement extends HTMLElement {
	
	HTMLCollection getAreas();

	CharSequence getName();
	void setName(CharSequence name);
}
