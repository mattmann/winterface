package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.ExtendedHTMLElement;
import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLLinkElement;

public class RhinoLinkElement extends RhinoElement implements HTMLLinkElement {

	public RhinoLinkElement(Element element, RhinoDocument ownerDocument) {
		super(element, ownerDocument, HTMLLinkElement.class, ExtendedHTMLElement.class);
	}

	public boolean getDisabled() {
		throw new UnsupportedOperationException();
	}

	public void setDisabled(boolean disabled) {
		throw new UnsupportedOperationException();
	}

	public String getCharset() {
		throw new UnsupportedOperationException();
	}

	public void setCharset(String charset) {
		throw new UnsupportedOperationException();
	}

	public String getHref() {
		throw new UnsupportedOperationException();
	}

	public void setHref(String href) {
		throw new UnsupportedOperationException();
	}

	public String getHreflang() {
		throw new UnsupportedOperationException();
	}

	public void setHreflang(String hreflang) {
		throw new UnsupportedOperationException();
	}

	public String getMedia() {
		throw new UnsupportedOperationException();
	}

	public void setMedia(String media) {
		throw new UnsupportedOperationException();
	}

	public String getRel() {
		throw new UnsupportedOperationException();
	}

	public void setRel(String rel) {
		throw new UnsupportedOperationException();
	}

	public String getRev() {
		throw new UnsupportedOperationException();
	}

	public void setRev(String rev) {
		throw new UnsupportedOperationException();
	}

	public String getTarget() {
		throw new UnsupportedOperationException();
	}

	public void setTarget(String target) {
		throw new UnsupportedOperationException();
	}

	public String getType() {
		throw new UnsupportedOperationException();
	}

	public void setType(String type) {
		throw new UnsupportedOperationException();
	}
}
