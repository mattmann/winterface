package com.github.mattmann.winterface;

public interface HTMLAreaElement extends HTMLElement {

	CharSequence getAccessKey();
	void setAccessKey(CharSequence accessKey);

	CharSequence getAlt();
	void setAlt(CharSequence alt);

	CharSequence getCoords();
	void setCoords(CharSequence coords);

	CharSequence getHref();
	void setHref(CharSequence href);

	boolean isNoHref();
	void setNoHref(boolean noHref);

	CharSequence getShape();
	void setShape(CharSequence shape);

	long getTabIndex();
	void setTabIndex(long tabIndex);

	CharSequence getTarget();
	void setTarget(CharSequence target);
}