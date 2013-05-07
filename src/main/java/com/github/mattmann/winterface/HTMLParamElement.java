package com.github.mattmann.winterface;

public interface HTMLParamElement extends HTMLElement {
	
	String getName();
	void setName(String name);
	
	String getType();
	void setType(String type);
	
	String getValue();
	void setValue(String value);
	
	String getValueType();
	void setValueType(String valueType);
}
