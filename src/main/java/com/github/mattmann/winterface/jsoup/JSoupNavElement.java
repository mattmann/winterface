package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.Element;

import com.github.mattmann.winterface.HTMLNavElement;

public class JSoupNavElement extends JSoupElement implements HTMLNavElement {

	public JSoupNavElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}
}
