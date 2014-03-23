package com.github.snoblind.winterface.util;

import com.github.snoblind.winterface.ExtendedHTMLCollection;
import com.github.snoblind.winterface.NodeListIterable;
import com.github.snoblind.winterface.NodeListIterator;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLCollection;
import static java.util.Collections.emptyList;
import static org.apache.commons.lang.Validate.notNull;

public final class NodeListUtils {

	private NodeListUtils() {
	}

	public static ExtendedHTMLCollection toHTMLCollection(final List<Node> nodes) {
		return new ExtendedHTMLCollection() {

			public int getLength() {
				return nodes.size();
			}

			public Node item(int index) {
				return nodes.get(index);
			}

			public Node namedItem(String name) {
				throw new UnsupportedOperationException(String.format("namedItem(\"%s\")", name));
			}
		};
	}
	
	public static NodeList fromList(final List<? extends Node> list) {
		return new NodeList() {

			public Node item(int index) {
				return list.get(index);
			}

			public int getLength() {
				return list.size();
			}
		};
	}

	public static Iterable<Node> iterable(final NodeList nodes) {
		if (nodes == null) {
			return emptyList();
		}
		return new NodeListIterable(nodes);
	}

	public static Iterator<Node> iterator(final NodeList nodes) {
		return new NodeListIterator(nodes);
	}

	public static Node namedItem(final String name, final Iterable<Node> nodes) {
		notNull(name);
		notNull(nodes);
		for (Node node: nodes) {
			Attr attr = (Attr)node.getAttributes().getNamedItem("id");
			if (attr == null) {
				continue;
			}
			String id = attr.getValue();
			if (name.equals(id)) {
				return node;
			}
		}
		for (Node node: nodes) {
			Attr attr = (Attr)node.getAttributes().getNamedItem("name");
			if (attr == null) {
				continue;
			}
			String id = attr.getValue();
			if (name.equals(id)) {
				return node;
			}
		}
		return null;
	}

	public static String toString(final Iterable<Node> nodes) {
		StringBuilder builder = new StringBuilder("[");
		for (Iterator<Node> iterator = nodes.iterator(); iterator.hasNext(); ) {
			builder.append(iterator.next());
			if (iterator.hasNext()) {
				builder.append(", ");
			}
		}
		return builder.append("]").toString();
	}

	public static HTMLCollection toHTMLCollection(final NodeList nodes) {
		if (nodes instanceof HTMLCollection) {
			return (HTMLCollection) nodes;
		}
		return new HTMLCollection() {

			public int getLength() {
				return nodes.getLength();
			}

			public Node item(int index) {
				return nodes.item(index);
			}

			public Node namedItem(String name) {
				return NodeListUtils.namedItem(name, NodeListUtils.iterable(nodes));
			}

			public String toString() {
				return NodeListUtils.toString(NodeListUtils.iterable(nodes));
			}
		};
	}
}