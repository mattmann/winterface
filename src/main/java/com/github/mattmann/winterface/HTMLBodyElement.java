package com.github.mattmann.winterface;

public interface HTMLBodyElement extends HTMLElement {

	CharSequence getALink();
	void setALink(CharSequence aLink);
	
	CharSequence getBackground();
	void setBackground(CharSequence background);
	
	CharSequence getBgColor();
	void setBgColor(CharSequence bgColor);
	
	CharSequence getLink();
	void setLink(CharSequence link);
	
	CharSequence getText();
	void setText(CharSequence text);
	
	CharSequence getVLink();
	void setVLink(CharSequence vLink);
}
