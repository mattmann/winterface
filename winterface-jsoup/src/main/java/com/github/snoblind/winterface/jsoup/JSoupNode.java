package com.github.snoblind.winterface.jsoup;

import com.github.snoblind.winterface.HTMLCollectionAdapter;
import com.github.snoblind.winterface.event.EventDispatcher;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.select.NodeVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.CharacterData;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.UserDataHandler;
import static org.apache.commons.lang.Validate.notNull;

public abstract class JSoupNode<T extends org.jsoup.nodes.Node> implements Node {
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(JSoupNode.class);

	protected final T node;
	protected JSoupDocument ownerDocument;
	
	public JSoupNode(final T node, final JSoupDocument ownerDocument) {
		notNull(this.node = node);
		notNull(this.ownerDocument = ownerDocument);
	}

	protected JSoupNode(final T node) {
		notNull(this.node = node);
	}

	public EventDispatcher getEventDispatcher() {
		return ownerDocument.getEventDispatcher();
	}
	
	public String getNodeName() {
		return node.nodeName();
	}

	public Node getParentNode() {
		org.jsoup.nodes.Node parentNode = node.parent();
		if (parentNode == null) {
			return null;
		}
		return adapt(parentNode);
	}
	
	public JSoupDocument getOwnerDocument() {
		return ownerDocument;
	}

	public String getNodeValue() {
		if (this instanceof Attr) {
			return ((Attr) this).getValue();
		}
		if (this instanceof CharacterData) {
			return ((CharacterData) this).getData();
		}
		if (this instanceof ProcessingInstruction) {
			return ((ProcessingInstruction) this).getData();
		}
		return null;
	}

	public void setNodeValue(String nodeValue) {
		throw new UnsupportedOperationException();
	}

	public String getInnerText() {
		return ownerDocument.getParser().getInnerText(this);
	}

	public NodeList getChildNodes() {
		final List<org.jsoup.nodes.Node> childNodes = node.childNodes();
		return new NodeList() {

			public Node item(int index) {
				return adapt(childNodes.get(index));
			}

			public int getLength() {
				return childNodes.size();
			}
		};
	}

	protected Node adapt(org.jsoup.nodes.Node node) {
		return ownerDocument.getNodeAdapterFactory().adapt(node, ownerDocument);
	}
	
	public Node getFirstChild() {
		if (node.childNodeSize() == 0) {
			return null;
		}
		return adapt(node.childNode(0));
	}

	public Node getLastChild() {
		final int length = node.childNodeSize();
		if (length == 0) {
			return null;
		}
		return adapt(node.childNode(length - 1));
	}

	public Node getPreviousSibling() {
		org.jsoup.nodes.Node previousSibling = node.previousSibling();
		if (previousSibling == null) {
			return null;
		}
		return adapt(previousSibling);
	}

	public Node getNextSibling() {
		org.jsoup.nodes.Node nextSibling = node.nextSibling();
		if (nextSibling == null) {
			return null;
		}
		return adapt(nextSibling);
	}

	public NamedNodeMap getAttributes() {
		throw new UnsupportedOperationException(getClass().getName());
	}

	public Node insertBefore(Node newChild, Node refChild) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Node removeChild(Node oldChild) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Node appendChild(Node newChild) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public boolean hasChildNodes() {
		return node.childNodeSize() > 0;
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

	public String getNamespaceURI() {
		return null;
	}

	public String getPrefix() {
		throw new UnsupportedOperationException();
	}

	public void setPrefix(String prefix) {
		throw new UnsupportedOperationException();
	}

	public String getLocalName() {
		if (this instanceof Element) {
			return getNodeName();
		}
		if (this instanceof Attr) {
			return getNodeName();
		}
		return null;
	}

	public boolean hasAttributes() {
		return node.attributes().size() > 0;
	}

	public String getBaseURI() {
		throw new UnsupportedOperationException();
	}

	public short compareDocumentPosition(Node other) throws org.w3c.dom.DOMException {
		throw new UnsupportedOperationException();
	}

	public String getTextContent() throws org.w3c.dom.DOMException {
		throw new UnsupportedOperationException();
	}

	public void setTextContent(String textContent)
			throws org.w3c.dom.DOMException {
		throw new UnsupportedOperationException();
	}

	public boolean isSameNode(Node other) {
		throw new UnsupportedOperationException();
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

	protected HTMLCollectionAdapter collect(final NodeFilter filter) {
		final List<Node> list = new ArrayList<Node>();
		node.traverse(new NodeVisitor() {
			public void head(org.jsoup.nodes.Node node, int depth) {
				if (filter.accept(node)) {
					list.add(adapt(node));
				}
			}
			public void tail(org.jsoup.nodes.Node node, int depth) {}
		});
		return new HTMLCollectionAdapter(list);
	}
}
