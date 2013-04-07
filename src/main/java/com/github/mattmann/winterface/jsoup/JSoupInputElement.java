package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.Element;

import com.github.mattmann.winterface.HTMLFormElement;
import com.github.mattmann.winterface.HTMLInputElement;
import com.github.mattmann.winterface.Node;

public class JSoupInputElement extends JSoupElement implements HTMLInputElement {

	public JSoupInputElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public CharSequence getDefaultValue() {
		throw new UnsupportedOperationException();
	}

	public void setDefaultValue(CharSequence defaultValue) {
		throw new UnsupportedOperationException();
	}

	public boolean isDefaultChecked() {
		throw new UnsupportedOperationException();
	}

	public void setDefaultChecked(boolean defaultChecked) {
		throw new UnsupportedOperationException();
	}

	public HTMLFormElement getForm() {
		Node parentNode = getParentNode();
		while (parentNode != null) {
			if (parentNode instanceof HTMLFormElement) {
				return (HTMLFormElement)parentNode;
			}
			parentNode = parentNode.getParentNode();
		}
		return null;
	}

	public CharSequence getType() {
		throw new UnsupportedOperationException();
	}

	public CharSequence getAccept() {
		throw new UnsupportedOperationException();
	}

	public void setAccept(CharSequence accept) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getAccessKey() {
		throw new UnsupportedOperationException();
	}

	public void setAccessKey(CharSequence accessKey) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getAlign() {
		throw new UnsupportedOperationException();
	}

	public void setAlign(CharSequence align) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getAlt() {
		throw new UnsupportedOperationException();
	}

	public void setAlt(CharSequence alt) {
		throw new UnsupportedOperationException();
	}

	public boolean isChecked() {
		throw new UnsupportedOperationException();
	}

	public void setChecked(boolean checked) {
		throw new UnsupportedOperationException();
	}

	public boolean isDisabled() {
		throw new UnsupportedOperationException();
	}

	public void setDisabled(boolean disabled) {
		throw new UnsupportedOperationException();
	}

	public long getMaxLength() {
		throw new UnsupportedOperationException();
	}

	public void setMaxLength(long maxLength) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getName() {
		return getAttribute("name");
	}

	public void setName(CharSequence name) {
		setAttribute("name", name);
	}

	public boolean isReadOnly() {
		throw new UnsupportedOperationException();
	}

	public void setReadOnly(boolean readOnly) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getSize() {
		throw new UnsupportedOperationException();
	}

	public void setSize(CharSequence size) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getSrc() {
		throw new UnsupportedOperationException();
	}

	public void setSrc(CharSequence src) {
		throw new UnsupportedOperationException();
	}

	public long getTabIndex() {
		throw new UnsupportedOperationException();
	}

	public void setTabIndex(long tabIndex) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getUseMap() {
		throw new UnsupportedOperationException();
	}

	public void setUseMap(CharSequence useMap) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getValue() {
		return getAttribute("value");
	}

	public void setValue(CharSequence value) {
		setAttribute("value", value);
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

	public void click() {
		throw new UnsupportedOperationException();
	}
}
