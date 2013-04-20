package com.github.mattmann.winterface.jsoup;

import com.github.mattmann.winterface.Event;
import com.github.mattmann.winterface.EventListener;
import com.github.mattmann.winterface.HTMLCollection;
import com.github.mattmann.winterface.HTMLElement;
import com.github.mattmann.winterface.HTMLFormElement;
import com.github.mattmann.winterface.HTMLInputElement;
import com.github.mattmann.winterface.HTMLSelectElement;
import com.github.mattmann.winterface.Location;
import com.github.mattmann.winterface.Window;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Connection.Method;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Element;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.jsoup.Connection.KeyVal;

public class JSoupFormElement extends JSoupElement implements HTMLFormElement {

	protected static final Log LOG = LogFactory.getLog(JSoupFormElement.class);

	public JSoupFormElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
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
		if (LOG.isDebugEnabled()) {
			Writer buffer = new StringWriter();
			PrintWriter writer = new PrintWriter(buffer, true);
			for (KeyVal keyVal: list) {
				writer.printf("%s: %s\n", keyVal.key(), keyVal.value());
			}
			LOG.debug(buffer.toString());
		}
		return list;
	}
	
	protected KeyVal resolveKeyVal(HTMLElement element) {
		if (LOG.isDebugEnabled()) {
			LOG.debug(element.getOuterHTML());
		}
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
		return HttpConnection.KeyVal.create(element.getName().toString(), element.getValue().toString());
	}

	protected Location getLocation() {
		return getWindow().getLocation();
	}

	protected Window getWindow() {
		return getOwnerDocument().getDefaultView();
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
		Window window = getWindow();
		JSoupLocation location = (JSoupLocation)getLocation();
		try {
			location.submit(getAction(), getMethod(), listData());
		}
		catch (IOException x) {
			throw new RuntimeException(x);
		}
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