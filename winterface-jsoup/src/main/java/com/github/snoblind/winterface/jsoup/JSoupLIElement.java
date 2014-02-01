package com.github.snoblind.winterface.jsoup;

import org.jsoup.nodes.Element;
import org.w3c.dom.html.HTMLLIElement;

public class JSoupLIElement extends JSoupElement implements HTMLLIElement {

	public JSoupLIElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public String getType() {
		throw new UnsupportedOperationException();
	}

	public void setType(String type) {
		throw new UnsupportedOperationException();
	}

	public int getValue() {
		throw new UnsupportedOperationException();
	}

	public void setValue(int value) {
		throw new UnsupportedOperationException();
	}
}