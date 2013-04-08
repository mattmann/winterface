package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.Element;

import com.github.mattmann.winterface.DOMException;
import com.github.mattmann.winterface.HTMLCollection;
import com.github.mattmann.winterface.HTMLElement;
import com.github.mattmann.winterface.HTMLTableRowElement;

public class JSoupTableRowElement extends JSoupElement implements HTMLTableRowElement {

	public JSoupTableRowElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public long getRowIndex() {
		throw new UnsupportedOperationException();
	}

	public long getSectionRowIndex() {
		throw new UnsupportedOperationException();
	}

	public HTMLCollection getCells() {
		return collect("> td");
	}

	public CharSequence getAlign() {
		throw new UnsupportedOperationException();
	}

	public void setAlign(CharSequence align) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getBgColor() {
		throw new UnsupportedOperationException();
	}

	public void setBgColor(CharSequence bgColor) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getCh() {
		throw new UnsupportedOperationException();
	}

	public void setCh(CharSequence ch) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getChOff() {
		throw new UnsupportedOperationException();
	}

	public void setChOff(CharSequence chOff) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getVAlign() {
		throw new UnsupportedOperationException();
	}

	public void setVAlign(CharSequence vAlign) {
		throw new UnsupportedOperationException();
	}

	public HTMLElement insertCell(long index) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void deleteCell(long index) throws DOMException {
		throw new UnsupportedOperationException();
	}

}
