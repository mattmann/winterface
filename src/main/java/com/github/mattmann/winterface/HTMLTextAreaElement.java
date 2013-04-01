package com.github.mattmann.winterface;

public interface HTMLTextAreaElement extends HTMLElement {

	CharSequence getDefaultValue();
	void setDefaultValue(CharSequence defaultValue);
	
	HTMLFormElement getForm();

	CharSequence getAccessKey();
	void setAccessKey(CharSequence accessKey);

	long getCols();
	void setCols(long cols);

	boolean isDisabled();
	void setDisabled(boolean disabled);

	CharSequence getName();
	void setName(CharSequence name);

	boolean isReadOnly();
	void setReadOnly(boolean readOnly);

	long getRows();
	void setRows(long rows);

	long getTabIndex();
	void setTabIndex(long tabIndex);

	CharSequence getType();

	CharSequence getValue();
	void setValue(CharSequence value);

	void blur();
	void focus();
	void select();
}
