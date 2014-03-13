package com.github.snoblind.winterface.rhino;

import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLLIElement;

public class RhinoLIElement extends RhinoElement implements HTMLLIElement {

	private static final long serialVersionUID = 5414591283591733909L;

	public RhinoLIElement(Element element, RhinoDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public String getType() {
		throw new UnsupportedOperationException();
	}

	public void setType(String type) {
		throw new UnsupportedOperationException();
	}

	public int getValue() {
		throw new UnsupportedOperationException();
	}

	public void setValue(int value) {
		throw new UnsupportedOperationException();
	}
}
