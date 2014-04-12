package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.html.HTMLBRElement;

public class NashornBRElement extends NashornElement implements HTMLBRElement {

	public NashornBRElement(final NashornDocument ownerDocument) {
		super("br", ownerDocument);
	}

	public String getClear() {
		throw new UnsupportedOperationException();
	}

	public void setClear(String clear) {
		throw new UnsupportedOperationException();
	}
}
