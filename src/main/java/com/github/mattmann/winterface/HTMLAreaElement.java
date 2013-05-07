package com.github.mattmann.winterface;

public interface HTMLAreaElement extends HTMLElement {

	String getAccessKey();
	void setAccessKey(String accessKey);

	String getAlt();
	void setAlt(String alt);

	String getCoords();
	void setCoords(String coords);

	String getHref();
	void setHref(String href);

	boolean isNoHref();
	void setNoHref(boolean noHref);

	String getShape();
	void setShape(String shape);

	long getTabIndex();
	void setTabIndex(long tabIndex);

	String getTarget();
	void setTarget(String target);
}