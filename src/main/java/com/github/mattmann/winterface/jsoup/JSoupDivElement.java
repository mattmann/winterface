package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.Element;

import com.github.mattmann.winterface.HTMLDivElement;

public class JSoupDivElement extends JSoupElement implements HTMLDivElement {

	public JSoupDivElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public String getAlign() {
		throw new UnsupportedOperationException();
	}

	public void setAlign(String align) {
		throw new UnsupportedOperationException();
	}
}