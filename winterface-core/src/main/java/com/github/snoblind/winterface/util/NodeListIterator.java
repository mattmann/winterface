package com.github.snoblind.winterface.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import static org.apache.commons.lang.Validate.notNull;

public class NodeListIterator implements Iterator<Node> {

	private final NodeList nodeList;
	private int index = 0;

	public NodeListIterator(NodeList nodeList) {
		notNull(nodeList);
		this.nodeList = nodeList;
	}

	public boolean hasNext() {
		return index < nodeList.getLength();
	}

	public Node next() {
		if (hasNext()) {
			return nodeList.item(index++);
		}
		else {
			throw new NoSuchElementException();
		}
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}
}