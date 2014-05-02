package com.github.snoblind.winterface.util;

import org.w3c.dom.Node;

public final class NodeUtils {

	private NodeUtils() {
	}

	public static String describePath(final Node node) {
		final Node parentNode = node.getParentNode();
		if (parentNode == null) {
			return node.getNodeName();
		}
		final StringBuilder builder = new StringBuilder();
		describePath(node, builder);
		return builder.toString();
	}

	private static void describePath(Node node, StringBuilder builder) {
		final Node parentNode = node.getParentNode();
		if (parentNode != null) {
			describePath(parentNode, builder);
			builder.append(" > ");
		}
		final int index = indexInParentForNodeName(node);
		builder.append(node.getNodeName());
		if (index > 0) {
			builder.append("[").append(1 + index).append("]");
		}
	}

	private static int indexInParentForNodeName(final Node node) {
		final Node parentNode = node.getParentNode();
		int index = -1;
		if (parentNode != null) {
			for (Node childNode: NodeListUtils.iterable(parentNode.getChildNodes())) {
				if (childNode.getNodeName().equals(node.getNodeName())) {
					index++;
				}
				if (childNode == node) {
					return index;
				}
			}
		}
		return index;
	}
}
