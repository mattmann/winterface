package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.Element;

import com.github.mattmann.winterface.HTMLHtmlElement;

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
