package com.github.mattmann.winterface;

public interface DOMImplementation {
	boolean hasFeature(CharSequence feature, CharSequence version);
	DocumentType createDocumentType(CharSequence qualifiedName, CharSequence publicId, CharSequence systemId) throws DOMException;
	Document createDocument(CharSequence namespaceURI, CharSequence qualifiedName, DocumentType doctype) throws DOMException;
}