package com.github.mattmann.winterface;

public interface HTMLObjectElement extends HTMLElement {
	
	HTMLFormElement getForm();

	CharSequence getCode();
	void setCode(CharSequence code);

	CharSequence getAlign();
	void setAlign(CharSequence align);

	CharSequence getArchive();
	void setArchive(CharSequence archive);

	CharSequence getBorder();
	void setBorder(CharSequence border);

	CharSequence getCodeBase();
	void setCodeBase(CharSequence codeBase);

	CharSequence getCodeType();
	void setCodeType(CharSequence codeType);

	CharSequence getData();
	void setData(CharSequence data);

	boolean isDeclare();
	void setDeclare(boolean declare);

	CharSequence getHeight();
	void setHeight(CharSequence height);

	CharSequence getHspace();
	void setHspace(CharSequence hspace);

	CharSequence getName();
	void setName(CharSequence name);

	CharSequence getStandby();
	void setStandby(CharSequence standby);

	long getTabIndex();
	void setTabIndex(long tabIndex);

	CharSequence getType();
	void setType(CharSequence type);

	CharSequence getUseMap();
	void setUseMap(CharSequence useMap);

	CharSequence getVspace();
	void setVspace(CharSequence vspace);

	CharSequence getWidth();
	void setWidth(CharSequence width);
}
