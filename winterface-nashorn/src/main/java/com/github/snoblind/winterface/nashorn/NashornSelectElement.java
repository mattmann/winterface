package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.DOMException;
import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLFormElement;
import org.w3c.dom.html.HTMLSelectElement;

public class NashornSelectElement extends NashornElement implements HTMLSelectElement {

	public NashornSelectElement(NashornDocument ownerDocument) {
		super("select", ownerDocument);
	}

	public String getType() {
		throw new UnsupportedOperationException();
	}

	public int getSelectedIndex() {
		throw new UnsupportedOperationException();
	}

	public void setSelectedIndex(int selectedIndex) {
		throw new UnsupportedOperationException();
	}

	public String getValue() {
		throw new UnsupportedOperationException();
	}

	public void setValue(String value) {
		throw new UnsupportedOperationException();
	}

	public int getLength() {
		throw new UnsupportedOperationException();
	}

	public HTMLFormElement getForm() {
		throw new UnsupportedOperationException();
	}

	public HTMLCollection getOptions() {
		throw new UnsupportedOperationException();
	}

	public boolean getDisabled() {
		throw new UnsupportedOperationException();
	}

	public void setDisabled(boolean disabled) {
		throw new UnsupportedOperationException();
	}

	public boolean getMultiple() {
		throw new UnsupportedOperationException();
	}

	public void setMultiple(boolean multiple) {
		throw new UnsupportedOperationException();
	}

	public String getName() {
		throw new UnsupportedOperationException();
	}

	public void setName(String name) {
		throw new UnsupportedOperationException();
	}

	public int getSize() {
		throw new UnsupportedOperationException();
	}

	public void setSize(int size) {
		throw new UnsupportedOperationException();
	}

	public int getTabIndex() {
		throw new UnsupportedOperationException();
	}

	public void setTabIndex(int tabIndex) {
		throw new UnsupportedOperationException();
	}

	public void add(HTMLElement element, HTMLElement before) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void remove(int index) {
		throw new UnsupportedOperationException();
	}

	public void blur() {
		throw new UnsupportedOperationException();
	}

	public void focus() {
		throw new UnsupportedOperationException();
	}

}
