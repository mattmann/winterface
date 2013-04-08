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

	public HTMLCollection getTBodies() {
		return collect("> tbody");
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

	public CharSequence getBorder() {
		throw new UnsupportedOperationException();
	}

	public void setBorder(CharSequence border) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getCellPadding() {
		throw new UnsupportedOperationException();
	}

	public void setCellPadding(CharSequence cellPadding) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getCellSpacing() {
		throw new UnsupportedOperationException();
	}

	public void setCellSpacing(CharSequence cellSpacing) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getFrame() {
		throw new UnsupportedOperationException();
	}

	public void setFrame(CharSequence frame) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getRules() {
		throw new UnsupportedOperationException();
	}

	public void setRules(CharSequence rules) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getSummary() {
		throw new UnsupportedOperationException();
	}

	public void setSummary(CharSequence summary) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getWidth() {
		throw new UnsupportedOperationException();
	}

	public void setWidth(CharSequence width) {
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
