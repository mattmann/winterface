package com.github.mattmann.winterface;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLCollection;

public interface HTMLDocument extends Document, EventTarget {

	HTMLElement querySelector(String selectors);
	NodeList querySelectorAll(String selectors);

	String getTitle();
	void setTitle(String title);

	String getReferrer();
	
	String getDomain();
	
	String getURL();
	
	HTMLElement getBody();
	void setBody(HTMLElement body);

	HTMLCollection getImages();

	HTMLCollection getApplets();

	HTMLCollection getLinks();

	HTMLCollection getForms();

	HTMLCollection getAnchors();

	String getCookie();
	void setCookie(String cookie);

	void open();
	void close();
	void write(String text);
	void writeln(String text);
	Element getElementById(String elementId);
	NodeList getElementsByName(String elementName);
	
	Window getDefaultView();
}
