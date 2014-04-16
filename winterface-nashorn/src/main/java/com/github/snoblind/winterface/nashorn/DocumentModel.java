package com.github.snoblind.winterface.nashorn;

import java.util.HashSet;
import java.util.Set;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import static org.apache.commons.lang.Validate.notNull;

public class DocumentModel implements TreeModel {

	private final Set<TreeModelListener> listeners = new HashSet<>();
	private final Document document;

	public DocumentModel(Document document) {
		notNull(document);
		this.document = document;
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
