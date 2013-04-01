package com.github.mattmann.winterface;

public interface HTMLIFrameElement extends HTMLElement {

	CharSequence getAlign();
	void setAlign(CharSequence align);
	
	CharSequence getFrameBorder();
	void setFrameBorder(CharSequence frameBorder);

	CharSequence getHeight();
	void setHeight(CharSequence height);
	
	CharSequence getLongDesc();
	void setLongDesc(CharSequence longDesc);

	CharSequence getMarginHeight();
	void setMarginHeight(CharSequence marginHeight);
	
	CharSequence getMarginWidth();
	void setMarginWidth(CharSequence marginWidth);
	
	CharSequence getName();
	void setName(CharSequence name);
	
	CharSequence getScrolling();
	void setScrolling(CharSequence scrolling);
	
	CharSequence getSrc();
	void setSrc(CharSequence src);

	CharSequence getWidth();
	void setWidth(CharSequence width);
}
