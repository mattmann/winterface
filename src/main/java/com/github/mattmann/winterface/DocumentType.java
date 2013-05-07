package com.github.mattmann.winterface;

public interface DocumentType extends Node {
	String getName();
	NamedNodeMap getEntities();
	NamedNodeMap getNotations();
	String getPublicId();
	String getSystemId();
	String getInternalSubset();	
}
