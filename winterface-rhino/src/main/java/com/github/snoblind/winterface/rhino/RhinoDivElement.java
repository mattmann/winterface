package com.github.snoblind.winterface.rhino;

import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLDivElement;

public class RhinoDivElement extends RhinoElement implements HTMLDivElement {

	private static final long serialVersionUID = 4253226048841923390L;

	public RhinoDivElement(Element element, RhinoDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public String getAlign() {
		throw new UnsupportedOperationException();
	}

	public void setAlign(String align) {
		throw new UnsupportedOperationException();
	}
}
