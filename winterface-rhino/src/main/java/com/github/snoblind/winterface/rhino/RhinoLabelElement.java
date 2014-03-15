package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.ExtendedHTMLElement;
import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLFormElement;
import org.w3c.dom.html.HTMLLabelElement;

public class RhinoLabelElement extends RhinoElement implements HTMLLabelElement {

	public RhinoLabelElement(Element element, RhinoDocument ownerDocument) {
		super(element, ownerDocument, HTMLLabelElement.class, ExtendedHTMLElement.class);
	}

	public HTMLFormElement getForm() {
		throw new UnsupportedOperationException();
	}

	public String getAccessKey() {
		throw new UnsupportedOperationException();
	}

	public void setAccessKey(String accessKey) {
		throw new UnsupportedOperationException();
	}

	public String getHtmlFor() {
		throw new UnsupportedOperationException();
	}

	public void setHtmlFor(String htmlFor) {
		throw new UnsupportedOperationException();
	}

}
