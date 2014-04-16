package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.html.HTMLHRElement;

public class NashornHRElement extends NashornElement implements HTMLHRElement {

	public NashornHRElement(NashornDocument ownerDocument) {
		super("hr", ownerDocument);
	}

	public String getAlign() {
		throw new UnsupportedOperationException();
	}

	public void setAlign(String align) {
		throw new UnsupportedOperationException();
	}

	public boolean getNoShade() {
		throw new UnsupportedOperationException();
	}

	public void setNoShade(boolean noShade) {
		throw new UnsupportedOperationException();
	}

	public String getSize() {
		throw new UnsupportedOperationException();
	}

	public void setSize(String size) {
		throw new UnsupportedOperationException();
	}

	public String getWidth() {
		throw new UnsupportedOperationException();
	}

	public void setWidth(String width) {
		throw new UnsupportedOperationException();
	}

}
