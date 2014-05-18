package com.github.snoblind.winterface.rhino;

import static org.apache.commons.lang.Validate.notNull;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import com.github.snoblind.winterface.XMLHttpRequest;

public class XMLHttpRequestAdapter implements Scriptable {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(XMLHttpRequestAdapter.class);

	private final Context context;
	private final Scriptable scope;
	private final XMLHttpRequest request;

	public XMLHttpRequestAdapter(final Context context, final Scriptable scope, final XMLHttpRequest request) {
		notNull(this.context = context);
		notNull(this.scope = scope);
		notNull(this.request = request);
	}

	public String getClassName() {
		throw new UnsupportedOperationException();
	}

	public Object get(String name, Scriptable start) {
		LOGGER.debug("get({}, {})", name, start);
		if ("open".equals(name)) {
			return new MethodFunction(request, "open");
		}
		if ("readyState".equals(name)) {
			return request.getReadyState();
		}
		if ("responseText".equals(name)) {
			return request.getResponseText();
		}
		if ("send".equals(name)) {
			return new MethodFunction(request, "send");
		}
		if ("status".equals(name)) {
			return request.getStatus();
		}
		throw new UnsupportedOperationException();
	}

	public Object get(int index, Scriptable start) {
		throw new UnsupportedOperationException();
	}

	public boolean has(String name, Scriptable start) {
		LOGGER.debug("has({}, {})", name, start);
		if ("onreadystatechange".equals(name)) {
			return true;
		}
		throw new UnsupportedOperationException();
	}

	public boolean has(int index, Scriptable start) {
		throw new UnsupportedOperationException();
	}

	public void put(String name, Scriptable start, Object value) {
		LOGGER.debug("put({}, {}, {})", name, start, value);
		if ("onreadystatechange".equals(name)) {
			final Function function = (Function)value;
			final Scriptable scriptable = this;
			request.setOnreadystatechange(new EventListener() {
				public void handleEvent(Event event) {
					function.call(context, scope, scriptable, new Object[] { event });
				}
			});
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	public void put(int index, Scriptable start, Object value) {
		throw new UnsupportedOperationException();
	}

	public void delete(String name) {
		throw new UnsupportedOperationException();
	}

	public void delete(int index) {
		throw new UnsupportedOperationException();
	}

	public Scriptable getPrototype() {
		throw new UnsupportedOperationException();
	}

	public void setPrototype(Scriptable prototype) {
		throw new UnsupportedOperationException();
	}

	public Scriptable getParentScope() {
		throw new UnsupportedOperationException();
	}

	public void setParentScope(Scriptable parent) {
		throw new UnsupportedOperationException();
	}

	public Object[] getIds() {
		throw new UnsupportedOperationException();
	}

	public Object getDefaultValue(Class<?> hint) {
		throw new UnsupportedOperationException();
	}

	public boolean hasInstance(Scriptable instance) {
		throw new UnsupportedOperationException();
	}

}
