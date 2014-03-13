package com.github.snoblind.winterface.rhino;

import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLHeadElement;

public class RhinoHeadElement extends RhinoElement implements HTMLHeadElement {

	private static final long serialVersionUID = 3716326119232332144L;

	public RhinoHeadElement(Element element, RhinoDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public String getProfile() {
		throw new UnsupportedOperationException();
	}

	public void setProfile(String profile) {
		throw new UnsupportedOperationException();
	}
}
