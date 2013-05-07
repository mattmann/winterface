package com.github.mattmann.winterface;

public interface HTMLElement extends Element, EventTarget, GlobalEventHandlers, NodeEventHandlers, TouchEventHandlers {

	String getId();
	void setId(String id);

	String getTitle();
	void setTitle(String title);

	String getLang();
	void setLang(String lang);
	
	String getDir();
	void setDir(String dir);
	
	String getClassName();
	void setClassName(String className);
	
	String getInnerHTML();
	void setInnerHTML(String innerHTML);

	String getOuterHTML();
	void setOuterHTML(String outerHTML);

	HTMLElement querySelector(String selectors);
	NodeList querySelectorAll(String selectors);
}