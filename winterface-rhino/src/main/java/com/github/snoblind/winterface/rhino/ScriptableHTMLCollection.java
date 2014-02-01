package com.github.snoblind.winterface.rhino;

import org.mozilla.javascript.Scriptable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLCollection;
import static org.apache.commons.lang.Validate.notNull;

public class ScriptableHTMLCollection implements HTMLCollection, Scriptable {

	private static final Logger logger = LoggerFactory.getLogger(ScriptableHTMLCollection.class);

	private final HTMLCollection collection;

	public ScriptableHTMLCollection(HTMLCollection collection) {
		notNull(this.collection = collection);
	}

	public int getLength() {
		return collection.getLength();
	}

	public Node item(int index) {
		return collection.item(index);
	}

	public Node namedItem(String name) {
		return collection.namedItem(name);
	}

	public String getClassName() {
		throw new UnsupportedOperationException();
	}

	public Object get(String name, Scriptable start) {
		logger.debug("get({}, {})", name, start);
		if ("length".equals(name)) {
			return collection.getLength();
		}
		throw new UnsupportedOperationException();
	}

	public Object get(int index, Scriptable start) {
		logger.debug("get({}, {})", index, start);
		return collection.item(index);
	}

	public boolean has(String name, Scriptable start) {
		throw new UnsupportedOperationException();
	}

	public boolean has(int index, Scriptable start) {
		throw new UnsupportedOperationException();
	}

	public void put(String name, Scriptable start, Object value) {
		throw new UnsupportedOperationException();
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
