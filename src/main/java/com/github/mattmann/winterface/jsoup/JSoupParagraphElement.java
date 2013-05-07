package com.github.mattmann.winterface.jsoup;

import com.github.mattmann.winterface.HTMLParagraphElement;
import org.jsoup.nodes.Element;

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
