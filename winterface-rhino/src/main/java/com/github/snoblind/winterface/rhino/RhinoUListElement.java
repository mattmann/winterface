package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.ExtendedHTMLElement;
import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLUListElement;

public class RhinoUListElement extends RhinoElement implements HTMLUListElement {

	public RhinoUListElement(Element element, RhinoDocument ownerDocument) {
		super(element, ownerDocument, HTMLUListElement.class, ExtendedHTMLElement.class);
	}

	public boolean getCompact() {
		throw new UnsupportedOperationException();
	}

	public void setCompact(boolean compact) {
		throw new UnsupportedOperationException();
	}

	public String getType() {
		throw new UnsupportedOperationException();
	}

	public void setType(String type) {
		throw new UnsupportedOperationException();
	}
}
