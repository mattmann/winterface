package com.github.snoblind.winterface.jsoup;

import com.github.snoblind.winterface.ExtendedHTMLScriptElement;

import org.jsoup.nodes.Element;
import static org.apache.commons.lang.Validate.notNull;

public class JSoupScriptElement extends JSoupElement implements ExtendedHTMLScriptElement {

	public JSoupScriptElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
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
		setBooleanAttribute("defer", defer);
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
		setBooleanAttribute("async", async);
	}

	public String getText() {
		return node.text();
	}

	public void setText(String text) {
		notNull(text);
		node.text(text.toString());
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