package com.github.mattmann.winterface;

public interface HTMLBaseElement extends HTMLElement {

	CharSequence getHref();
	void setHref(CharSequence href);
	
	CharSequence getTarget();
	void setTarget(CharSequence target);
}
