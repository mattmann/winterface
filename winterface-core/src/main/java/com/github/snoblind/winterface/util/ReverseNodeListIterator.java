package com.github.snoblind.winterface.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import static org.apache.commons.lang.Validate.notNull;

public class ReverseNodeListIterator implements Iterator<Node> {

	private final NodeList nodeList;
	private int index;

	public ReverseNodeListIterator(NodeList nodeList) {
		notNull(nodeList);
		this.nodeList = nodeList;
		this.index = nodeList.getLength() - 1;
	}

	public boolean hasNext() {
		return index >= 0;
	}

	public Node next() {
		if (index < 0) {
			throw new NoSuchElementException();
		}
		return nodeList.item(index--);
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}
}
