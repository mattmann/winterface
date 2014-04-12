package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.html.HTMLFormElement;
import org.w3c.dom.html.HTMLOptionElement;

public class NashornOptionElement extends NashornElement implements HTMLOptionElement {

	public NashornOptionElement(final NashornDocument ownerDocument) {
		super("option", ownerDocument);
	}

	public HTMLFormElement getForm() {
		throw new UnsupportedOperationException();
	}

	public boolean getDefaultSelected() {
		throw new UnsupportedOperationException();
	}

	public void setDefaultSelected(boolean defaultSelected) {
		throw new UnsupportedOperationException();
	}

	public String getText() {
		throw new UnsupportedOperationException();
	}

	public int getIndex() {
		throw new UnsupportedOperationException();
	}

	public boolean getDisabled() {
		throw new UnsupportedOperationException();
	}

	public void setDisabled(boolean disabled) {
		throw new UnsupportedOperationException();
	}

	public String getLabel() {
		throw new UnsupportedOperationException();
	}

	public void setLabel(String label) {
		throw new UnsupportedOperationException();
	}

	public boolean getSelected() {
		throw new UnsupportedOperationException();
	}

	public void setSelected(boolean selected) {
		throw new UnsupportedOperationException();
	}

	public String getValue() {
		throw new UnsupportedOperationException();
	}

	public void setValue(String value) {
		throw new UnsupportedOperationException();
	}

}
