package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.ExtendedHTMLElement;
import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLHtmlElement;

public class RhinoHtmlElement extends RhinoElement implements HTMLHtmlElement {

	public RhinoHtmlElement(Element element, RhinoDocument ownerDocument) {
		super(element, ownerDocument, HTMLHtmlElement.class, ExtendedHTMLElement.class);
	}

	public String getVersion() {
		throw new UnsupportedOperationException();
	}

	public void setVersion(String version) {
		throw new UnsupportedOperationException();
	}
}
