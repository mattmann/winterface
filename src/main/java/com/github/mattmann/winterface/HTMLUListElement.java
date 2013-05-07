package com.github.mattmann.winterface;

public interface HTMLUListElement extends HTMLElement {

	boolean isCompact();
	void setCompact(boolean compact);

	String getType();
	void setType(String type);
}
