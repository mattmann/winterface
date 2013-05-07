package com.github.mattmann.winterface.jsoup;

import com.github.mattmann.winterface.event.EventDispatcher;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;

public class JSoupDOMImplementation implements DOMImplementation {

	public boolean hasFeature(String feature, String version) {
		throw new UnsupportedOperationException();
	}

	public DocumentType createDocumentType(String qualifiedName, String publicId, String systemId) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Document createDocument(String namespaceURI, String qualifiedName, DocumentType doctype) throws DOMException {
		org.jsoup.nodes.Document document = new org.jsoup.nodes.Document(namespaceURI.toString());
		EventDispatcher eventDispatcher = new JSoupEventDispatcher();
		return new JSoupDocument(document, eventDispatcher);
	}

	public Object getFeature(String feature, String version) {
		throw new UnsupportedOperationException();
	}
}