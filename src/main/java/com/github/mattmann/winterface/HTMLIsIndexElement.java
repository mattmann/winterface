package com.github.mattmann.winterface;

public interface HTMLIsIndexElement extends HTMLElement {
	
	HTMLFormElement getForm();
	
	String getPrompt();
	void setPrompt(String prompt);
}
