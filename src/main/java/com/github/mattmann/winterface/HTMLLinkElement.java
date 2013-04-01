package com.github.mattmann.winterface;

public interface HTMLLinkElement extends HTMLElement {
	
	boolean isDisabled();
	void setDisabled(boolean disabled);
	
	CharSequence getCharset();
	void setCharset(CharSequence charset);
	
	CharSequence getHref();
	void setHref(CharSequence href);
	
	CharSequence getHreflang();
	void setHreflang(CharSequence hreflang);
	
	CharSequence getMedia();
	void setMedia(CharSequence media);
	
	CharSequence getRel();
	void setRel(CharSequence rel);
	
	CharSequence getRev();
	void setRev(CharSequence rev);

	CharSequence getTarget();
	void setTarget(CharSequence target);
	
	CharSequence getType();
	void setType(CharSequence type);
}
