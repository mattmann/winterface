package com.github.mattmann.winterface;

public interface HTMLLinkElement extends HTMLElement {
	
	boolean isDisabled();
	void setDisabled(boolean disabled);
	
	String getCharset();
	void setCharset(String charset);
	
	String getHref();
	void setHref(String href);
	
	String getHreflang();
	void setHreflang(String hreflang);
	
	String getMedia();
	void setMedia(String media);
	
	String getRel();
	void setRel(String rel);
	
	String getRev();
	void setRev(String rev);

	String getTarget();
	void setTarget(String target);
	
	String getType();
	void setType(String type);
}
