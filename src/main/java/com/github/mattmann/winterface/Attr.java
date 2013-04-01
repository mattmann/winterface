package com.github.mattmann.winterface;

public interface Attr extends Node {
	boolean isSpecified();
	CharSequence getName();
	CharSequence getValue();
	Element getOwnerElement();
	void setValue(CharSequence value);
}
