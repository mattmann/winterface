package com.github.mattmann.winterface;

public interface NamedNodeMap {
	long getLength();
	Node getNamedItem(String name);
	Node getNamedItemNS(String namespaceURI, String localName);
	Node item(long index);
	Node removeNamedItem(String name) throws DOMException;
	Node removeNamedItemNS(String namespaceURI, String localName) throws DOMException;
	Node setNamedItem(Node arg) throws DOMException;
	Node setNamedItemNS(Node arg) throws DOMException;
}
