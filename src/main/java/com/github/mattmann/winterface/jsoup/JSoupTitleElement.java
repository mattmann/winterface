package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.Element;
import org.w3c.dom.html.HTMLTitleElement;
import static org.apache.commons.lang.Validate.notNull;

public class JSoupTitleElement extends JSoupElement implements HTMLTitleElement {

	public JSoupTitleElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public String getText() {
		return node.text();
	}

	public void setText(String text) {
		notNull(text);
		node.text(text);
	}
}
