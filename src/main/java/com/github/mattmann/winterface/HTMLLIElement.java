package com.github.mattmann.winterface;

public interface HTMLLIElement extends HTMLElement {
	
	CharSequence getType();
	void setType(CharSequence type);
	
	long getValue();
	void setValue(long value);
}
