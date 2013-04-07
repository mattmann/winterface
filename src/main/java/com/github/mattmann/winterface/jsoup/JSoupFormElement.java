package com.github.mattmann.winterface.jsoup;

import com.github.mattmann.winterface.Event;
import com.github.mattmann.winterface.EventListener;
import com.github.mattmann.winterface.HTMLCollection;
import com.github.mattmann.winterface.HTMLElement;
import com.github.mattmann.winterface.HTMLFormElement;
import com.github.mattmann.winterface.HTMLInputElement;
import com.github.mattmann.winterface.HTMLSelectElement;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsoup.Connection.Method;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Element;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.jsoup.Connection.KeyVal;
import static org.jsoup.Connection.Request;

public class JSoupFormElement extends JSoupElement implements HTMLFormElement {

	public JSoupFormElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
		setOnsubmit(new EventListener() {
			public void handleEvent(Event event) {
				JSoupWindow window = getWindow();
				JSoupLocation location = getLocation();
				try {
					URL href = new URL(location.getHref().toString());
					URL action = isBlank(getAction().toString()) ? href : new URL(href, getAction().toString());
					Method method = isBlank(getMethod().toString()) ? Method.GET : Method.valueOf(getMethod().toString().toUpperCase());
					Request request = location.connection.request();
					request.url(action);
					request.method(method);
					for (KeyVal keyVal: listData()) {
						request.data(keyVal);
					}
					window.document = new JSoupDocument(location.connection.get(), new JSoupEventDispatcher());
				}
				catch (IOException x) {
					throw new RuntimeException(x);
				}
			}
		});
	}
	
	protected List<KeyVal> listData() {
		HTMLCollection elements = getElements();
		if (elements.getLength() == 0) {
			return Collections.emptyList();
		}
		List<KeyVal> list = new ArrayList<KeyVal>(elements.getLength());
		for (int i = 0; i < elements.getLength(); i++) {
			KeyVal keyVal = resolveKeyVal((HTMLElement)elements.item(i));
			if (keyVal != null) {
				list.add(keyVal);
			}
		}
		return list;
	}
	
	protected KeyVal resolveKeyVal(HTMLElement element) {
		if (element instanceof HTMLInputElement) {
			return resolveKeyVal((HTMLInputElement)element);
		}
		if (element instanceof HTMLSelectElement) {
			return resolveKeyVal((HTMLSelectElement)element);
		}
		throw new UnsupportedOperationException(element.getOuterHTML().toString());
	}
	
	protected KeyVal resolveKeyVal(HTMLInputElement element) {
		return HttpConnection.KeyVal.create(element.getName().toString(), element.getValue().toString());
	}

	protected KeyVal resolveKeyVal(HTMLSelectElement element) {
		System.out.println(element.getOuterHTML());
		return HttpConnection.KeyVal.create(element.getName().toString(), element.getValue().toString());
	}

	protected JSoupLocation getLocation() {
		return (JSoupLocation)getWindow().getLocation();
	}

	protected JSoupWindow getWindow() {
		return (JSoupWindow)getOwnerDocument().getDefaultView();
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