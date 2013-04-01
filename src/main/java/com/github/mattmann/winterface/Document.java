package com.github.mattmann.winterface;

public interface Document extends DocumentEvent, Node {
	Attr createAttribute(CharSequence name) throws DOMException;
	Attr createAttributeNS(CharSequence namespaceURI, CharSequence qualifiedName) throws DOMException;
	CDATASection createCDATASection(CharSequence data) throws DOMException;
	Comment createComment(CharSequence data);
	DocumentFragment createDocumentFragment();
	DocumentType getDoctype();
	DOMImplementation getImplementation();
	Element createElement(CharSequence tagName) throws DOMException;
	Element createElementNS(CharSequence namespaceURI, CharSequence qualifiedName) throws DOMException;
	Element getDocumentElement();
	Element getElementById(CharSequence elementId);
	EntityReference createEntityReference(CharSequence name) throws DOMException;
	Node importNode(Node importedNode, boolean deep) throws DOMException;
	NodeList getElementsByTagName(CharSequence tagname);
	NodeList getElementsByTagNameNS(CharSequence namespaceURI, CharSequence localName);
	ProcessingInstruction createProcessingInstruction(CharSequence target, CharSequence data) throws DOMException;
	Text createTextNode(CharSequence data);
}
