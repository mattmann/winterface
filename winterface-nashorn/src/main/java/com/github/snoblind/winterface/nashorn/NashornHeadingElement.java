package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.html.HTMLHeadingElement;

public class NashornHeadingElement extends NashornElement implements HTMLHeadingElement {

	public NashornHeadingElement(final String tagName, final NashornDocument ownerDocument) {
		super(tagName, ownerDocument);
	}

	public String getAlign() {
		throw new UnsupportedOperationException();
	}

	public void setAlign(String align) {
		throw new UnsupportedOperationException();
	}

}
