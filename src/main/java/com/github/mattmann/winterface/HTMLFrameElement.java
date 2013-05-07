package com.github.mattmann.winterface;

public interface HTMLFrameElement extends HTMLElement {

	String getFrameBorder();
	void setFrameBorder(String frameBorder);

	String getLongDesc();
	void setLongDesc(String longDesc);

	String getMarginHeight();
	void setMarginHeight(String marginHeight);

	String getMarginWidth();
	void setMarginWidth(String marginWidth);

	String getName();
	void setName(String name);

	boolean isNoResize();
	void setNoResize(boolean noResize);

	String getScrolling();
	void setScrolling(String scrolling);

	String getSrc();
	void setSrc(String src);
}
