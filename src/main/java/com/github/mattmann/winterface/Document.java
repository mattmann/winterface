package com.github.mattmann.winterface;

public interface Document extends DocumentEvent, Node {
	Attr createAttribute(String name) throws DOMException;
	Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException;
	CDATASection createCDATASection(String data) throws DOMException;
	Comment createComment(String data);
	DocumentFragment createDocumentFragment();
	DocumentType getDoctype();
	DOMImplementation getImplementation();
	Element createElement(String tagName) throws DOMException;
	Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException;
	Element getDocumentElement();
	Element getElementById(String elementId);
	EntityReference createEntityReference(String name) throws DOMException;
	Node importNode(Node importedNode, boolean deep) throws DOMException;
	NodeList getElementsByTagName(String tagname);
	NodeList getElementsByTagNameNS(String namespaceURI, String localName);
	ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException;
	Text createTextNode(String data);
}
