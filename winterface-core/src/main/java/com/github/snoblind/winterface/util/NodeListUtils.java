package com.github.snoblind.winterface.util;

import com.github.snoblind.winterface.NodeListIterable;
import com.github.snoblind.winterface.NodeListIterator;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import static java.util.Collections.emptyList;

public final class NodeListUtils {

	private NodeListUtils() {
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
}