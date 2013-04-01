package com.github.mattmann.winterface;

public interface HTMLLabelElement extends HTMLElement {

	HTMLFormElement getForm();
	
	CharSequence getAccessKey();
	void setAccessKey(CharSequence accessKey);
	
	CharSequence getHtmlFor();
	void setHtmlFor(CharSequence htmlFor);
}
