package com.github.mattmann.winterface;

public interface HTMLLegendElement extends HTMLElement {

	HTMLFormElement getForm();
	
	CharSequence getAccessKey();
	void setAccessKey(CharSequence accessKey);
	
	CharSequence getAlign();
	void setAlign(CharSequence align);
}
