package com.github.mattmann.winterface.jsoup;

import com.github.mattmann.winterface.HTMLFooterElement;
import org.jsoup.nodes.Element;

public class JSoupFooterElement extends JSoupElement implements HTMLFooterElement {

	public JSoupFooterElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}
}
