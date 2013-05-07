package com.github.mattmann.winterface.jsoup;

import com.github.mattmann.winterface.HTMLFormElement;
import com.github.mattmann.winterface.HTMLInputElement;
import org.jsoup.nodes.Element;
import org.w3c.dom.Node;

public class JSoupInputElement extends JSoupElement implements HTMLInputElement {

	public JSoupInputElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public String getDefaultValue() {
		throw new UnsupportedOperationException();
	}

	public void setDefaultValue(String defaultValue) {
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

	public String getType() {
		throw new UnsupportedOperationException();
	}

	public String getAccept() {
		throw new UnsupportedOperationException();
	}

	public void setAccept(String accept) {
		throw new UnsupportedOperationException();
	}

	public String getAccessKey() {
		throw new UnsupportedOperationException();
	}

	public void setAccessKey(String accessKey) {
		throw new UnsupportedOperationException();
	}

	public String getAlign() {
		throw new UnsupportedOperationException();
	}

	public void setAlign(String align) {
		throw new UnsupportedOperationException();
	}

	public String getAlt() {
		throw new UnsupportedOperationException();
	}

	public void setAlt(String alt) {
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

	public String getName() {
		return getAttribute("name");
	}

	public void setName(String name) {
		setAttribute("name", name);
	}

	public boolean isReadOnly() {
		throw new UnsupportedOperationException();
	}

	public void setReadOnly(boolean readOnly) {
		throw new UnsupportedOperationException();
	}

	public String getSize() {
		throw new UnsupportedOperationException();
	}

	public void setSize(String size) {
		throw new UnsupportedOperationException();
	}

	public String getSrc() {
		throw new UnsupportedOperationException();
	}

	public void setSrc(String src) {
		throw new UnsupportedOperationException();
	}

	public long getTabIndex() {
		throw new UnsupportedOperationException();
	}

	public void setTabIndex(long tabIndex) {
		throw new UnsupportedOperationException();
	}

	public String getUseMap() {
		throw new UnsupportedOperationException();
	}

	public void setUseMap(String useMap) {
		throw new UnsupportedOperationException();
	}

	public String getValue() {
		return getAttribute("value");
	}

	public void setValue(String value) {
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
