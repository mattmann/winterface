package com.github.mattmann.winterface;

public interface HTMLHRElement extends HTMLElement {

	String getAlign();
	void setAlign(String align);

	boolean isNoShade();
	void setNoShade(boolean noShade);
	
	String getSize();
	void setSize(String size);

	String getWidth();
	void setWidth(String width);
}
