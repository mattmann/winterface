package com.github.mattmann.winterface;

public interface HTMLElement extends Element, EventTarget, GlobalEventHandlers, NodeEventHandlers, TouchEventHandlers {

	CharSequence getId();
	void setId(CharSequence id);

	CharSequence getTitle();
	void setTitle(CharSequence title);

	CharSequence getLang();
	void setLang(CharSequence lang);
	
	CharSequence getDir();
	void setDir(CharSequence dir);
	
	CharSequence getClassName();
	void setClassName(CharSequence className);
	
	CharSequence getInnerHTML();
	void setInnerHTML(CharSequence innerHTML);

	CharSequence getOuterHTML();
	void setOuterHTML(CharSequence outerHTML);

	HTMLElement querySelector(CharSequence selectors);
	NodeList querySelectorAll(CharSequence selectors);
}