package com.github.mattmann.winterface;

import org.w3c.dom.DOMException;

public interface HTMLTableRowElement extends HTMLElement {

	long getRowIndex();
	long getSectionRowIndex();
	HTMLCollection getCells();
	
	String getAlign();
	void setAlign(String align);

	String getBgColor();
	void setBgColor(String bgColor);

	String getCh();
	void setCh(String ch);

	String getChOff();
	void setChOff(String chOff);

	String getVAlign();
	void setVAlign(String vAlign);

	HTMLElement insertCell(long index) throws DOMException;

	void deleteCell(long index) throws DOMException;
}
