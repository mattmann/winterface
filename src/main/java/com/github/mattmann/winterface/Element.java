package com.github.mattmann.winterface;

public interface Element extends Node {
	Attr getAttributeNode(CharSequence name);
	Attr getAttributeNodeNS(CharSequence namespaceURI, CharSequence localName);
	Attr removeAttributeNode(Attr oldAttr) throws DOMException;
	Attr setAttributeNode(Attr newAttr) throws DOMException;
	Attr setAttributeNodeNS(Attr newAttr) throws DOMException;
	boolean hasAttribute(CharSequence name);
	boolean hasAttributeNS(CharSequence namespaceURI, CharSequence localName);
	CharSequence getAttribute(CharSequence name);
	CharSequence getAttributeNS(CharSequence namespaceURI, CharSequence localName);
	CharSequence getTagName();
	NodeList getElementsByTagName(CharSequence name);
	NodeList getElementsByTagNameNS(CharSequence namespaceURI, CharSequence localName);
	void removeAttribute(CharSequence name) throws DOMException;
	void removeAttributeNS(CharSequence namespaceURI, CharSequence localName) throws DOMException;
	void setAttribute(CharSequence name, CharSequence value) throws DOMException;
	void setAttributeNS(CharSequence namespaceURI, CharSequence qualifiedName, CharSequence value) throws DOMException;
}
