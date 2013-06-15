package com.github.mattmann.winterface.jsoup;

import com.github.mattmann.winterface.ExtendedHTMLFormElement;
import org.jsoup.nodes.Element;
import org.w3c.dom.html.HTMLOptionElement;

public class JSoupOptionElement extends JSoupElement implements HTMLOptionElement {

	public JSoupOptionElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public ExtendedHTMLFormElement getForm() {
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
		String value = getAttribute("selected").toString();
		return "selected".equalsIgnoreCase(value) || "true".equalsIgnoreCase(value);
	}

	public void setSelected(boolean selected) {
		throw new UnsupportedOperationException();
	}

	public String getValue() {
		return getAttribute("value");
	}

	public void setValue(String value) {
		throw new UnsupportedOperationException();
	}

}
