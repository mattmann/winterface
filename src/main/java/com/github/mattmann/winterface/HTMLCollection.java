package com.github.mattmann.winterface;

import org.w3c.dom.Node;

public interface HTMLCollection {
	int getLength();
    Node item(int index);
    Node namedItem(String name);
}
