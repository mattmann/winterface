package com.github.mattmann.winterface;

public interface DocumentType extends Node {
	CharSequence getName();
	NamedNodeMap getEntities();
	NamedNodeMap getNotations();
	CharSequence getPublicId();
	CharSequence getSystemId();
	CharSequence getInternalSubset();	
}
