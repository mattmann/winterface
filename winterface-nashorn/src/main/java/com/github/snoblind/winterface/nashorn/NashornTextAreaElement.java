package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.html.HTMLFormElement;
import org.w3c.dom.html.HTMLTextAreaElement;

public class NashornTextAreaElement extends NashornElement implements HTMLTextAreaElement {

	public NashornTextAreaElement(final NashornDocument ownerDocument) {
		super("textarea", ownerDocument);
	}

	public String getDefaultValue() {
		throw new UnsupportedOperationException();
	}

	public void setDefaultValue(String defaultValue) {
		throw new UnsupportedOperationException();
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

	public int getCols() {
		throw new UnsupportedOperationException();
	}

	public void setCols(int cols) {
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

	public boolean getReadOnly() {
		throw new UnsupportedOperationException();
	}

	public void setReadOnly(boolean readOnly) {
		throw new UnsupportedOperationException();
	}

	public int getRows() {
		throw new UnsupportedOperationException();
	}

	public void setRows(int rows) {
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

	public void blur() {
		throw new UnsupportedOperationException();
	}

	public void focus() {
		throw new UnsupportedOperationException();
	}

	public void select() {
		throw new UnsupportedOperationException();
	}
}
