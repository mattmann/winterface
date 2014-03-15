package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.ExtendedHTMLElement;
import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLTitleElement;

public class RhinoTitleElement extends RhinoElement implements HTMLTitleElement {

	public RhinoTitleElement(Element element, RhinoDocument ownerDocument) {
		super(element, ownerDocument, HTMLTitleElement.class, ExtendedHTMLElement.class);
	}

	public String getText() {
		throw new UnsupportedOperationException();
	}

	public void setText(String text) {
		throw new UnsupportedOperationException();
	}
}
