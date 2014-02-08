package com.github.snoblind.winterface.jsoup;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLCollection;
import static org.apache.commons.lang.Validate.notNull;

public class JSoupCollection implements HTMLCollection, Scriptable {

	private final JSoupNode<?> node;
	private final Elements elements;

	public JSoupCollection(JSoupNode<?> node, Elements elements) {
		notNull(this.node = node);
		notNull(this.elements = elements);
	}

	public int getLength() {
		return elements.size();
	}

	public Node item(int index) {
		return node.wrap(elements.get(index));
	}

	public Node namedItem(String name) {
		notNull(name);
		for (Element element: elements) {
			if (element.attr("id").equals(name)) {
				return node.wrap(element);
			}
		}
		for (Element element: elements) {
			if (element.attr("name").equals(name)) {
				return node.wrap(element);
			}
		}
		return null;
	}

	public String getClassName() {
		return getClass().getName();
	}

	public Object get(String name, Scriptable start) {
		if ("item".equals(name)) {
			throw new UnsupportedOperationException(String.format("get(%s, %s)", name, start));
//			return new MethodFunction(this, "item");
		}
		if ("length".equals(name)) {
			return getLength();
		}
		throw new UnsupportedOperationException(String.format("get(%s, %s)", name, start));
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