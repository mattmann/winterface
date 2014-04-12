package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLFormElement;

public class NashornFormElement extends NashornElement implements HTMLFormElement {

	public NashornFormElement(final NashornDocument ownerDocument) {
		super("form", ownerDocument);
	}

	public HTMLCollection getElements() {
		throw new UnsupportedOperationException();
	}

	public int getLength() {
		throw new UnsupportedOperationException();
	}

	public String getName() {
		throw new UnsupportedOperationException();
	}

	public void setName(String name) {
		throw new UnsupportedOperationException();
	}

	public String getAcceptCharset() {
		throw new UnsupportedOperationException();
	}

	public void setAcceptCharset(String acceptCharset) {
		throw new UnsupportedOperationException();
	}

	public String getAction() {
		throw new UnsupportedOperationException();
	}

	public void setAction(String action) {
		throw new UnsupportedOperationException();
	}

	public String getEnctype() {
		throw new UnsupportedOperationException();
	}

	public void setEnctype(String enctype) {
		throw new UnsupportedOperationException();
	}

	public String getMethod() {
		throw new UnsupportedOperationException();
	}

	public void setMethod(String method) {
		throw new UnsupportedOperationException();
	}

	public String getTarget() {
		throw new UnsupportedOperationException();
	}

	public void setTarget(String target) {
		throw new UnsupportedOperationException();
	}

	public void submit() {
		throw new UnsupportedOperationException();
	}

	public void reset() {
		throw new UnsupportedOperationException();
	}
}
