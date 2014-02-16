package com.github.snoblind.winterface.rhino;

import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLHtmlElement;

public class RhinoHtmlElement extends RhinoElement implements HTMLHtmlElement {

	private static final long serialVersionUID = 7362463087681881390L;

	public RhinoHtmlElement(Element element, RhinoDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public String getVersion() {
		throw new UnsupportedOperationException();
	}

	public void setVersion(String version) {
		throw new UnsupportedOperationException();
	}
}
