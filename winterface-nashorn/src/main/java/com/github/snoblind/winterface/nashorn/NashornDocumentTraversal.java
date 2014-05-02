package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;
import org.w3c.dom.traversal.TreeWalker;

public class NashornDocumentTraversal implements DocumentTraversal {

	public NodeIterator createNodeIterator(Node root, int whatToShow, NodeFilter filter, boolean entityReferenceExpansion) throws DOMException {
		return new NashornNodeIterator(root, whatToShow, filter, entityReferenceExpansion);
	}

	public TreeWalker createTreeWalker(Node root, int whatToShow, NodeFilter filter, boolean entityReferenceExpansion) throws DOMException {
		throw new UnsupportedOperationException();
	}
}