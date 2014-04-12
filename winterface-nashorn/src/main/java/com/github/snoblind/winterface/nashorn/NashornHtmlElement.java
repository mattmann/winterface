package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.html.HTMLHtmlElement;

public class NashornHtmlElement extends NashornElement implements HTMLHtmlElement {

	public NashornHtmlElement(final NashornDocument ownerDocument) {
		super("html", ownerDocument);
	}

	public String getVersion() {
		throw new UnsupportedOperationException();
	}

	public void setVersion(String version) {
		throw new UnsupportedOperationException();
	}
}
