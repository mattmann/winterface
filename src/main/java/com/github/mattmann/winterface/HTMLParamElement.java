package com.github.mattmann.winterface;

public interface HTMLParamElement extends HTMLElement {
	
	CharSequence getName();
	void setName(CharSequence name);
	
	CharSequence getType();
	void setType(CharSequence type);
	
	CharSequence getValue();
	void setValue(CharSequence value);
	
	CharSequence getValueType();
	void setValueType(CharSequence valueType);
}
