package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.ExtendedHTMLCollection;
import java.util.AbstractList;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLCollection;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.apache.commons.lang.Validate.notNull;

public class RhinoHTMLCollection extends AbstractList<Node> implements ExtendedHTMLCollection, Scriptable {

	private final HTMLCollection collection;
	private final RhinoDocument document;
	private Scriptable prototype;
	private Scriptable parentScope;

	public RhinoHTMLCollection(final HTMLCollection collection, final RhinoDocument document) {
		notNull(collection);
		notNull(document);
		this.collection = collection;
		this.document = document;
	}

	public Node get(int index) {
		if (index < 0 || collection.getLength() <= index) {
			throw new IndexOutOfBoundsException(String.valueOf(index));
		}
		return item(index);
	}

	public int size() {
		return getLength();
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

	/**
     * Returns an array of ids for the properties of the object.
     *
     * @return an array of java.lang.Objects with an entry for every
     * listed property. Properties accessed via an integer index will
     * have a corresponding
     * Integer entry in the returned array. Properties accessed by
     * a String will have a String entry in the returned array.
     */
	public Object[] getIds() {
		final Object[] ids = new Object[2 * collection.getLength()];
		for (int i = 0; i < collection.getLength(); i += 2) {
			final Node node = collection.item(i);
			ids[i] = i;
			ids[i + 1] = resolveId(node);
		}
		return ids;
	}

	private String resolveId(Node node) {
		final NamedNodeMap attributes = node.getAttributes();
		if (attributes == null) {
			return EMPTY;
		}
		Node attribute = attributes.getNamedItem("id");
		if (attribute == null) {
			attribute = attributes.getNamedItem("name");
			if (attribute == null) {
				return EMPTY;
			}
		}
		return ((Attr) attribute).getValue();
	}

	public Object getDefaultValue(Class<?> hint) {
		if (ScriptRuntime.StringClass.equals(hint)) {
			return toString();
		}
		throw new IllegalArgumentException(String.valueOf(hint));
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

	public boolean has(String name, Scriptable start) {
		return null != collection.namedItem(name);
	}

	public boolean has(int index, Scriptable start) {
		return null != collection.item(index);
	}

    public boolean hasInstance(Scriptable instance) {
        return ScriptRuntime.jsDelegatesTo(instance, this);
    }	

	public Scriptable getPrototype() {
		return prototype;
	}

	public void setPrototype(Scriptable prototype) {
		this.prototype = prototype;
	}

	public Scriptable getParentScope() {
		return parentScope;
	}

	public void setParentScope(Scriptable parentScope) {
		this.parentScope = parentScope;
	}
}
