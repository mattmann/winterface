package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.html.HTMLButtonElement;
import org.w3c.dom.html.HTMLFormElement;

public class NashornButtonElement extends NashornElement implements HTMLButtonElement {

	public NashornButtonElement(NashornDocument ownerDocument) {
		super("button", ownerDocument);
	}

	public HTMLFormElement getForm() {
		throw new UnsupportedOperationException();
	}

	public String getAccessKey() {
		throw new UnsupportedOperationException();
	}

	public void setAccessKey(String accessKey) {
		throw new UnsupportedOperationException();
	}

	public boolean getDisabled() {
		throw new UnsupportedOperationException();
	}

	public void setDisabled(boolean disabled) {
		throw new UnsupportedOperationException();
	}

	public String getName() {
		throw new UnsupportedOperationException();
	}

	public void setName(String name) {
		throw new UnsupportedOperationException();
	}

	public int getTabIndex() {
		throw new UnsupportedOperationException();
	}

	public void setTabIndex(int tabIndex) {
		throw new UnsupportedOperationException();
	}

	public String getType() {
		throw new UnsupportedOperationException();
	}

	public String getValue() {
		throw new UnsupportedOperationException();
	}

	public void setValue(String value) {
		throw new UnsupportedOperationException();
	}

}
