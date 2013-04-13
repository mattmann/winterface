package com.github.mattmann.winterface;

public interface HTMLScriptElement extends HTMLElement {
	
	CharSequence getText();
	void setText(CharSequence text);
	
//	CharSequence getHtmlFor();
//	void setHtmlFor(CharSequence htmlFor);
	
//	CharSequence getEvent();
//	void setEvent(CharSequence event);
	
	CharSequence getCharset();
	void setCharset(CharSequence charset);
	
	boolean isDefer();
	void setDefer(boolean defer);
	
	CharSequence getSrc();
	void setSrc(CharSequence src);
	
	CharSequence getType();
	void setType(CharSequence type);
	
	boolean isAsync();
	void setAsync(boolean async);
}
