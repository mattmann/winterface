package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.html.HTMLHeadElement;

public class NashornHeadElement extends NashornElement implements HTMLHeadElement {

	public NashornHeadElement(final NashornDocument ownerDocument) {
		super("head", ownerDocument);
	}

	public String getProfile() {
		throw new UnsupportedOperationException();
	}

	public void setProfile(String profile) {
		throw new UnsupportedOperationException();
	}
}
