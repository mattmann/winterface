package com.github.mattmann.winterface;

public interface HTMLTableColElement extends HTMLElement {

	String getAlign();
	void setAlign(String align);
	
	String getCh();
	void setCh(String ch);
	
	String getChOff();
	void setChOff(String chOff);
	
	long getSpan();
	void setSpan(long span);
	
	String getVAlign();
	void setVAlign(String vAlign);
	
	String getWidth();
	void setWidth(String width);
}
