package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.Document;
import org.w3c.dom.html.HTMLIFrameElement;

public class NashornIFrameElement extends NashornElement implements HTMLIFrameElement {

	public NashornIFrameElement(NashornDocument ownerDocument) {
		super("iframe", ownerDocument);
	}

	public String getAlign() {
		throw new UnsupportedOperationException();
	}

	public void setAlign(String align) {
		throw new UnsupportedOperationException();
	}

	public String getFrameBorder() {
		throw new UnsupportedOperationException();
	}

	public void setFrameBorder(String frameBorder) {
		throw new UnsupportedOperationException();
	}

	public String getHeight() {
		throw new UnsupportedOperationException();
	}

	public void setHeight(String height) {
		throw new UnsupportedOperationException();
	}

	public String getLongDesc() {
		throw new UnsupportedOperationException();
	}

	public void setLongDesc(String longDesc) {
		throw new UnsupportedOperationException();
	}

	public String getMarginHeight() {
		throw new UnsupportedOperationException();
	}

	public void setMarginHeight(String marginHeight) {
		throw new UnsupportedOperationException();
	}

	public String getMarginWidth() {
		throw new UnsupportedOperationException();
	}

	public void setMarginWidth(String marginWidth) {
		throw new UnsupportedOperationException();
	}

	public String getName() {
		throw new UnsupportedOperationException();
	}

	public void setName(String name) {
		throw new UnsupportedOperationException();
	}

	public String getScrolling() {
		throw new UnsupportedOperationException();
	}

	public void setScrolling(String scrolling) {
		throw new UnsupportedOperationException();
	}

	public String getSrc() {
		throw new UnsupportedOperationException();
	}

	public void setSrc(String src) {
		throw new UnsupportedOperationException();
	}

	public String getWidth() {
		throw new UnsupportedOperationException();
	}

	public void setWidth(String width) {
		throw new UnsupportedOperationException();
	}

	public Document getContentDocument() {
		throw new UnsupportedOperationException();
	}
}
