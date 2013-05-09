package com.github.mattmann.winterface.jsoup;

import com.github.mattmann.winterface.HTMLElement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.select.NodeVisitor;
import org.w3c.dom.CharacterData;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

import static org.apache.commons.lang.Validate.notNull;

public abstract class JSoupNode<T extends org.jsoup.nodes.Node> implements Node {
	
	protected static final Log LOG = LogFactory.getLog(JSoupNode.class);

	protected final T node;
	
	public JSoupNode(T node) {
		notNull(this.node = node);
	}

	public String getNodeName() {
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

	public String getNodeValue() {
		throw new UnsupportedOperationException(getClass().getName());
	}

	public void setNodeValue(String nodeValue) {
		throw new UnsupportedOperationException();
	}

//	public short getNodeType() {
//		if (node instanceof org.jsoup.nodes.Element) {
//			return ELEMENT_NODE;
//		}
//		if (node instanceof org.jsoup.nodes.TextNode) {
//			return TEXT_NODE;
//		}
//		if (node instanceof org.jsoup.nodes.Comment) {
//			return COMMENT_NODE;
//		}
//		if (node instanceof org.jsoup.nodes.DataNode) {
//			return TEXT_NODE;
//		}
//		throw new UnsupportedOperationException(node.getClass().getName());
//	}

	public static String getInnerText(Node node) {
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

	public String getInnerText() {
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
		if (node.childNodeSize() == 0) {
			return null;
		}
		return wrap(node.childNode(0));
	}

	public Node getLastChild() {
		final int length = node.childNodeSize();
		if (length == 0) {
			return null;
		}
		return wrap(node.childNode(length - 1));
	}

	public Node getPreviousSibling() {
		org.jsoup.nodes.Node previousSibling = node.previousSibling();
		if (previousSibling == null) {
			return null;
		}
		return wrap(previousSibling);
	}

	public Node getNextSibling() {
		org.jsoup.nodes.Node nextSibling = node.nextSibling();
		if (nextSibling == null) {
			return null;
		}
		return wrap(nextSibling);
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
		throw new UnsupportedOperationException();
	}

	public String getPrefix() {
		throw new UnsupportedOperationException();
	}

	public void setPrefix(String prefix) {
		throw new UnsupportedOperationException();
	}

	public String getLocalName() {
		throw new UnsupportedOperationException();
	}

	public boolean hasAttributes() {
		return node.attributes().size() > 0;
	}
	public String getBaseURI() {
		throw new UnsupportedOperationException();
	}

	public short compareDocumentPosition(Node other)
			throws org.w3c.dom.DOMException {
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
					list.add(wrap(node));
				}
			}
			public void tail(org.jsoup.nodes.Node node, int depth) {}
		});
		return new HTMLCollectionAdapter(list);
	}

	protected Node wrap(org.jsoup.nodes.Node node) {
		notNull(node);
		final JSoupDocument ownerDocument = getOwnerDocument();
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
			return new JSoupText((org.jsoup.nodes.TextNode)node, ownerDocument);
		}
		if (node instanceof org.jsoup.nodes.Comment) {
			return new JSoupComment((org.jsoup.nodes.Comment)node, ownerDocument);
		}
		if (node instanceof org.jsoup.nodes.DataNode) {
			return new JSoupCDATASection((org.jsoup.nodes.DataNode)node, ownerDocument);
		}
		if (node instanceof org.jsoup.nodes.DocumentType) {
			return new JSoupDocumentType((org.jsoup.nodes.DocumentType)node, ownerDocument);
		}
		throw new IllegalArgumentException(node.getClass().getName());
	}

	protected static Pattern HEADING_PATTERN = Pattern.compile("h[1-6]", Pattern.CASE_INSENSITIVE);
	
	protected HTMLElement wrap(final org.jsoup.nodes.Element element) {
		final String tagName = element.tagName();
		final JSoupDocument ownerDocument = getOwnerDocument();
		if ("a".equals(tagName)) {
			return new JSoupAnchorElement(element, ownerDocument);
		}
		if ("applet".equals(tagName)) {
			return new JSoupAppletElement(element, ownerDocument);
		}
		if ("b".equals(tagName)) {
			return new JSoupElement(element, ownerDocument);
		}
		if ("body".equals(tagName)) {
			return new JSoupBodyElement(element, ownerDocument);
		}
		if ("br".equals(tagName)) {
			return new JSoupBRElement(element, ownerDocument);
		}
		if ("div".equals(tagName)) {
			return new JSoupDivElement(element, ownerDocument);
		}
		if ("font".equals(tagName)) {
			return new JSoupFontElement(element, ownerDocument);
		}
		if ("label".equals(tagName)) {
			return new JSoupLabelElement(element, ownerDocument);
		}
		if ("li".equals(tagName)) {
			return new JSoupLIElement(element, ownerDocument);
		}
		if ("link".equals(tagName)) {
			return new JSoupLinkElement(element, ownerDocument);
		}
		if ("img".equals(tagName)) {
			return new JSoupImageElement(element, ownerDocument);
		}
		if ("input".equals(tagName)) {
			return new JSoupInputElement(element, ownerDocument);
		}
		if ("footer".equals(tagName)) {
			return new JSoupFooterElement(element, ownerDocument);
		}
		if ("form".equals(tagName)) {
			return new JSoupFormElement(element, ownerDocument);
		}
		if ("head".equals(tagName)) {
			return new JSoupHeadElement(element, ownerDocument);
		}
		if ("html".equals(tagName)) {
			return new JSoupHtmlElement(element, ownerDocument);
		}
		if ("meta".equals(tagName)) {
			return new JSoupMetaElement(element, ownerDocument);
		}
		if ("nav".equals(tagName)) {
			return new JSoupNavElement(element, ownerDocument);
		}
		if ("noscript".equals(tagName)) {
			return new JSoupElement(element, ownerDocument);
		}
		if ("ol".equals(tagName)) {
			return new JSoupOLElement(element, ownerDocument);
		}
		if ("option".equals(tagName)) {
			return new JSoupOptionElement(element, ownerDocument);
		}
		if ("p".equals(tagName)) {
			return new JSoupParagraphElement(element, ownerDocument);
		}
		if ("script".equals(tagName)) {
			return new JSoupScriptElement(element, ownerDocument);
		}
		if ("select".equals(tagName)) {
			return new JSoupSelectElement(element, ownerDocument);
		}
		if ("span".equals(tagName)) {
			return new JSoupElement(element, ownerDocument);
		}
		if ("style".equals(tagName)) {
			return new JSoupStyleElement(element, ownerDocument);
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
		if ("ul".equals(tagName)) {
			return new JSoupUListElement(element, ownerDocument);
		}
		if (HEADING_PATTERN.matcher(tagName).matches()) {
			return new JSoupHeadingElement(element, ownerDocument);
		}
		throw new IllegalArgumentException(element.tagName());
//		return new JSoupElement(element, ownerDocument);
	}
}
