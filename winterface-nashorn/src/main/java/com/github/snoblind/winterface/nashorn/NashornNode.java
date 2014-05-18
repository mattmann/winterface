package com.github.snoblind.winterface.nashorn;

import com.github.snoblind.winterface.event.ExtendedEvent;
import com.github.snoblind.winterface.util.NodeListUtils;
import java.util.Iterator;
import java.util.LinkedList;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;
import org.w3c.dom.events.EventTarget;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.w3c.dom.DOMException.INVALID_STATE_ERR;

public abstract class NashornNode implements Node, EventTarget {

	private final LinkedList<Node> children = new LinkedList<Node>();
	private NashornNode parentNode;
	
	public Node getParentNode() {
		return parentNode;
	}

	public NodeList getChildNodes() {
		return new NodeList() {

			public Node item(int index) {
				try {
					return children.get(index);
				}
				catch (IndexOutOfBoundsException x) {
					return null;
				}
			}

			public int getLength() {
				return children.size();
			}
		};
	}

	public Node getFirstChild() {
		return children.isEmpty() ? null : children.getFirst();
	}

	public Node getLastChild() {
		return children.isEmpty() ? null : children.getLast();
	}

	public Node getPreviousSibling() {
		throw new UnsupportedOperationException();
	}

	public Node getNextSibling() {
		if (parentNode == null) {
			return null;
		}
		final Iterator<Node> iterator = parentNode.children.iterator();
		while (iterator.hasNext()) {
			final Node node = iterator.next();
			if (node == this && iterator.hasNext()) {
				return iterator.next();
			}
		}
		return null;
	}

	public Node insertBefore(Node newChild, Node refChild) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Node removeChild(Node oldChild) {
		return removeChild((NashornNode) oldChild);
	}

	private NashornNode removeChild(final NashornNode oldChild) {
		final ExtendedEvent event = getOwnerDocument().getEventDispatcher().createEvent("Event");
		event.setTarget(oldChild);
		event.initEvent("DOMNodeRemoved", true, true);
		dispatchEvent(event);
		if (children.remove(oldChild)) {
			oldChild.parentNode = null;
			return oldChild;
		}
		return null;
	}

	public abstract NashornDocument getOwnerDocument();

	public Node appendChild(final Node newChild) {
		return appendChild((NashornNode) newChild);
	}
	
	private NashornNode appendChild(final NashornNode newChild) {
		if (!(newChild.parentNode == null || newChild.parentNode == this)) {
			newChild.parentNode.removeChild(newChild);
		}
		if (children.add(newChild)) {
			newChild.parentNode = this;
			return newChild;
			
		}
		return null;
	}

	public boolean isSameNode(final Node node) {
		return equals(node);
	}

	public boolean hasChildNodes() {
		throw new UnsupportedOperationException();
	}

	public Node cloneNode(boolean deep) {
		throw new UnsupportedOperationException();
	}

	public void normalize() {
		throw new UnsupportedOperationException();
	}

	public boolean isSupported(String feature, String version) {
		throw new UnsupportedOperationException();
	}

	public String getPrefix() {
		throw new UnsupportedOperationException();
	}

	public void setPrefix(String prefix) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public boolean hasAttributes() {
		throw new UnsupportedOperationException();
	}

	public String getBaseURI() {
		throw new UnsupportedOperationException();
	}

	public short compareDocumentPosition(Node other) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public String getTextContent() throws DOMException {
		switch (getNodeType()) {
		case DOCUMENT_NODE:
		case DOCUMENT_TYPE_NODE:
		case NOTATION_NODE:
			return null;
		case TEXT_NODE:
		case CDATA_SECTION_NODE:
		case COMMENT_NODE:
		case PROCESSING_INSTRUCTION_NODE:
			return getNodeValue();
		case ELEMENT_NODE:
		case ATTRIBUTE_NODE:
		case ENTITY_NODE:
		case ENTITY_REFERENCE_NODE:
		case DOCUMENT_FRAGMENT_NODE:
			final NodeList childNodes = getChildNodes();
			if (0 == childNodes.getLength()) {
				return EMPTY;
			}
			final StringBuilder builder = new StringBuilder();
			for (Node childNode: NodeListUtils.iterable(childNodes)) {
				if (COMMENT_NODE != childNode.getNodeType() && PROCESSING_INSTRUCTION_NODE != childNode.getNodeType()) {
					builder.append(childNode.getTextContent());
				}
			}
			return builder.toString();
		default:
			throw new DOMException(INVALID_STATE_ERR, String.format("Unexpected node type: %d.", getNodeType()));
		}
	}

	public void setTextContent(final String textContent) throws DOMException {
		while (getFirstChild() != null) {
			removeChild(getFirstChild());
		}
		appendChild(getOwnerDocument().createTextNode(textContent));
	}

	public String lookupPrefix(String namespaceURI) {
		throw new UnsupportedOperationException();
	}

	public boolean isDefaultNamespace(String namespaceURI) {
		throw new UnsupportedOperationException();
	}

	public String lookupNamespaceURI(String prefix) {
		throw new UnsupportedOperationException();
	}

	public boolean isEqualNode(Node arg) {
		throw new UnsupportedOperationException();
	}

	public Object getFeature(String feature, String version) {
		throw new UnsupportedOperationException();
	}

	public Object setUserData(String key, Object data, UserDataHandler handler) {
		throw new UnsupportedOperationException();
	}

	public Object getUserData(String key) {
		throw new UnsupportedOperationException();
	}
}
