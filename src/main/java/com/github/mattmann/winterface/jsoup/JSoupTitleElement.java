package com.github.mattmann.winterface.jsoup;

import com.github.mattmann.winterface.HTMLTitleElement;
import org.jsoup.nodes.Element;
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
