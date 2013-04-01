package com.github.mattmann.winterface;

public interface HTMLFrameElement extends HTMLElement {

	CharSequence getFrameBorder();
	void setFrameBorder(CharSequence frameBorder);

	CharSequence getLongDesc();
	void setLongDesc(CharSequence longDesc);

	CharSequence getMarginHeight();
	void setMarginHeight(CharSequence marginHeight);

	CharSequence getMarginWidth();
	void setMarginWidth(CharSequence marginWidth);

	CharSequence getName();
	void setName(CharSequence name);

	boolean isNoResize();
	void setNoResize(boolean noResize);

	CharSequence getScrolling();
	void setScrolling(CharSequence scrolling);

	CharSequence getSrc();
	void setSrc(CharSequence src);
}
