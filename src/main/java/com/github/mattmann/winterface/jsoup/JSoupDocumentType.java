package com.github.mattmann.winterface.jsoup;

import com.github.mattmann.winterface.DocumentType;
import com.github.mattmann.winterface.NamedNodeMap;

public class JSoupDocumentType extends JSoupNode<org.jsoup.nodes.DocumentType> implements DocumentType {

	public JSoupDocumentType(org.jsoup.nodes.DocumentType documentType) {
		super(documentType);
	}

	public CharSequence getName() {
		throw new UnsupportedOperationException();
	}

	public NamedNodeMap getEntities() {
		throw new UnsupportedOperationException();
	}

	public NamedNodeMap getNotations() {
		throw new UnsupportedOperationException();
	}

	public CharSequence getPublicId() {
		throw new UnsupportedOperationException();
	}

	public CharSequence getSystemId() {
		throw new UnsupportedOperationException();
	}

	public CharSequence getInternalSubset() {
		throw new UnsupportedOperationException();
	}

	public JSoupDocument getOwnerDocument() {
		throw new UnsupportedOperationException();
	}
}
