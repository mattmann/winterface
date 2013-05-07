package com.github.mattmann.winterface;

public interface HTMLStyleElement extends HTMLElement {

	boolean isDisabled();
	void setDisabled(boolean disabled);
	
	String getMedia();
	void setMedia(String media);
	
	String getType();
	void setType(String type);
}
