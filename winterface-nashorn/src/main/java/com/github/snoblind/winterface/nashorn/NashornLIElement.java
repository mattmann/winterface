package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.html.HTMLLIElement;

public class NashornLIElement extends NashornElement implements HTMLLIElement {

	public NashornLIElement(final NashornDocument ownerDocument) {
		super("li", ownerDocument);
	}

	public String getType() {
		throw new UnsupportedOperationException();
	}

	public void setType(String type) {
		throw new UnsupportedOperationException();
	}

	public int getValue() {
		throw new UnsupportedOperationException();
	}

	public void setValue(int value) {
		throw new UnsupportedOperationException();
	}

}
