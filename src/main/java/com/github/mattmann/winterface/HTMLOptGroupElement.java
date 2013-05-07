package com.github.mattmann.winterface;

public interface HTMLOptGroupElement extends HTMLElement {

	boolean isDisabled();
	void setDisabled(boolean disabled);
	
	String getLabel();
	void setLabel(String label);
}
