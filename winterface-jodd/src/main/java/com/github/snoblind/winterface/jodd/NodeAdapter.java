package com.github.snoblind.winterface.jodd;

import jodd.lagarto.dom.Node;
import jodd.lagarto.dom.Node.NodeType;
import org.apache.commons.lang.Validate;
import static org.w3c.dom.Node.*;

public class NodeAdapter {

	public static NodeType nodeType(org.w3c.dom.Node node) {
		if (node == null) {
			return null;
		}
		switch (node.getNodeType()) {
		case TEXT_NODE:
			return NodeType.TEXT;
		default:
			throw new IllegalArgumentException(Short.toString(node.getNodeType()));
		}
	}

	public static String nodeName(org.w3c.dom.Node node) {
		return node == null ? null : node.getNodeName();
	}

	public static org.w3c.dom.Node unwrap(Node node) {
		Validate.notNull(node);
		if (node instanceof ElementAdapter) {
			return ((ElementAdapter) node).element;
		}
		throw new IllegalArgumentException(node.getClass().getName());
	}
}