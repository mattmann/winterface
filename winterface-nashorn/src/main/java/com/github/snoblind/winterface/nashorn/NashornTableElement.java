package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.DOMException;
import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLTableCaptionElement;
import org.w3c.dom.html.HTMLTableElement;
import org.w3c.dom.html.HTMLTableSectionElement;

public class NashornTableElement extends NashornElement implements HTMLTableElement {

	public NashornTableElement(final NashornDocument ownerDocument) {
		super("table", ownerDocument);
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

	public HTMLCollection getTBodies() {
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

	public HTMLElement insertRow(int index) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void deleteRow(int index) throws DOMException {
		throw new UnsupportedOperationException();
	}

}
