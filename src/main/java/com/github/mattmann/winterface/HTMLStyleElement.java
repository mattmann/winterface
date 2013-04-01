package com.github.mattmann.winterface;

public interface HTMLStyleElement extends HTMLElement {

	boolean isDisabled();
	void setDisabled(boolean disabled);
	
	CharSequence getMedia();
	void setMedia(CharSequence media);
	
	CharSequence getType();
	void setType(CharSequence type);
}
