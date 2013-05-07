package com.github.mattmann.winterface;

public interface HTMLTableSectionElement extends HTMLElement {

	String getAlign();
	void setAlign(String align);
	
	String getCh();
	void setCh(String ch);
	
	String getChOff();
	void setChOff(String chOff);

	String getVAlign();
	void setVAlign(String vAlign);
	
	HTMLCollection getRows();

	HTMLElement insertRow(long index) throws DOMException;

	void deleteRow(long index) throws DOMException;
}
