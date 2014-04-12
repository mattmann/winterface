package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.html.HTMLMetaElement;

public class NashornMetaElement extends NashornElement implements HTMLMetaElement {

	public NashornMetaElement(final NashornDocument ownerDocument) {
		super("meta", ownerDocument);
	}

	public String getContent() {
		throw new UnsupportedOperationException();
	}

	public void setContent(String content) {
		throw new UnsupportedOperationException();
	}

	public String getHttpEquiv() {
		throw new UnsupportedOperationException();
	}

	public void setHttpEquiv(String httpEquiv) {
		throw new UnsupportedOperationException();
	}

	public String getName() {
		throw new UnsupportedOperationException();
	}

	public void setName(String name) {
		throw new UnsupportedOperationException();
	}

	public String getScheme() {
		throw new UnsupportedOperationException();
	}

	public void setScheme(String scheme) {
		throw new UnsupportedOperationException();
	}
}
