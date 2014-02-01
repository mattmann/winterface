package com.github.snoblind.winterface.jsoup;

import org.jsoup.nodes.Element;
import org.w3c.dom.html.HTMLFormElement;
import org.w3c.dom.html.HTMLLabelElement;

public class JSoupLabelElement extends JSoupElement implements HTMLLabelElement {

	public JSoupLabelElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public HTMLFormElement getForm() {
		throw new UnsupportedOperationException();
	}

	public String getAccessKey() {
		throw new UnsupportedOperationException();
	}

	public void setAccessKey(String accessKey) {
		throw new UnsupportedOperationException();
	}

	public String getHtmlFor() {
		throw new UnsupportedOperationException();
	}

	public void setHtmlFor(String htmlFor) {
		throw new UnsupportedOperationException();
	}
}