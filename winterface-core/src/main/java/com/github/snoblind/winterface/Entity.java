package com.github.snoblind.winterface;

import org.w3c.dom.Node;

public interface Entity extends Node {
	String getPublicId();
	String getSystemId();
	String getNotationName();
}
