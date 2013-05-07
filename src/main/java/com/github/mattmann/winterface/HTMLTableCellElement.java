package com.github.mattmann.winterface;

public interface HTMLTableCellElement extends HTMLElement {

	long getCellIndex();

	String getAbbr();
	void setAbbr(String abbr);

	String getAlign();
	void setAlign(String align);

	String getAxis();
	void setAxis(String axis);

	String getBgColor();
	void setBgColor(String bgColor);

	String getCh();
	void setCh(String ch);

	String getChOff();
	void setChOff(String chOff);

	long getColSpan();
	void setColSpan(long colSpan);

	String getHeaders();
	void setHeaders(String headers);

	String getHeight();
	void setHeight(String height);

	boolean isNoWrap();
	void setNoWrap(boolean noWrap);

	long getRowSpan();
	void setRowSpan(long rowSpan);

	String getScope();
	void setScope(String scope);

	String getVAlign();
	void setVAlign(String vAlign);

	String getWidth();
	void setWidth(String width);
}
