package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.ExtendedHTMLElement;
import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLHeadElement;

public class RhinoHeadElement extends RhinoElement implements HTMLHeadElement {

	public RhinoHeadElement(Element element, RhinoDocument ownerDocument) {
		super(element, ownerDocument, HTMLHeadElement.class, ExtendedHTMLElement.class);
	}

	public String getProfile() {
		throw new UnsupportedOperationException();
	}

	public void setProfile(String profile) {
		throw new UnsupportedOperationException();
	}
}
