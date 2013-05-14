package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.Element;

import com.github.mattmann.winterface.HTMLBRElement;

public class JSoupBRElement extends JSoupElement implements HTMLBRElement {

	public JSoupBRElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public String getClear() {
		throw new UnsupportedOperationException();
	}

	public void setClear(String clear) {
		throw new UnsupportedOperationException();
	}
}