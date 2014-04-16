package com.github.snoblind.winterface.nashorn;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang.ObjectUtils;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import static org.apache.commons.lang.Validate.notNull;

public class NashornNamedNodeMap implements NamedNodeMap, Iterable<Node> {

	private Map<Key, Node> map = new LinkedHashMap<Key, Node>();

	public Iterator<Node> iterator() {
		return map.values().iterator();
	}
	
	public Node getNamedItem(String name) {
		return getNamedItemNS(null, name);
	}

	public Node getNamedItemNS(String namespaceURI, String localName) throws DOMException {
		return map.get(new Key(namespaceURI, localName));
	}

	public Node setNamedItem(Node node) throws DOMException {
		return setNamedItemNS(node);
	}

	public Node setNamedItemNS(Node node) throws DOMException {
		return map.put(new Key(node.getNamespaceURI(), node.getLocalName()), node);
	}

	public Node removeNamedItem(String name) throws DOMException {
		return removeNamedItemNS(null, name);
	}

	public Node removeNamedItemNS(String namespaceURI, String localName) throws DOMException {
		return map.remove(new Key(namespaceURI, localName));
	}

	public Node item(int index) {
		if (index < 0 || index >= map.size()) {
			return null;
		}
		final Iterator<Node> iterator = map.values().iterator();
		for (int i = 0; iterator.hasNext(); i++) {
			final Node node = iterator.next();
			if (i == index) {
				return node;
			}
		}
		return null;
	}

	public int getLength() {
		return map.size();
	}

	private static class Key {

		private final String namespaceURI;
		private final String localName;

		public Key(String namespaceURI, String localName) {
			notNull(localName);
			this.namespaceURI = namespaceURI;
			this.localName = localName;
		}

		public int hashCode() {
			return namespaceURI == null ? 0 : namespaceURI.hashCode() + localName.hashCode();
		}

		public boolean equals(Object object) {
			return object instanceof Key && equals((Key) object);
		}

		private boolean equals(Key key) {
			return ObjectUtils.equals(namespaceURI, key.namespaceURI) && key.localName.equals(localName);
		}
	}
}
