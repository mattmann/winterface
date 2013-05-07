package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.Element;
import com.github.mattmann.winterface.DOMException;
import com.github.mattmann.winterface.HTMLCollection;
import com.github.mattmann.winterface.HTMLElement;
import com.github.mattmann.winterface.HTMLTableCaptionElement;
import com.github.mattmann.winterface.HTMLTableElement;
import com.github.mattmann.winterface.HTMLTableSectionElement;

public class JSoupTableElement extends JSoupElement implements HTMLTableElement {

	public JSoupTableElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public HTMLTableCaptionElement getCaption() {
		throw new UnsupportedOperationException();
	}

	public void setCaption(HTMLTableCaptionElement caption) {
		throw new UnsupportedOperationException();
	}

	public HTMLTableSectionElement getTHead() {
		throw new UnsupportedOperationException();
	}

	public void setTHead(HTMLTableSectionElement tHead) {
		throw new UnsupportedOperationException();
	}

	public HTMLTableSectionElement getTFoot() {
		throw new UnsupportedOperationException();
	}

	public void setTFoot(HTMLTableSectionElement tFoot) {
		throw new UnsupportedOperationException();
	}

	public HTMLCollection getRows() {
		throw new UnsupportedOperationException();
	}

	public HTMLCollection gettBodies() {
		return getTBodies();
	}

	public HTMLCollection getTBodies() {
		return collect("> tbody");
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

	public String getBorder() {
		throw new UnsupportedOperationException();
	}

	public void setBorder(String border) {
		throw new UnsupportedOperationException();
	}

	public String getCellPadding() {
		throw new UnsupportedOperationException();
	}

	public void setCellPadding(String cellPadding) {
		throw new UnsupportedOperationException();
	}

	public String getCellSpacing() {
		throw new UnsupportedOperationException();
	}

	public void setCellSpacing(String cellSpacing) {
		throw new UnsupportedOperationException();
	}

	public String getFrame() {
		throw new UnsupportedOperationException();
	}

	public void setFrame(String frame) {
		throw new UnsupportedOperationException();
	}

	public String getRules() {
		throw new UnsupportedOperationException();
	}

	public void setRules(String rules) {
		throw new UnsupportedOperationException();
	}

	public String getSummary() {
		throw new UnsupportedOperationException();
	}

	public void setSummary(String summary) {
		throw new UnsupportedOperationException();
	}

	public String getWidth() {
		throw new UnsupportedOperationException();
	}

	public void setWidth(String width) {
		throw new UnsupportedOperationException();
	}

	public HTMLElement createTHead() {
		throw new UnsupportedOperationException();
	}

	public void deleteTHead() {
		throw new UnsupportedOperationException();
	}

	public HTMLElement createTFoot() {
		throw new UnsupportedOperationException();
	}

	public void deleteTFoot() {
		throw new UnsupportedOperationException();
	}

	public HTMLElement createCaption() {
		throw new UnsupportedOperationException();
	}

	public void deleteCaption() {
		throw new UnsupportedOperationException();
	}

	public HTMLElement insertRow(long index) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void deleteRow(long index) throws DOMException {
		throw new UnsupportedOperationException();
	}
}
