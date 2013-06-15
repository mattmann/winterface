package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.Element;
import org.w3c.dom.html.HTMLParagraphElement;

public class JSoupParagraphElement extends JSoupElement implements HTMLParagraphElement {

	public JSoupParagraphElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public String getAlign() {
		return getAttribute("align");
	}

	public void setAlign(String align) {
		setAttribute("align", align);
	}
}
