package com.github.mattmann.winterface;

public interface HTMLBaseFontElement extends HTMLElement {

	CharSequence getColor();
	void setColor(CharSequence color);
	
	CharSequence getFace();
	void setFace(CharSequence face);
	
	CharSequence getSize();
	void setSize(CharSequence size);
}
