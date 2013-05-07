package com.github.mattmann.winterface;

public interface Element extends Node {
	Attr getAttributeNode(String name);
	Attr getAttributeNodeNS(String namespaceURI, String localName);
	Attr removeAttributeNode(Attr oldAttr) throws DOMException;
	Attr setAttributeNode(Attr newAttr) throws DOMException;
	Attr setAttributeNodeNS(Attr newAttr) throws DOMException;
	boolean hasAttribute(String name);
	boolean hasAttributeNS(String namespaceURI, String localName);
	String getAttribute(String name);
	String getAttributeNS(String namespaceURI, String localName);
	String getTagName();
	NodeList getElementsByTagName(String name);
	NodeList getElementsByTagNameNS(String namespaceURI, String localName);
	void removeAttribute(String name) throws DOMException;
	void removeAttributeNS(String namespaceURI, String localName) throws DOMException;
	void setAttribute(String name, String value) throws DOMException;
	void setAttributeNS(String namespaceURI, String qualifiedName, String value) throws DOMException;
}
