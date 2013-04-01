package com.github.mattmann.winterface.jsoup;

import com.github.mattmann.winterface.DOMException;
import com.github.mattmann.winterface.DOMImplementation;
import com.github.mattmann.winterface.Document;
import com.github.mattmann.winterface.DocumentType;
import com.github.mattmann.winterface.event.EventDispatcher;

public class JSoupDOMImplementation implements DOMImplementation {

	public boolean hasFeature(CharSequence feature, CharSequence version) {
		throw new UnsupportedOperationException();
	}

	public DocumentType createDocumentType(CharSequence qualifiedName, CharSequence publicId, CharSequence systemId) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Document createDocument(CharSequence namespaceURI, CharSequence qualifiedName, DocumentType doctype) throws DOMException {
		org.jsoup.nodes.Document document = new org.jsoup.nodes.Document(namespaceURI.toString());
		EventDispatcher eventDispatcher = new JSoupEventDispatcher();
		return new JSoupDocument(document, eventDispatcher);
	}
}