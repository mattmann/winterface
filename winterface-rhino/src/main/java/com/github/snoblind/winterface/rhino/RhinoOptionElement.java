package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.ExtendedHTMLElement;
import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLFormElement;
import org.w3c.dom.html.HTMLOptionElement;

public class RhinoOptionElement extends RhinoElement implements HTMLOptionElement {

	public RhinoOptionElement(Element element, RhinoDocument ownerDocument) {
		super(element, ownerDocument, HTMLOptionElement.class, ExtendedHTMLElement.class);
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
		return getAttribute("disabled").equals("disabled");
	}

	public void setDisabled(boolean disabled) {
		setAttribute("disabled", disabled);
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
		setAttribute("selected", selected);
	}

	public String getValue() {
		return getAttribute("value");
	}

	public void setValue(String value) {
		setAttribute("value", value);
	}
}
