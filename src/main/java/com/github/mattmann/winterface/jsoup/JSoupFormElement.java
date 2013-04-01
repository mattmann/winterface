package com.github.mattmann.winterface.jsoup;


import org.jsoup.nodes.Element;

import com.github.mattmann.winterface.Event;
import com.github.mattmann.winterface.EventListener;
import com.github.mattmann.winterface.HTMLCollection;
import com.github.mattmann.winterface.HTMLFormElement;

public class JSoupFormElement extends JSoupElement implements HTMLFormElement {

	public JSoupFormElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public HTMLCollection getElements() {
		return collect(new FormElementFilter());
	}
	
	protected boolean isFormElement(Element element) {
		return false;
	}

	public int getLength() {
		return getElements().getLength();
	}

	public CharSequence getName() {
		return getAttribute("name");
	}

	public void setName(CharSequence name) {
		setAttribute("name", name);
	}

	public CharSequence getAcceptCharset() {
		return getAttribute("accept-charset");
	}

	public void setAcceptCharset(CharSequence acceptCharset) {
		setAttribute("accept-charset", acceptCharset);
	}

	public CharSequence getAction() {
		return getAttribute("action");
	}

	public void setAction(CharSequence action) {
		setAttribute("action", action);
	}

	public CharSequence getEnctype() {
		return getAttribute("enctype");
	}

	public void setEnctype(CharSequence enctype) {
		setAttribute("enctype", enctype);
	}

	public CharSequence getMethod() {
		return getAttribute("method");
	}

	public void setMethod(CharSequence method) {
		setAttribute("method", method);
	}

	public CharSequence getTarget() {
		return getAttribute("target");
	}

	public void setTarget(CharSequence target) {
		setAttribute("target", target);
	}

	public void submit() {
		Event event = ownerDocument.createEvent("Event");
		event.initEvent("submit", true, true);
		dispatchEvent(event);
	}

	public void reset() {
		Event event = ownerDocument.createEvent("Event");
		event.initEvent("reset", true, true);
		dispatchEvent(event);
	}
	
	private EventListener onsubmit;

	public EventListener getOnsubmit() {
		return onsubmit;
	}

	public void setOnsubmit(EventListener onsubmit) {
		if (this.onsubmit != null) {
			ownerDocument.eventDispatcher.removeEventListener(this, "submit", this.onsubmit, false);
		}
		this.onsubmit = onsubmit;
		if (this.onsubmit != null) {
			ownerDocument.eventDispatcher.addEventListener(this, "submit", this.onsubmit, false);
		}
	}

	private EventListener onreset;

	public EventListener getOnreset() {
		return onreset;
	}

	public void setOnreset(EventListener onreset) {
		if (this.onreset != null) {
			ownerDocument.eventDispatcher.removeEventListener(this, "reset", this.onreset, false);
		}
		this.onreset = onreset;
		if (this.onreset != null) {
			ownerDocument.eventDispatcher.addEventListener(this, "reset", this.onreset, false);
		}
	}
}