package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.html.HTMLFormElement;
import org.w3c.dom.html.HTMLLabelElement;

public class NashornLabelElement extends NashornElement implements HTMLLabelElement {

	public NashornLabelElement(NashornDocument ownerDocument) {
		super("label", ownerDocument);
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
