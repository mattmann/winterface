package com.github.mattmann.winterface;

public interface HTMLInputElement extends HTMLElement {

	CharSequence getDefaultValue();
	void setDefaultValue(CharSequence defaultValue);

	boolean isDefaultChecked();
	void setDefaultChecked(boolean defaultChecked);
	
	HTMLFormElement getForm();

	CharSequence getType();

	CharSequence getAccept();
	void setAccept(CharSequence accept);

	CharSequence getAccessKey();
	void setAccessKey(CharSequence accessKey);

	CharSequence getAlign();
	void setAlign(CharSequence align);

	CharSequence getAlt();
	void setAlt(CharSequence alt);

	boolean isChecked();
	void setChecked(boolean checked);

	boolean isDisabled();
	void setDisabled(boolean disabled);

	long getMaxLength();
	void setMaxLength(long maxLength);

	CharSequence getName();
	void setName(CharSequence name);

	boolean isReadOnly();
	void setReadOnly(boolean readOnly);

	CharSequence getSize();
	void setSize(CharSequence size);

	CharSequence getSrc();
	void setSrc(CharSequence src);

	long getTabIndex();
	void setTabIndex(long tabIndex);

	CharSequence getUseMap();
	void setUseMap(CharSequence useMap);

	CharSequence getValue();
	void setValue(CharSequence value);

	void blur();
	void focus();
	void select();
	void click();
}
