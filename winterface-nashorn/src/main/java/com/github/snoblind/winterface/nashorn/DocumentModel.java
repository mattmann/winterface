package com.github.snoblind.winterface.nashorn;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import static org.apache.commons.lang.Validate.notNull;
import static org.apache.commons.lang.Validate.isTrue;

public class DocumentModel implements TreeModel {

	private final Set<TreeModelListener> listeners = new HashSet<>();
	private final NashornDocument document;

	public DocumentModel(NashornDocument document) {
		notNull(document);
		this.document = document;
		final EventListener listener = new EventListener() {
			public void handleEvent(final Event event) {
				switch (event.getType()) {
				case "DOMNodeRemoved":
					final Node node = (Node) event.getTarget();
					fireTreeNodeRemoved(node);
					break;
				default:
					throw new IllegalArgumentException(event.getType());
				}
			}
		};
		document.addEventListener("DOMSubtreeModified", listener, true);
		document.addEventListener("DOMNodeInserted", listener, true);
		document.addEventListener("DOMNodeRemoved", listener, true);
		document.addEventListener("DOMNodeRemovedFromDocument", listener, true);
		document.addEventListener("DOMNodeInsertedIntoDocument", listener, true);
		document.addEventListener("DOMAttrModified", listener, true);
		document.addEventListener("DOMCharacterDataModified", listener, true);
	}

	private void fireTreeNodeRemoved(final Node child) {
		if (!listeners.isEmpty()) {
			final TreePath path = getTreePath(child.getParentNode());
			final int childIndex = getIndexOfChild(child.getParentNode(), child);
			final TreeModelEvent event = new TreeModelEvent(this, path, new int[] { childIndex }, new Object[] { child });
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					isTrue(child.getParentNode() == null);
					for (TreeModelListener listener: listeners) {
						listener.treeNodesRemoved(event);
					}
				}
			});
		}
	}

	private TreePath getTreePath(Node node) {
		notNull(node);
		final LinkedList<Node> list = new LinkedList<Node>();
		while (!(node == null || node.equals(document))) {
			list.addFirst(node);
			node = node.getParentNode();
		}
		return new TreePath(list.toArray(new Node[list.size()]));
	}

	public Object getRoot() {
		return document.getDocumentElement();
	}

	public Object getChild(Object parent, int index) {
		return ((Node) parent).getChildNodes().item(index);
	}

	public int getChildCount(Object parent) {
		return ((Node) parent).getChildNodes().getLength();
	}

	public boolean isLeaf(Object node) {
		return ((Node) node).getFirstChild() == null;
	}

	public void valueForPathChanged(TreePath path, Object newValue) {
		System.err.printf("valueForPathChanged(%s, %s)%n", path, newValue);
		throw new UnsupportedOperationException();
	}

	public int getIndexOfChild(Object parent, Object child) {
		return getIndexOfChild((Node) parent, (Node) child);
	}

	private int getIndexOfChild(Node parent, Node child1) {
		final NodeList children = parent.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			final Node child2 = children.item(i);
			if (child2.isSameNode(child1)) {
				return i;
			}
		}
		return -1;
	}

	public void addTreeModelListener(TreeModelListener listener) {
		listeners.add(listener);
	}

	public void removeTreeModelListener(TreeModelListener listener) {
		listeners.remove(listener);
	}

}
