package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.html.HTMLFontElement;

public class NashornFontElement extends NashornElement implements HTMLFontElement {

	public NashornFontElement(final NashornDocument ownerDocument) {
		super("font", ownerDocument);
	}

	public String getColor() {
		throw new UnsupportedOperationException();
	}

	public void setColor(String color) {
		throw new UnsupportedOperationException();
	}

	public String getFace() {
		throw new UnsupportedOperationException();
	}

	public void setFace(String face) {
		throw new UnsupportedOperationException();
	}

	public String getSize() {
		throw new UnsupportedOperationException();
	}

	public void setSize(String size) {
		throw new UnsupportedOperationException();
	}

}
