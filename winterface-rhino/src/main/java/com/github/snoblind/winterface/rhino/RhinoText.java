package com.github.snoblind.winterface.rhino;

import org.w3c.dom.DOMException;
import org.w3c.dom.Text;
import static org.apache.commons.lang.Validate.notNull;

public class RhinoText extends RhinoNode<Text> implements Text {

	private static final long serialVersionUID = 1900789236571971113L;

	private final RhinoDocument ownerDocument;

	public RhinoText(Text node, RhinoDocument ownerDocument) {
		super(node);
		notNull(this.ownerDocument = ownerDocument);
	}

	public RhinoDocument getOwnerDocument() {
		return ownerDocument;
	}

	public String getData() throws DOMException {
		throw new UnsupportedOperationException();
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

	public Text splitText(int offset) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public boolean isElementContentWhitespace() {
		throw new UnsupportedOperationException();
	}

	public String getWholeText() {
		throw new UnsupportedOperationException();
	}

	public Text replaceWholeText(String content) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public String getClassName() {
		throw new UnsupportedOperationException();
	}
}
