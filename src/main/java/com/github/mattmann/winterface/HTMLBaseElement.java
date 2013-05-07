package com.github.mattmann.winterface;

public interface HTMLBaseElement extends HTMLElement {

	String getHref();
	void setHref(String href);
	
	String getTarget();
	void setTarget(String target);
}
