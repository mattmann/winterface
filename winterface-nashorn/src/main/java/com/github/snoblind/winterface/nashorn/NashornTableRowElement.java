package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.DOMException;
import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLTableRowElement;

public class NashornTableRowElement extends NashornElement implements HTMLTableRowElement {

	public NashornTableRowElement(final NashornDocument ownerDocument) {
		super("tr", ownerDocument);
	}

	public int getRowIndex() {
		throw new UnsupportedOperationException();
	}

	public int getSectionRowIndex() {
		throw new UnsupportedOperationException();
	}

	public HTMLCollection getCells() {
		throw new UnsupportedOperationException();
	}

	public String getAlign() {
		throw new UnsupportedOperationException();
	}

	public void setAlign(String align) {
		throw new UnsupportedOperationException();
	}

	public String getBgColor() {
		throw new UnsupportedOperationException();
	}

	public void setBgColor(String bgColor) {
		throw new UnsupportedOperationException();
	}

	public String getCh() {
		throw new UnsupportedOperationException();
	}

	public void setCh(String ch) {
		throw new UnsupportedOperationException();
	}

	public String getChOff() {
		throw new UnsupportedOperationException();
	}

	public void setChOff(String chOff) {
		throw new UnsupportedOperationException();
	}

	public String getVAlign() {
		throw new UnsupportedOperationException();
	}

	public void setVAlign(String vAlign) {
		throw new UnsupportedOperationException();
	}

	public HTMLElement insertCell(int index) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void deleteCell(int index) throws DOMException {
		throw new UnsupportedOperationException();
	}
}
