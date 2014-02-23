package com.github.snoblind.winterface.rhino;

import org.w3c.dom.Comment;
import org.w3c.dom.DOMException;
import static org.apache.commons.lang.Validate.notNull;

public class RhinoComment extends RhinoNode<Comment> implements Comment {

	private static final long serialVersionUID = -1818831899075684175L;

	private final RhinoDocument ownerDocument;

	public RhinoComment(final Comment node, final RhinoDocument ownerDocument) {
		super(node);
		notNull(this.ownerDocument = ownerDocument);
	}

	public RhinoDocument getOwnerDocument() {
		return ownerDocument;
	}

	public String getData() throws DOMException {
		return node.getData();
	}

	public void setData(String data) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public int getLength() {
		throw new UnsupportedOperationException();
	}

	public String substringData(int offset, int count) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void appendData(String arg) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void insertData(int offset, String arg) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void deleteData(int offset, int count) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void replaceData(int offset, int count, String arg) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public String getClassName() {
		throw new UnsupportedOperationException();
	}
}
