package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.ExtendedHTMLElement;
import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLLIElement;

public class RhinoLIElement extends RhinoElement implements HTMLLIElement {

	public RhinoLIElement(Element element, RhinoDocument ownerDocument) {
		super(element, ownerDocument, HTMLLIElement.class, ExtendedHTMLElement.class);
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
