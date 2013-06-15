package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.Element;
import org.w3c.dom.html.HTMLHtmlElement;

public class JSoupHtmlElement extends JSoupElement implements HTMLHtmlElement {

	public JSoupHtmlElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public String getVersion() {
		throw new UnsupportedOperationException();
	}

	public void setVersion(String version) {
		throw new UnsupportedOperationException();
	}
}