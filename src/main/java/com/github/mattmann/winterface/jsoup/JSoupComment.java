package com.github.mattmann.winterface.jsoup;

import com.github.mattmann.winterface.Comment;
import com.github.mattmann.winterface.DOMException;
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

	public CharSequence getData() {
		return node.getData();
	}

	public CharSequence substringData(int offset, int count) throws DOMException {
		return node.getData().subSequence(offset, offset + count);

	}

	public long getLength() {
		return node.getData().length();
	}

	public void appendData(CharSequence arg) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void deleteData(int offset, int count) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void insertData(int offset, CharSequence arg) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void replaceData(int offset, int count, CharSequence arg) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void setData(CharSequence data) {
		throw new UnsupportedOperationException();
	}

	public String toString() {
		return node.toString();
	}
}
