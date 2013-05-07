package com.github.mattmann.winterface.jsoup;

import com.github.mattmann.winterface.HTMLCollection;
import com.github.mattmann.winterface.HTMLElement;
import com.github.mattmann.winterface.HTMLTableRowElement;
import org.jsoup.nodes.Element;
import org.w3c.dom.DOMException;

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

	public HTMLElement insertCell(long index) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void deleteCell(long index) throws DOMException {
		throw new UnsupportedOperationException();
	}

}
