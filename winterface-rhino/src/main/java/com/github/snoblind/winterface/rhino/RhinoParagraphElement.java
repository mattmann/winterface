package com.github.snoblind.winterface.rhino;

import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLParagraphElement;

public class RhinoParagraphElement extends RhinoElement implements HTMLParagraphElement {

	private static final long serialVersionUID = 1191519639000482200L;

	public RhinoParagraphElement(Element element, RhinoDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public String getAlign() {
		return getAttribute("align");
	}

	public void setAlign(String align) {
		setAttribute("align", align);
	}
}
