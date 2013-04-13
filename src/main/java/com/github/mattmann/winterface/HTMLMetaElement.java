package com.github.mattmann.winterface;

public interface HTMLMetaElement extends HTMLElement {
	
	CharSequence getContent();
	void setContent(CharSequence content);

	CharSequence getHttpEquiv();
	void setHttpEquiv(CharSequence httpEquiv);

	CharSequence getName();
	void setName(CharSequence name);

	CharSequence getScheme();
	void setScheme(CharSequence scheme);
	
	CharSequence getCharset();
	void setCharset(CharSequence charset);
}
