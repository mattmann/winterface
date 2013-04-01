package com.github.mattmann.winterface;

public interface HTMLTableCellElement extends HTMLElement {

	long getCellIndex();

	CharSequence getAbbr();
	void setAbbr(CharSequence abbr);

	CharSequence getAlign();
	void setAlign(CharSequence align);

	CharSequence getAxis();
	void setAxis(CharSequence axis);

	CharSequence getBgColor();
	void setBgColor(CharSequence bgColor);

	CharSequence getCh();
	void setCh(CharSequence ch);

	CharSequence getChOff();
	void setChOff(CharSequence chOff);

	long getColSpan();
	void setColSpan(long colSpan);

	CharSequence getHeaders();
	void setHeaders(CharSequence headers);

	CharSequence getHeight();
	void setHeight(CharSequence height);

	boolean isNoWrap();
	void setNoWrap(boolean noWrap);

	long getRowSpan();
	void setRowSpan(long rowSpan);

	CharSequence getScope();
	void setScope(CharSequence scope);

	CharSequence getVAlign();
	void setVAlign(CharSequence vAlign);

	CharSequence getWidth();
	void setWidth(CharSequence width);
}
