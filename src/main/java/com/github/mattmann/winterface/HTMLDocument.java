package com.github.mattmann.winterface;

public interface HTMLDocument extends Document, EventTarget {

	HTMLElement querySelector(CharSequence selectors);
	NodeList querySelectorAll(CharSequence selectors);

	CharSequence getTitle();
	void setTitle(CharSequence title);

	CharSequence getReferrer();
	
	CharSequence getDomain();
	
	CharSequence getURL();
	
	HTMLElement getBody();
	void setBody(HTMLElement body);

	HTMLCollection getImages();

	HTMLCollection getApplets();

	HTMLCollection getLinks();

	HTMLCollection getForms();

	HTMLCollection getAnchors();

	CharSequence getCookie();
	void setCookie(CharSequence cookie);

	void open();
	void close();
	void write(CharSequence text);
	void writeln(CharSequence text);
	Element getElementById(CharSequence elementId);
	NodeList getElementsByName(CharSequence elementName);
	
	Window getDefaultView();
}
