package com.github.mattmann.winterface;

public interface HTMLIsIndexElement extends HTMLElement {
	
	HTMLFormElement getForm();
	
	CharSequence getPrompt();
	void setPrompt(CharSequence prompt);
}
