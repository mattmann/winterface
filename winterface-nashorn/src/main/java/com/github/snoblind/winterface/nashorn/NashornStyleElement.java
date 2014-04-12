package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.html.HTMLStyleElement;

public class NashornStyleElement extends NashornElement implements HTMLStyleElement {

	public NashornStyleElement(final NashornDocument ownerDocument) {
		super("style", ownerDocument);
	}

	public boolean getDisabled() {
		throw new UnsupportedOperationException();
	}

	public void setDisabled(boolean disabled) {
		throw new UnsupportedOperationException();
	}

	public String getMedia() {
		throw new UnsupportedOperationException();
	}

	public void setMedia(String media) {
		throw new UnsupportedOperationException();
	}

	public String getType() {
		throw new UnsupportedOperationException();
	}

	public void setType(String type) {
		throw new UnsupportedOperationException();
	}
}
