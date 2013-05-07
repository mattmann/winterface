package com.github.mattmann.winterface;

import org.w3c.dom.DOMException;
import org.w3c.dom.html.HTMLCollection;

public interface HTMLSelectElement extends HTMLElement {

	String getType();

	int getSelectedIndex();
	void setSelectedIndex(int selectedIndex);
	
	String getValue();
	void setValue(String value);
	
	long getLength();
	
	HTMLFormElement getForm();
	
	HTMLCollection getOptions();

	boolean isDisabled();
	void setDisabled(boolean disabled);

	boolean isMultiple();
	void setMultiple(boolean multiple);
	
	String getName();
	void setName(String name);

	long getSize();
	void setSize(long size);

	long getTabIndex();
	void setTabIndex(long tabIndex);

	void add(HTMLElement element, HTMLElement before) throws DOMException;

	void remove(long index);

	void blur();

	void focus();
}
