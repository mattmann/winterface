package com.github.snoblind.winterface.jsoup;

import org.w3c.dom.Comment;
import org.w3c.dom.DOMException;
import static org.apache.commons.lang.Validate.notNull;

public class JSoupComment extends JSoupNode<org.jsoup.nodes.Comment> implements Comment {

	private final JSoupDocument ownerDocument;

	public JSoupComment(org.jsoup.nodes.Comment node, JSoupDocument ownerDocument) {
		super(node);
		notNull(this.ownerDocument = ownerDocument);
	}

	public JSoupDocument getOwnerDocument() {
		return ownerDocument;
	}

	public short getNodeType() {
		return COMMENT_NODE;
	}

	public String getData() {
		return node.getData();
	}

	public String substringData(int offset, int count) throws DOMException {
		return node.getData().substring(offset, offset + count);
	}

	public int getLength() {
		return node.getData().length();
	}

	public void appendData(String arg) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void deleteData(int offset, int count) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void insertData(int offset, String arg) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void replaceData(int offset, int count, String arg) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void setData(String data) {
		throw new UnsupportedOperationException();
	}

	public String toString() {
		return node.toString();
	}
}
