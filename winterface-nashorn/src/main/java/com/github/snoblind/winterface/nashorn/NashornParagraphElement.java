package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.html.HTMLParagraphElement;

public class NashornParagraphElement extends NashornElement implements HTMLParagraphElement {

	public NashornParagraphElement(final NashornDocument ownerDocument) {
		super("p", ownerDocument);
	}

	public String getAlign() {
		throw new UnsupportedOperationException();
	}

	public void setAlign(String align) {
		throw new UnsupportedOperationException();
	}

}
