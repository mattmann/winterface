package com.github.mattmann.winterface;

public interface HTMLOptionElement extends HTMLElement {
	
	HTMLFormElement getForm();

	boolean isDefaultSelected();
	void setDefaultSelected(boolean defaultSelected);
	
	CharSequence getText();
	
	long getIndex();

	boolean isDisabled();
	void setDisabled(boolean disabled);

	CharSequence getLabel();
	void setLabel(CharSequence label);

	boolean isSelected();
	void setSelected(boolean selected);

	CharSequence getValue();
	void setValue(CharSequence value);
}
