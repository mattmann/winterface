package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.ExtendedHTMLElement;
import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLDivElement;

public class RhinoDivElement extends RhinoElement implements HTMLDivElement {

	public RhinoDivElement(Element element, RhinoDocument ownerDocument) {
		super(element, ownerDocument, HTMLDivElement.class, ExtendedHTMLElement.class);
	}

	public String getAlign() {
		throw new UnsupportedOperationException();
	}

	public void setAlign(String align) {
		throw new UnsupportedOperationException();
	}
}
