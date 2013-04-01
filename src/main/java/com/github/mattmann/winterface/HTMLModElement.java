package com.github.mattmann.winterface;

public interface HTMLModElement extends HTMLElement {
	
	CharSequence getCite();
	void setCite(CharSequence cite);
	
	CharSequence getDateTime();
	void setDateTime(CharSequence dateTime);
}
