package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.html.HTMLDivElement;

public class NashornDivElement extends NashornElement implements HTMLDivElement {

	public NashornDivElement(final NashornDocument ownerDocument) {
		super("div", ownerDocument);
	}

	public String getAlign() {
		throw new UnsupportedOperationException();
	}

	public void setAlign(String align) {
		throw new UnsupportedOperationException();
	}
}
