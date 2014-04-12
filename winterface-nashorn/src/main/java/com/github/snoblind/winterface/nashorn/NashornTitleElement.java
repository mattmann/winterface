package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.html.HTMLTitleElement;

public class NashornTitleElement extends NashornElement implements HTMLTitleElement {

	public NashornTitleElement(final NashornDocument ownerDocument) {
		super("title", ownerDocument);
	}

	public String getText() {
		throw new UnsupportedOperationException();
	}

	public void setText(String text) {
		throw new UnsupportedOperationException();
	}
}