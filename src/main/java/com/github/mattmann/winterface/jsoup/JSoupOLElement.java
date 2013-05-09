package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.Element;

import com.github.mattmann.winterface.HTMLOListElement;

public class JSoupOLElement extends JSoupElement implements HTMLOListElement {

	public JSoupOLElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public boolean getCompact() {
		throw new UnsupportedOperationException();
	}

	public void setCompact(boolean compact) {
		throw new UnsupportedOperationException();
	}

	public int getStart() {
		throw new UnsupportedOperationException();
	}

	public void setStart(int start) {
		throw new UnsupportedOperationException();
	}

	public String getType() {
		throw new UnsupportedOperationException();
	}

	public void setType(String type) {
		throw new UnsupportedOperationException();
	}
}