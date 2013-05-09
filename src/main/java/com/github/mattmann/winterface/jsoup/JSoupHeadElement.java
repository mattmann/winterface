package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.Element;
import com.github.mattmann.winterface.HTMLHeadElement;

public class JSoupHeadElement extends JSoupElement implements HTMLHeadElement {

	public JSoupHeadElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public String getProfile() {
		throw new UnsupportedOperationException();
	}

	public void setProfile(String profile) {
		throw new UnsupportedOperationException();
	}
}
