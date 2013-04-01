package com.github.mattmann.winterface;

public interface HTMLCollection {
	int getLength();
    Node item(int index);
    Node namedItem(CharSequence name);
}
