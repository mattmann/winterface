package com.github.mattmann.winterface;

public interface DOMImplementation {
	boolean hasFeature(String feature, String version);
	DocumentType createDocumentType(String qualifiedName, String publicId, String systemId) throws DOMException;
	Document createDocument(String namespaceURI, String qualifiedName, DocumentType doctype) throws DOMException;
}