package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.ExtendedHTMLCollection;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.Node;
import static org.apache.commons.lang.Validate.notNull;

public class RhinoHTMLCollection extends DelegatingScriptable implements ExtendedHTMLCollection, Scriptable {

	private final ExtendedHTMLCollection collection;

	public RhinoHTMLCollection(final ExtendedHTMLCollection collection) {
		notNull(collection);
		this.scriptable = new ReflectingScriptableObject(this, ExtendedHTMLCollection.class);
		this.collection = collection;
	}

	public int getLength() {
		return collection.getLength();
	}

	public Node item(int index) {
		throw new UnsupportedOperationException();
	}

	public Node namedItem(String name) {
		throw new UnsupportedOperationException();
	}
}
