package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.html.HTMLAnchorElement;

public class NashornAnchorElement extends NashornElement implements HTMLAnchorElement {

	public NashornAnchorElement(final NashornDocument ownerDocument) {
		super("a", ownerDocument);
	}

	public String getAccessKey() {
		throw new UnsupportedOperationException();
	}

	public void setAccessKey(String accessKey) {
		throw new UnsupportedOperationException();
	}

	public String getCharset() {
		throw new UnsupportedOperationException();
	}

	public void setCharset(String charset) {
		throw new UnsupportedOperationException();
	}

	public String getCoords() {
		throw new UnsupportedOperationException();
	}

	public void setCoords(String coords) {
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

	public String getName() {
		throw new UnsupportedOperationException();
	}

	public void setName(String name) {
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

	public String getShape() {
		throw new UnsupportedOperationException();
	}

	public void setShape(String shape) {
		throw new UnsupportedOperationException();
	}

	public int getTabIndex() {
		throw new UnsupportedOperationException();
	}

	public void setTabIndex(int tabIndex) {
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

	public void blur() {
		throw new UnsupportedOperationException();
	}

	public void focus() {
		throw new UnsupportedOperationException();
	}
}
