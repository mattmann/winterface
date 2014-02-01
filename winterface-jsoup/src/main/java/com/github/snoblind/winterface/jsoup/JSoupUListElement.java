package com.github.snoblind.winterface.jsoup;

import org.jsoup.nodes.Element;
import org.w3c.dom.html.HTMLUListElement;

public class JSoupUListElement extends JSoupElement implements HTMLUListElement {

	public JSoupUListElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public boolean getCompact() {
		throw new UnsupportedOperationException();
	}

	public void setCompact(boolean compact) {
		throw new UnsupportedOperationException();
	}

	public String getType() {
		throw new UnsupportedOperationException();
	}

	public void setType(String type) {
		throw new UnsupportedOperationException();
	}
}