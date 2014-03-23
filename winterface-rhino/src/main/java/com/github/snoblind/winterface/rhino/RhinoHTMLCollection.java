package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.ExtendedHTMLCollection;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLCollection;
import static org.apache.commons.lang.Validate.notNull;

public class RhinoHTMLCollection implements ExtendedHTMLCollection, Scriptable {

	private final HTMLCollection collection;
	private final RhinoDocument document;

	public RhinoHTMLCollection(final HTMLCollection collection, final RhinoDocument document) {
		notNull(collection);
		notNull(document);
		this.collection = collection;
		this.document = document;
	}

	public int getLength() {
		return collection.getLength();
	}

	public Node item(int index) {
		final Node node = collection.item(index);
		if (node == null) {
			return null;
		}
		return document.adapt(node);
	}

	public Node namedItem(String name) {
		final Node node = collection.namedItem(name);
		if (node == null) {
			return null;
		}
		return document.adapt(node);
	}

	public String getClassName() {
		return getClass().getName();
	}

	public Object get(String name, Scriptable start) {
		return namedItem(name);
	}

	public Object get(int index, Scriptable start) {
		return item(index);
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
