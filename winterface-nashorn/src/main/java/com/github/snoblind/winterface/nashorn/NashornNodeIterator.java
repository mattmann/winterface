package com.github.snoblind.winterface.nashorn;

import com.github.snoblind.winterface.util.NodeListUtils;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;
import static org.apache.commons.lang.Validate.notNull;

public class NashornNodeIterator implements Iterator<Node>, NodeIterator {

	private final LinkedList<Node> queue = new LinkedList<Node>();
	private final Node root;
	private final int whatToShow;
	private final NodeFilter filter;
	private final boolean expandEntityReferences;

	public NashornNodeIterator(Node root, int whatToShow, NodeFilter filter, boolean expandEntityReferences) {
		notNull(root);
		this.root = root;
		this.whatToShow = whatToShow;
		this.filter = filter;
		this.expandEntityReferences = expandEntityReferences;
		for (Node childNode: NodeListUtils.reverseIterable(root.getChildNodes())) {
			queue.addLast(childNode);
		}
	}

	public Node getRoot() {
		return root;
	}

	public int getWhatToShow() {
		return whatToShow;
	}

	public NodeFilter getFilter() {
		return filter;
	}

	public boolean getExpandEntityReferences() {
		return expandEntityReferences;
	}

	public boolean hasNext() {
		try {
			queue.addFirst(next());
			return true;
		}
		catch (NoSuchElementException x) {
			return false;
		}
	}

	public Node next() {
		while (!queue.isEmpty()) {
			final Node node = queue.removeFirst();
			for (Node childNode: NodeListUtils.reverseIterable(node.getChildNodes())) {
				queue.addLast(childNode);
			}
			if (NodeFilter.FILTER_ACCEPT == filter.acceptNode(node)) {
				return node;
			}
		}
		throw new NoSuchElementException();
	}

	public Node nextNode() {
		try {
			return next();
		}
		catch (NoSuchElementException x) {
			return null;
		}
	}

	public Node previousNode() {
		throw new UnsupportedOperationException();
	}

	public void detach() {
		throw new UnsupportedOperationException();
	}
}
