package com.github.mattmann.winterface;

public interface HTMLLabelElement extends HTMLElement {

	HTMLFormElement getForm();
	
	String getAccessKey();
	void setAccessKey(String accessKey);
	
	String getHtmlFor();
	void setHtmlFor(String htmlFor);
}
