package com.github.mattmann.winterface;

public interface HTMLFormElement extends HTMLElement {

	HTMLCollection getElements();

	int getLength();

	String getName();
	void setName(String name);

	String getAcceptCharset();
	void setAcceptCharset(String acceptCharset);

	String getAction();
	void setAction(String action);

	String getEnctype();
	void setEnctype(String enctype);

	String getMethod();
	void setMethod(String method);

	String getTarget();
	void setTarget(String target);

	void submit();
	void reset();
	
	EventListener getOnsubmit();
	void setOnsubmit(EventListener onsubmit);
	
	EventListener getOnreset();
	void setOnreset(EventListener onreset);
}
