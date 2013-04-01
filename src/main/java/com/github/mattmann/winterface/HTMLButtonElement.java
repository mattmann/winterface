package com.github.mattmann.winterface;

public interface HTMLButtonElement extends HTMLElement {

	HTMLFormElement getForm();
	
	CharSequence getAccessKey();
	void setAccessKey(CharSequence accessKey);
	
	boolean isDisabled();
	void setDisabled(boolean disabled);

	CharSequence getName();
	void setName(CharSequence name);

	long getTabIndex();
	void setTabIndex(long tabIndex);

	CharSequence getType();
	void setType(CharSequence type);

	CharSequence getValue();
	void setValue(CharSequence value);
}
