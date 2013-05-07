package com.github.mattmann.winterface;

public interface HTMLButtonElement extends HTMLElement {

	HTMLFormElement getForm();
	
	String getAccessKey();
	void setAccessKey(String accessKey);
	
	boolean isDisabled();
	void setDisabled(boolean disabled);

	String getName();
	void setName(String name);

	long getTabIndex();
	void setTabIndex(long tabIndex);

	String getType();
	void setType(String type);

	String getValue();
	void setValue(String value);
}
