package com.github.mattmann.winterface.jsoup;

import static org.apache.commons.lang.Validate.notNull;
import org.jsoup.nodes.TextNode;

import com.github.mattmann.winterface.DOMException;
import com.github.mattmann.winterface.Text;

public class JSoupText extends JSoupNode<TextNode> implements Text {

	private final JSoupDocument ownerDocument;

	public JSoupText(TextNode node, JSoupDocument ownerDocument) {
		super(node);
		notNull(this.ownerDocument = ownerDocument);
	}

	public JSoupDocument getOwnerDocument() {
		return ownerDocument;
	}

	public CharSequence getData() {
		return node.text();
	}

	public CharSequence substringData(long offset, long count) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public long getLength() {
		throw new UnsupportedOperationException();
	}

	public void appendData(CharSequence arg) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void deleteData(long offset, long count) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void insertData(long offset, CharSequence arg) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void replaceData(long offset, long count, CharSequence arg) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void setData(CharSequence data) {
		throw new UnsupportedOperationException();
	}

	public Text splitText(long offset) throws DOMException {
		throw new UnsupportedOperationException();
	}
}
