package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.TextNode;
import org.w3c.dom.DOMException;
import org.w3c.dom.Text;
import static org.apache.commons.lang.Validate.notNull;

public class JSoupText extends JSoupNode<TextNode> implements Text {

	private final JSoupDocument ownerDocument;

	public JSoupText(TextNode node, JSoupDocument ownerDocument) {
		super(node);
		notNull(this.ownerDocument = ownerDocument);
	}

	public JSoupDocument getOwnerDocument() {
		return ownerDocument;
	}

	public String getData() {
		return node.text();
	}

	public String substringData(int offset, int count) throws DOMException {
		return node.text().substring(offset, offset + count);
	}

	public int getLength() {
		throw new UnsupportedOperationException();
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

	public Text splitText(int offset) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public String toString() {
		return node.text();
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
}
