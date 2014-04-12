package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.html.HTMLScriptElement;

public class NashornScriptElement extends NashornElement implements HTMLScriptElement {

	public NashornScriptElement(final NashornDocument ownerDocument) {
		super("script", ownerDocument);
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

	public String getCharset() {
		throw new UnsupportedOperationException();
	}

	public void setCharset(String charset) {
		throw new UnsupportedOperationException();
	}

	public boolean getDefer() {
		throw new UnsupportedOperationException();
	}

	public void setDefer(boolean defer) {
		throw new UnsupportedOperationException();
	}

	public String getSrc() {
		throw new UnsupportedOperationException();
	}

	public void setSrc(String src) {
		throw new UnsupportedOperationException();
	}

	public String getType() {
		throw new UnsupportedOperationException();
	}

	public void setType(String type) {
		throw new UnsupportedOperationException();
	}
}