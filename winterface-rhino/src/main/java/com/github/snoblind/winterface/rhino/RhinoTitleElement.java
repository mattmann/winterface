package com.github.snoblind.winterface.rhino;

import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLTitleElement;

public class RhinoTitleElement extends RhinoElement implements HTMLTitleElement {

	private static final long serialVersionUID = 7538597343546525637L;

	public RhinoTitleElement(Element element, RhinoDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public String getText() {
		throw new UnsupportedOperationException();
	}

	public void setText(String text) {
		throw new UnsupportedOperationException();
	}
}
