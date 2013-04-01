package com.github.mattmann.winterface;

public interface HTMLUListElement extends HTMLElement {

	boolean isCompact();
	void setCompact(boolean compact);

	CharSequence getType();
	void setType(CharSequence type);
}
