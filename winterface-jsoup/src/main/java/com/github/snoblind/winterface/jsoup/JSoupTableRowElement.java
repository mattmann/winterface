package com.github.snoblind.winterface.jsoup;

import com.github.snoblind.winterface.ExtendedHTMLElement;

import org.jsoup.nodes.Element;
import org.w3c.dom.DOMException;
import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLTableRowElement;

public class JSoupTableRowElement extends JSoupElement implements HTMLTableRowElement {

	public JSoupTableRowElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public int getRowIndex() {
		throw new UnsupportedOperationException();
	}

	public int getSectionRowIndex() {
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

	public ExtendedHTMLElement insertCell(int index) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void deleteCell(int index) throws DOMException {
		throw new UnsupportedOperationException();
	}

}
