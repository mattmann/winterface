package com.github.mattmann.winterface;

public interface HTMLLegendElement extends HTMLElement {

	HTMLFormElement getForm();
	
	String getAccessKey();
	void setAccessKey(String accessKey);
	
	String getAlign();
	void setAlign(String align);
}
