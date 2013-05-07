package com.github.mattmann.winterface;

public interface HTMLObjectElement extends HTMLElement {
	
	HTMLFormElement getForm();

	String getCode();
	void setCode(String code);

	String getAlign();
	void setAlign(String align);

	String getArchive();
	void setArchive(String archive);

	String getBorder();
	void setBorder(String border);

	String getCodeBase();
	void setCodeBase(String codeBase);

	String getCodeType();
	void setCodeType(String codeType);

	String getData();
	void setData(String data);

	boolean isDeclare();
	void setDeclare(boolean declare);

	String getHeight();
	void setHeight(String height);

	String getHspace();
	void setHspace(String hspace);

	String getName();
	void setName(String name);

	String getStandby();
	void setStandby(String standby);

	long getTabIndex();
	void setTabIndex(long tabIndex);

	String getType();
	void setType(String type);

	String getUseMap();
	void setUseMap(String useMap);

	String getVspace();
	void setVspace(String vspace);

	String getWidth();
	void setWidth(String width);
}
