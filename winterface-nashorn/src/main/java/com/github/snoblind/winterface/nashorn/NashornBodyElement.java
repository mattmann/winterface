package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.html.HTMLBodyElement;

public class NashornBodyElement extends NashornElement implements HTMLBodyElement {

	public NashornBodyElement(final NashornDocument ownerDocument) {
		super("body", ownerDocument);
	}

	public String getALink() {
		throw new UnsupportedOperationException();
	}

	public void setALink(String aLink) {
		throw new UnsupportedOperationException();
	}

	public String getBackground() {
		throw new UnsupportedOperationException();
	}

	public void setBackground(String background) {
		throw new UnsupportedOperationException();
	}

	public String getBgColor() {
		throw new UnsupportedOperationException();
	}

	public void setBgColor(String bgColor) {
		throw new UnsupportedOperationException();
	}

	public String getLink() {
		throw new UnsupportedOperationException();
	}

	public void setLink(String link) {
		throw new UnsupportedOperationException();
	}

	public String getText() {
		throw new UnsupportedOperationException();
	}

	public void setText(String text) {
		throw new UnsupportedOperationException();
	}

	public String getVLink() {
		throw new UnsupportedOperationException();
	}

	public void setVLink(String vLink) {
		throw new UnsupportedOperationException();
	}
}
