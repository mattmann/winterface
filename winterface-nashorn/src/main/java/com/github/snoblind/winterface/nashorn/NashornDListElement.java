package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.html.HTMLDListElement;

public class NashornDListElement extends NashornElement implements HTMLDListElement {

	public NashornDListElement(final NashornDocument ownerDocument) {
		super("dl", ownerDocument);
	}

	public boolean getCompact() {
		throw new UnsupportedOperationException();
	}

	public void setCompact(boolean compact) {
		throw new UnsupportedOperationException();
	}
}
