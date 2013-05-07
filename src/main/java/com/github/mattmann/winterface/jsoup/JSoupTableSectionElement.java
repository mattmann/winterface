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

	public String getAlign() {
		throw new UnsupportedOperationException();
	}

	public void setAlign(String align) {
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
