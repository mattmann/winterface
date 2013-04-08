package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.Element;
import com.github.mattmann.winterface.DOMException;
import com.github.mattmann.winterface.HTMLCollection;
import com.github.mattmann.winterface.HTMLElement;
import com.github.mattmann.winterface.HTMLTableSectionElement;

public class JSoupTableSectionElement extends JSoupElement implements HTMLTableSectionElement {

	public JSoupTableSectionElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public CharSequence getAlign() {
		throw new UnsupportedOperationException();
	}

	public void setAlign(CharSequence align) {
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

	public HTMLCollection getRows() {
		return collect("> tr");
	}

	public HTMLElement insertRow(long index) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void deleteRow(long index) throws DOMException {
		throw new UnsupportedOperationException();
	}

}
