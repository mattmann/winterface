package com.github.snoblind.winterface.util;

import java.util.Iterator;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import static org.apache.commons.lang.Validate.notNull;

public class NodeListIterable implements Iterable<Node> {

	private final NodeList nodeList;

	public NodeListIterable(NodeList nodeList) {
		notNull(nodeList);
		this.nodeList = nodeList;
	}

	public Iterator<Node> iterator() {
		return new NodeListIterator(nodeList);
	}
}
