package com.github.mattmann.winterface;

import org.w3c.dom.Node;

public interface Notation extends Node {
	String getPublicId();
	String getSystemId();
}
