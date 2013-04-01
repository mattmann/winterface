package com.github.mattmann.winterface;

public interface HTMLSelectElement extends HTMLElement {

	CharSequence getType();

	long getSelectedIndex();
	void setSelectedIndex(long selectedIndex);
	
	CharSequence getValue();
	void setValue(CharSequence value);
	
	long getLength();
	
	HTMLFormElement getForm();
	
	HTMLCollection getOptions();

	boolean isDisabled();
	void setDisabled(boolean disabled);

	boolean isMultiple();
	void setMultiple(boolean multiple);
	
	CharSequence getName();
	void setName(CharSequence name);

	long getSize();
	void setSize(long size);

	long getTabIndex();
	void setTabIndex(long tabIndex);

	void add(HTMLElement element, HTMLElement before) throws DOMException;

	void remove(long index);

	void blur();

	void focus();
}
