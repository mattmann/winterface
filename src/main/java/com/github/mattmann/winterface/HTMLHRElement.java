package com.github.mattmann.winterface;

public interface HTMLHRElement extends HTMLElement {

	CharSequence getAlign();
	void setAlign(CharSequence align);

	boolean isNoShade();
	void setNoShade(boolean noShade);
	
	CharSequence getSize();
	void setSize(CharSequence size);

	CharSequence getWidth();
	void setWidth(CharSequence width);
}
