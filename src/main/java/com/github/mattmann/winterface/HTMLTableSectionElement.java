package com.github.mattmann.winterface;

public interface HTMLTableSectionElement extends HTMLElement {

	CharSequence getAlign();
	void setAlign(CharSequence align);
	
	CharSequence getCh();
	void setCh(CharSequence ch);
	
	CharSequence getChOff();
	void setChOff(CharSequence chOff);

	CharSequence getVAlign();
	void setVAlign(CharSequence vAlign);
	
	HTMLCollection getRows();

	HTMLElement insertRow(long index) throws DOMException;

	void deleteRow(long index) throws DOMException;
}
