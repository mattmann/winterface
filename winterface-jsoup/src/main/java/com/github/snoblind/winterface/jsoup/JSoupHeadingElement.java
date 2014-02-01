package com.github.snoblind.winterface.jsoup;

import org.w3c.dom.html.HTMLHeadingElement;
import org.jsoup.nodes.Element;

public class JSoupHeadingElement extends JSoupElement implements HTMLHeadingElement {

	public JSoupHeadingElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public String getAlign() {
		throw new UnsupportedOperationException();
	}

	public void setAlign(String align) {
		throw new UnsupportedOperationException();
	}
}