package com.github.snoblind.winterface.jsoup;

import org.jsoup.nodes.Element;
import org.w3c.dom.html.HTMLHeadElement;

public class JSoupHeadElement extends JSoupElement implements HTMLHeadElement {

	public JSoupHeadElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public String getProfile() {
		throw new UnsupportedOperationException();
	}

	public void setProfile(String profile) {
		throw new UnsupportedOperationException();
	}
}