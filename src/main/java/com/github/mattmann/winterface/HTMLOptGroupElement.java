package com.github.mattmann.winterface;

public interface HTMLOptGroupElement extends HTMLElement {

	boolean isDisabled();
	void setDisabled(boolean disabled);
	
	CharSequence getLabel();
	void setLabel(CharSequence label);
}
