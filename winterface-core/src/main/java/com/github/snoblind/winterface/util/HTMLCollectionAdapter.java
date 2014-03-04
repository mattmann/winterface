package com.github.snoblind.winterface.util;

import java.util.List;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLCollection;
import static org.apache.commons.lang.Validate.notNull;

public class HTMLCollectionAdapter implements HTMLCollection, NodeList {
	
	private final List<Node> nodes;

	public HTMLCollectionAdapter(List<Node> nodes) {
		notNull(this.nodes = nodes);
	}

	public int getLength() {
		return nodes.size();
	}

	public Node item(int index) {
		return nodes.get(index);
	}

	public Node namedItem(String name) {
		return NodeListUtils.namedItem(name, nodes);
	}

	public String toString() {
		return NodeListUtils.toString(nodes);
	}
}