package com.github.mattmann.winterface;

public interface HTMLFormElement extends HTMLElement {

	HTMLCollection getElements();

	int getLength();

	CharSequence getName();
	void setName(CharSequence name);

	CharSequence getAcceptCharset();
	void setAcceptCharset(CharSequence acceptCharset);

	CharSequence getAction();
	void setAction(CharSequence action);

	CharSequence getEnctype();
	void setEnctype(CharSequence enctype);

	CharSequence getMethod();
	void setMethod(CharSequence method);

	CharSequence getTarget();
	void setTarget(CharSequence target);

	void submit();
	void reset();
	
	EventListener getOnsubmit();
	void setOnsubmit(EventListener onsubmit);
	
	EventListener getOnreset();
	void setOnreset(EventListener onreset);
}
