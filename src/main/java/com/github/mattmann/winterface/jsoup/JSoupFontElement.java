package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.Element;
import org.w3c.dom.html.HTMLFontElement;

public class JSoupFontElement extends JSoupElement implements HTMLFontElement {

	public JSoupFontElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public String getColor() {
		throw new UnsupportedOperationException();
	}

	public void setColor(String color) {
		throw new UnsupportedOperationException();
	}

	public String getFace() {
		throw new UnsupportedOperationException();
	}

	public void setFace(String face) {
		throw new UnsupportedOperationException();
	}

	public String getSize() {
		throw new UnsupportedOperationException();
	}

	public void setSize(String size) {
		throw new UnsupportedOperationException();
	}
}