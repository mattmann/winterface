package com.github.mattmann.winterface;

public interface HTMLMetaElement extends HTMLElement {

	String getContent();
	void setContent(String content);

	String getHttpEquiv();
	void setHttpEquiv(String httpEquiv);

	String getName();
	void setName(String name);

	String getScheme();
	void setScheme(String scheme);
	
	String getCharset();
	void setCharset(String charset);
}
