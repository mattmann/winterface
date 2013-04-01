package com.github.mattmann.winterface;

public interface HTMLAnchorElement extends HTMLElement {

	CharSequence getAccessKey();
	void setAccessKey(CharSequence accessKey);
	
	CharSequence getCharset();
	void setCharset(CharSequence charset);

	CharSequence getCoords();
	void setCoords(CharSequence coords);

	CharSequence getHref();
	void setHref(CharSequence href);

	CharSequence getHreflang();
	void setHreflang(CharSequence hreflang);
	
	CharSequence getMedia();
	void setMedia(CharSequence media);

	CharSequence getName();
	void setName(CharSequence name);

	CharSequence getRel();
	void setRel(CharSequence rel);

	CharSequence getRev();
	void setRev(CharSequence rev);

	CharSequence getShape();
	void setShape(CharSequence shape);

	int getTabIndex();
	void setTabIndex(int tabIndex);

	CharSequence getTarget();
	void setTarget(CharSequence target);

	CharSequence getType();
	void setType(CharSequence type);

	void click();
    void blur();
    void focus();
}
