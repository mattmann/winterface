package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.ExtendedHTMLScriptElement;
import org.w3c.dom.Element;

public class RhinoScriptElement extends RhinoElement implements ExtendedHTMLScriptElement {

	public RhinoScriptElement(Element element, RhinoDocument ownerDocument) {
		super(element, ownerDocument, ExtendedHTMLScriptElement.class);
	}

	public String getCharset() {
		return getAttribute("charset");
	}

	public void setCharset(String charset) {
		setAttribute("charset", charset);
	}

	public boolean getDefer() {
		return hasAttribute("defer");
	}

	public void setDefer(boolean defer) {
		setAttribute("defer", defer);
	}

	public String getSrc() {
		return getAttribute("src");
	}

	public void setSrc(String src) {
		setAttribute("src", src);
	}

	public String getType() {
		return getAttribute("type");
	}

	public void setType(String type) {
		setAttribute("type", type);
	}

	public boolean isAsync() {
		return hasAttribute("async");
	}

	public void setAsync(boolean async) {
		setAttribute("async", async);
	}

	public String getText() {
		throw new UnsupportedOperationException();
	}

	public void setText(String text) {
		throw new UnsupportedOperationException();
	}

	public String getHtmlFor() {
		throw new UnsupportedOperationException();
	}

	public void setHtmlFor(String htmlFor) {
		throw new UnsupportedOperationException();
	}

	public String getEvent() {
		throw new UnsupportedOperationException();
	}

	public void setEvent(String event) {
		throw new UnsupportedOperationException();
	}
}
