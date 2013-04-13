package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.Element;

import com.github.mattmann.winterface.HTMLMetaElement;

public class JSoupMetaElement extends JSoupElement implements HTMLMetaElement {

	public JSoupMetaElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public CharSequence getContent() {
		return getAttribute("content");
	}

	public void setContent(CharSequence content) {
		setAttribute("content", content);
	}

	public CharSequence getHttpEquiv() {
		return getAttribute("http-equiv");
	}

	public void setHttpEquiv(CharSequence httpEquiv) {
		setAttribute("http-equiv", httpEquiv);
	}

	public CharSequence getName() {
		return getAttribute("name");
	}

	public void setName(CharSequence name) {
		this.setAttribute("name", name);
	}

	public CharSequence getScheme() {
		return getAttribute("scheme");
	}

	public void setScheme(CharSequence scheme) {
		setAttribute("scheme", scheme);
	}

	public CharSequence getCharset() {
		return getAttribute("charset");
	}

	public void setCharset(CharSequence charset) {
		setAttribute("charset", charset);
	}
}
