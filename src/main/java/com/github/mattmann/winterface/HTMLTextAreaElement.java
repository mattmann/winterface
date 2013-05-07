package com.github.mattmann.winterface;

public interface HTMLTextAreaElement extends HTMLElement {

	String getDefaultValue();
	void setDefaultValue(String defaultValue);
	
	HTMLFormElement getForm();

	String getAccessKey();
	void setAccessKey(String accessKey);

	long getCols();
	void setCols(long cols);

	boolean isDisabled();
	void setDisabled(boolean disabled);

	String getName();
	void setName(String name);

	boolean isReadOnly();
	void setReadOnly(boolean readOnly);

	long getRows();
	void setRows(long rows);

	long getTabIndex();
	void setTabIndex(long tabIndex);

	String getType();

	String getValue();
	void setValue(String value);

	void blur();
	void focus();
	void select();
}
