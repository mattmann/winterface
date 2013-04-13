package com.github.mattmann.winterface.jsoup;

import com.github.mattmann.winterface.HTMLScriptElement;
import org.jsoup.nodes.Element;
import static org.apache.commons.lang.Validate.notNull;

public class JSoupScriptElement extends JSoupElement implements HTMLScriptElement {

	public JSoupScriptElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public CharSequence getCharset() {
		return getAttribute("charset");
	}

	public void setCharset(CharSequence charset) {
		setAttribute("charset", charset);
	}

	public boolean isDefer() {
		return hasAttribute("defer");
	}

	public void setDefer(boolean defer) {
		setBooleanAttribute("defer", defer);
	}

	public CharSequence getSrc() {
		return getAttribute("src");
	}

	public void setSrc(CharSequence src) {
		setAttribute("src", src);
	}

	public CharSequence getType() {
		return getAttribute("type");
	}

	public void setType(CharSequence type) {
		setAttribute("type", type);
	}

	public boolean isAsync() {
		return hasAttribute("async");
	}

	public void setAsync(boolean async) {
		setBooleanAttribute("async", async);
	}

	public CharSequence getText() {
		return node.text();
	}

	public void setText(CharSequence text) {
		notNull(text);
		node.text(text.toString());
	}
}