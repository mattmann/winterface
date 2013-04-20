package com.github.mattmann.winterface.jsoup;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.select.NodeVisitor;
import com.github.mattmann.winterface.DOMException;
import com.github.mattmann.winterface.HTMLElement;
import com.github.mattmann.winterface.NamedNodeMap;
import com.github.mattmann.winterface.Node;
import com.github.mattmann.winterface.NodeList;
import com.github.mattmann.winterface.CharacterData;

import static org.apache.commons.lang.Validate.notNull;

public abstract class JSoupNode<T extends org.jsoup.nodes.Node> implements Node {
	
	protected static final Log LOG = LogFactory.getLog(JSoupNode.class);

	protected final T node;
	
	public JSoupNode(T node) {
		notNull(this.node = node);
	}

	public CharSequence getNodeName() {
		return node.nodeName();
	}

	public Node getParentNode() {
		org.jsoup.nodes.Node parentNode = node.parent();
		if (parentNode == null) {
			return null;
		}
		return wrap(parentNode);
	}
	
	public abstract JSoupDocument getOwnerDocument();

	public CharSequence getNodeValue() {
		throw new UnsupportedOperationException();
	}

	public void setNodeValue(CharSequence nodeValue) {
		throw new UnsupportedOperationException();
	}

	public short getNodeType() {
		throw new UnsupportedOperationException();
	}

	public static CharSequence getInnerText(Node node) {
		if (node instanceof CharacterData) {
			return ((CharacterData)node).getData();
		}
		StringBuilder builder = new StringBuilder();
		NodeList childNodes = node.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			JSoupNode<?> child = (JSoupNode<?>)childNodes.item(i);
			builder.append(child.getInnerText());
		}
		return builder.toString();
	}

	public CharSequence getInnerText() {
		return JSoupNode.getInnerText(this);
	}

	public NodeList getChildNodes() {
		final List<org.jsoup.nodes.Node> childNodes = node.childNodes();
		return new NodeList() {

			public Node item(int index) {
				return wrap(childNodes.get(index));
			}

			public int getLength() {
				return childNodes.size();
			}
		};
	}

	public Node getFirstChild() {
		throw new UnsupportedOperationException();
	}

	public Node getLastChild() {
		throw new UnsupportedOperationException();
	}

	public Node getPreviousSibling() {
		throw new UnsupportedOperationException();
	}

	public Node getNextSibling() {
		throw new UnsupportedOperationException();
	}

	public NamedNodeMap getAttributes() {
		throw new UnsupportedOperationException();
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
		throw new UnsupportedOperationException();
	}

	public Node cloneNode(boolean deep) {
		throw new UnsupportedOperationException();
	}

	public void normalize() {
		throw new UnsupportedOperationException();
	}

	public boolean isSupported(CharSequence feature, CharSequence version) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getNamespaceURI() {
		throw new UnsupportedOperationException();
	}

	public CharSequence getPrefix() {
		throw new UnsupportedOperationException();
	}

	public void setPrefix(CharSequence prefix) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getLocalName() {
		throw new UnsupportedOperationException();
	}

	public boolean hasAttributes() {
		return node.attributes().size() > 0;
	}

	protected HTMLCollectionAdapter collect(final NodeFilter filter) {
		final List<Node> list = new ArrayList<Node>();
		node.traverse(new NodeVisitor() {
			public void head(org.jsoup.nodes.Node node, int depth) {
				if (filter.accept(node)) {
					list.add(wrap(node));
				}
			}
			public void tail(org.jsoup.nodes.Node node, int depth) {}
		});
		return new HTMLCollectionAdapter(list);
	}

	protected Node wrap(org.jsoup.nodes.Node node) {
		notNull(node);
		if (node instanceof org.jsoup.nodes.Document) {
			if (getOwnerDocument().node.equals(node)) {
				return getOwnerDocument();
			}
			throw new IllegalArgumentException(node.getClass().getName());
		}
		if (node instanceof org.jsoup.nodes.Element) {
			return wrap((org.jsoup.nodes.Element)node);
		}
		if (node instanceof org.jsoup.nodes.TextNode) {
			return new JSoupText((org.jsoup.nodes.TextNode)node, getOwnerDocument());
		}
		throw new IllegalArgumentException(node.getClass().getName());
	}

	protected HTMLElement wrap(final org.jsoup.nodes.Element element) {
		final String tagName = element.tagName();
		final JSoupDocument ownerDocument = getOwnerDocument();
		if ("a".equals(tagName)) {
			return new JSoupAnchorElement(element, ownerDocument);
		}
		if ("applet".equals(tagName)) {
			return new JSoupAppletElement(element, ownerDocument);
		}
		if ("img".equals(tagName)) {
			return new JSoupImageElement(element, ownerDocument);
		}
		if ("input".equals(tagName)) {
			return new JSoupInputElement(element, ownerDocument);
		}
		if ("form".equals(tagName)) {
			return new JSoupFormElement(element, ownerDocument);
		}
		if ("meta".equals(tagName)) {
			return new JSoupMetaElement(element, ownerDocument);
		}
		if ("option".equals(tagName)) {
			return new JSoupOptionElement(element, ownerDocument);
		}
		if ("select".equals(tagName)) {
			return new JSoupSelectElement(element, ownerDocument);
		}
		if ("table".equals(tagName)) {
			return new JSoupTableElement(element, ownerDocument);
		}
		if ("tbody".equals(tagName)) {
			return new JSoupTableSectionElement(element, ownerDocument);
		}
		if ("td".equals(tagName)) {
			return new JSoupTableCellElement(element, ownerDocument);
		}
		if ("title".equals(tagName)) {
			return new JSoupTitleElement(element, ownerDocument);
		}
		if ("tr".equals(tagName)) {
			return new JSoupTableRowElement(element, ownerDocument);
		}
		return new JSoupElement(element, ownerDocument);
	}
}
