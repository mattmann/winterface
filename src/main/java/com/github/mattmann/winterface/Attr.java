package com.github.mattmann.winterface;

public interface Attr extends Node {
	boolean isSpecified();
	String getName();
	String getValue();
	Element getOwnerElement();
	void setValue(String value);
}
