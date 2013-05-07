package com.github.mattmann.winterface;

public interface HTMLInputElement extends HTMLElement {

	String getDefaultValue();
	void setDefaultValue(String defaultValue);

	boolean isDefaultChecked();
	void setDefaultChecked(boolean defaultChecked);
	
	HTMLFormElement getForm();

	String getType();

	String getAccept();
	void setAccept(String accept);

	String getAccessKey();
	void setAccessKey(String accessKey);

	String getAlign();
	void setAlign(String align);

	String getAlt();
	void setAlt(String alt);

	boolean isChecked();
	void setChecked(boolean checked);

	boolean isDisabled();
	void setDisabled(boolean disabled);

	long getMaxLength();
	void setMaxLength(long maxLength);

	String getName();
	void setName(String name);

	boolean isReadOnly();
	void setReadOnly(boolean readOnly);

	String getSize();
	void setSize(String size);

	String getSrc();
	void setSrc(String src);

	long getTabIndex();
	void setTabIndex(long tabIndex);

	String getUseMap();
	void setUseMap(String useMap);

	String getValue();
	void setValue(String value);

	void blur();
	void focus();
	void select();
	void click();
}
