package com.github.mattmann.winterface;

public interface HTMLLIElement extends HTMLElement {
	
	String getType();
	void setType(String type);
	
	long getValue();
	void setValue(long value);
}
