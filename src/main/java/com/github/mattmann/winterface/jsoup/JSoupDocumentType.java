package com.github.mattmann.winterface.jsoup;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.DocumentType;

public class JSoupDocumentType extends JSoupNode<org.jsoup.nodes.DocumentType> implements DocumentType {

	private final JSoupDocument ownerDocument;

	public JSoupDocumentType(org.jsoup.nodes.DocumentType documentType, JSoupDocument ownerDocument) {
		super(documentType);
		this.ownerDocument = ownerDocument;
	}

	public short getNodeType() {
		return DOCUMENT_TYPE_NODE;
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
		return ownerDocument;
	}
}
