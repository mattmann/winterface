package com.github.mattmann.winterface;

public interface HTMLOptionElement extends HTMLElement {
	
	HTMLFormElement getForm();

	boolean isDefaultSelected();
	void setDefaultSelected(boolean defaultSelected);
	
	String getText();
	
	long getIndex();

	boolean isDisabled();
	void setDisabled(boolean disabled);

	String getLabel();
	void setLabel(String label);

	boolean isSelected();
	void setSelected(boolean selected);

	String getValue();
	void setValue(String value);
}
