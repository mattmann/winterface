package com.github.mattmann.winterface;

public interface HTMLScriptElement extends HTMLElement {
	
	String getText();
	void setText(String text);
	
//	String getHtmlFor();
//	void setHtmlFor(String htmlFor);
	
//	String getEvent();
//	void setEvent(String event);
	
	String getCharset();
	void setCharset(String charset);
	
	boolean isDefer();
	void setDefer(boolean defer);
	
	String getSrc();
	void setSrc(String src);
	
	String getType();
	void setType(String type);
	
	boolean isAsync();
	void setAsync(boolean async);
}
