package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.Element;

import com.github.mattmann.winterface.HTMLParagraphElement;

public class JSoupParagraphElement extends JSoupElement implements HTMLParagraphElement {

	public JSoupParagraphElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public CharSequence getAlign() {
		return getAttribute("align");
	}

	public void setAlign(CharSequence align) {
		setAttribute("align", align);
	}
}
