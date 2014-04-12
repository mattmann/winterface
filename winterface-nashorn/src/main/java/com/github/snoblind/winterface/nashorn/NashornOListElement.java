package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.html.HTMLOListElement;

public class NashornOListElement extends NashornElement implements HTMLOListElement {

	public NashornOListElement(final NashornDocument ownerDocument) {
		super("ol", ownerDocument);
	}

	public boolean getCompact() {
		throw new UnsupportedOperationException();
	}

	public void setCompact(boolean compact) {
		throw new UnsupportedOperationException();
	}

	public int getStart() {
		throw new UnsupportedOperationException();
	}

	public void setStart(int start) {
		throw new UnsupportedOperationException();
	}

	public String getType() {
		throw new UnsupportedOperationException();
	}

	public void setType(String type) {
		throw new UnsupportedOperationException();
	}

}
