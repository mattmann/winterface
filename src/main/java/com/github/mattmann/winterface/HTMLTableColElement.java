package com.github.mattmann.winterface;

public interface HTMLTableColElement extends HTMLElement {

	CharSequence getAlign();
	void setAlign(CharSequence align);
	
	CharSequence getCh();
	void setCh(CharSequence ch);
	
	CharSequence getChOff();
	void setChOff(CharSequence chOff);
	
	long getSpan();
	void setSpan(long span);
	
	CharSequence getVAlign();
	void setVAlign(CharSequence vAlign);
	
	CharSequence getWidth();
	void setWidth(CharSequence width);
}
