package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.html.HTMLUListElement;

public class NashornUListElement extends NashornElement implements HTMLUListElement {

	public NashornUListElement(final NashornDocument ownerDocument) {
		super("ulist", ownerDocument);
	}

	public boolean getCompact() {
		throw new UnsupportedOperationException();
	}

	public void setCompact(boolean compact) {
		throw new UnsupportedOperationException();
	}

	public String getType() {
		throw new UnsupportedOperationException();
	}

	public void setType(String type) {
		throw new UnsupportedOperationException();
	}
}
