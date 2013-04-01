package com.github.mattmann.winterface;

public interface HTMLTableRowElement extends HTMLElement {

	long getRowIndex();
	long getSectionRowIndex();
	HTMLCollection getCells();
	
	CharSequence getAlign();
	void setAlign(CharSequence align);

	CharSequence getBgColor();
	void setBgColor(CharSequence bgColor);

	CharSequence getCh();
	void setCh(CharSequence ch);

	CharSequence getChOff();
	void setChOff(CharSequence chOff);

	CharSequence getVAlign();
	void setVAlign(CharSequence vAlign);

	HTMLElement insertCell(long index) throws DOMException;

	void deleteCell(long index) throws DOMException;
}
