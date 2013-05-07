package com.github.mattmann.winterface;

public interface HTMLAnchorElement extends HTMLElement {

	String getAccessKey();
	void setAccessKey(String accessKey);
	
	String getCharset();
	void setCharset(String charset);

	String getCoords();
	void setCoords(String coords);

	String getHref();
	void setHref(String href);

	String getHreflang();
	void setHreflang(String hreflang);
	
	String getMedia();
	void setMedia(String media);

	String getName();
	void setName(String name);

	String getRel();
	void setRel(String rel);

	String getRev();
	void setRev(String rev);

	String getShape();
	void setShape(String shape);

	int getTabIndex();
	void setTabIndex(int tabIndex);

	String getTarget();
	void setTarget(String target);

	String getType();
	void setType(String type);

	void click();
    void blur();
    void focus();
}
