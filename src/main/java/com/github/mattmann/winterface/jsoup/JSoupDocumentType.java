package com.github.mattmann.winterface.jsoup;

import com.github.mattmann.winterface.NamedNodeMap;
import org.w3c.dom.DocumentType;

public class JSoupDocumentType extends JSoupNode<org.jsoup.nodes.DocumentType> implements DocumentType {

	public JSoupDocumentType(org.jsoup.nodes.DocumentType documentType) {
		super(documentType);
	}

	public String getName() {
		throw new UnsupportedOperationException();
	}

	public NamedNodeMap getEntities() {
		throw new UnsupportedOperationException();
	}

	public NamedNodeMap getNotations() {
		throw new UnsupportedOperationException();
	}

	public String getPublicId() {
		throw new UnsupportedOperationException();
	}

	public String getSystemId() {
		throw new UnsupportedOperationException();
	}

	public String getInternalSubset() {
		throw new UnsupportedOperationException();
	}

	public JSoupDocument getOwnerDocument() {
		throw new UnsupportedOperationException();
	}
}
