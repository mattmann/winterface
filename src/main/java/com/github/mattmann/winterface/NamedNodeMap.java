package com.github.mattmann.winterface;

public interface NamedNodeMap {
	long getLength();
	Node getNamedItem(CharSequence name);
	Node getNamedItemNS(CharSequence namespaceURI, CharSequence localName);
	Node item(long index);
	Node removeNamedItem(CharSequence name) throws DOMException;
	Node removeNamedItemNS(CharSequence namespaceURI, CharSequence localName) throws DOMException;
	Node setNamedItem(Node arg) throws DOMException;
	Node setNamedItemNS(Node arg) throws DOMException;
}
