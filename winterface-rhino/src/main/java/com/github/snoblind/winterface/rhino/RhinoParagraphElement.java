package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.ExtendedHTMLElement;
import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLParagraphElement;

public class RhinoParagraphElement extends RhinoElement implements HTMLParagraphElement {

	public RhinoParagraphElement(Element element, RhinoDocument ownerDocument) {
		super(element, ownerDocument, HTMLParagraphElement.class, ExtendedHTMLElement.class);
	}

	public String getAlign() {
		return getAttribute("align");
	}

	public void setAlign(String align) {
		setAttribute("align", align);
	}
}
