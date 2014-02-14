package com.github.snoblind.winterface.jodd;

import java.io.IOException;
import jodd.lagarto.dom.Attribute;
import jodd.lagarto.dom.Document;
import jodd.lagarto.dom.Node;
import jodd.lagarto.LagartoLexer.Position;
import org.apache.commons.lang.Validate;
import org.w3c.dom.DOMException;
import org.w3c.dom.NodeList;

public class ElementAdapter extends jodd.lagarto.dom.Element {

	protected final org.w3c.dom.Element element;

	public ElementAdapter(Document ownerDocument, org.w3c.dom.Element element) {
		super(ownerDocument, element == null ? null : element.getTagName());
		Validate.notNull(element);
		this.element = element;
	}

	public jodd.lagarto.dom.Element clone() {
		throw new UnsupportedOperationException();
	}

	public boolean isVoidElement() {
		throw new UnsupportedOperationException();
	}

	public boolean isSelfClosed() {
		throw new UnsupportedOperationException();
	}

	public String toString() {
		throw new UnsupportedOperationException();
	}

	protected <T extends Node> T cloneTo(T dest) {
		throw new UnsupportedOperationException();
	}

	public NodeType getNodeType() {
		return NodeType.ELEMENT;
	}

	public String getNodeName() {
		return element.getNodeName();
	}

	public String getNodeRawName() {
		throw new UnsupportedOperationException();
	}

	public String getNodeValue() {
		throw new UnsupportedOperationException();
	}

	public void setNodeValue(String value) {
		throw new UnsupportedOperationException();
	}

	public Document getOwnerDocument() {
		throw new UnsupportedOperationException();
	}

	public void detachFromParent() {
		throw new UnsupportedOperationException();
	}

	public void addChild(Node node) {
		throw new UnsupportedOperationException();
	}

	public void addChild(Node... nodes) {
		throw new UnsupportedOperationException();
	}

	public void insertChild(Node node, int index) {
		throw new UnsupportedOperationException();
	}

	public void insertChild(Node[] nodes, int index) {
		throw new UnsupportedOperationException();
	}

	public void insertBefore(Node newChild, Node refChild) {
		throw new UnsupportedOperationException();
	}

	public void insertAfter(Node newChild, Node refChild) {
		throw new UnsupportedOperationException();
	}

	public void insertAfter(Node[] newChilds, Node refChild) {
		throw new UnsupportedOperationException();
	}

	public Node removeChild(int index) {
		throw new UnsupportedOperationException();
	}

	public void removeChild(Node childNode) {
		throw new UnsupportedOperationException();
	}

	public void removeAllChilds() {
		throw new UnsupportedOperationException();
	}

	public Node getParentNode() {
		throw new UnsupportedOperationException();
	}

	public boolean hasAttributes() {
		throw new UnsupportedOperationException();
	}

	public int getAttributesCount() {
		throw new UnsupportedOperationException();
	}

	public Attribute getAttribute(int index) {
		throw new UnsupportedOperationException();
	}

	public boolean hasAttribute(String name) {
		return element.hasAttribute(name);
	}

	public String getAttribute(String name) {
		return element.getAttribute(name);
	}

	protected Attribute getAttributeInstance(String name) {
		throw new UnsupportedOperationException();
	}

	protected int indexOfAttributeInstance(String name) {
		throw new UnsupportedOperationException();
	}

	public boolean removeAttribute(String name) {
		throw new UnsupportedOperationException();
	}

	public void setAttribute(String name, String value) {
		throw new UnsupportedOperationException();
	}

	public void setAttribute(String name) {
		throw new UnsupportedOperationException();
	}

	public boolean isAttributeIncluding(String name, String word) {
		throw new UnsupportedOperationException();
	}

	public boolean hasChildNodes() {
		throw new UnsupportedOperationException();
	}

	public int getChildNodesCount() {
		return element.getChildNodes().getLength();
	}

	public int getChildElementsCount() {
		throw new UnsupportedOperationException();
	}

	public int getChildElementsCount(String elementName) {
		throw new UnsupportedOperationException();
	}

	public Node[] getChildNodes() {
		throw new UnsupportedOperationException();
	}

	public jodd.lagarto.dom.Element[] getChildElements() {
		throw new UnsupportedOperationException();
	}

	public Node getChild(int index) {
		final org.w3c.dom.Node node = element.getChildNodes().item(index);
		if (node == null) {
			return null;
		}
		if (node instanceof org.w3c.dom.Text) {
			return new TextAdapter(ownerDocument, (org.w3c.dom.Text) node);
		}
		if (node instanceof org.w3c.dom.Element) {
			return new ElementAdapter(ownerDocument, (org.w3c.dom.Element) node);
		}
		throw new IllegalArgumentException(node.getClass().getName());
	}

	public Node getChild(int... indexes) {
		throw new UnsupportedOperationException();
	}

	public jodd.lagarto.dom.Element getChildElement(int index) {
		throw new UnsupportedOperationException();
	}

	public Node getFirstChild() {
		throw new UnsupportedOperationException();
	}

	public jodd.lagarto.dom.Element getFirstChildElement() {
		throw new UnsupportedOperationException();
	}

	public jodd.lagarto.dom.Element getFirstChildElement(String elementName) {
		throw new UnsupportedOperationException();
	}

	public Node getLastChild() {
		throw new UnsupportedOperationException();
	}

	public jodd.lagarto.dom.Element getLastChildElement() {
		throw new UnsupportedOperationException();
	}

	public jodd.lagarto.dom.Element getLastChildElement(String elementName) {
		throw new UnsupportedOperationException();
	}

	public boolean check() {
		throw new UnsupportedOperationException();
	}

	protected void reindexChildren() {
		throw new UnsupportedOperationException();
	}

	protected void reindexChildrenOnAdd(int addedCount) {
		throw new UnsupportedOperationException();
	}

	protected void initChildElementNodes() {
		throw new UnsupportedOperationException();
	}

	protected void initSiblingNames() {
		throw new UnsupportedOperationException();
	}

	protected void initAttributes() {
		throw new UnsupportedOperationException();
	}

	protected void initChildNodes(Node newNode) {
		throw new UnsupportedOperationException();
	}

	protected void changeOwnerDocument(Node node, Document ownerDocument) {
		throw new UnsupportedOperationException();
	}

	public int getSiblingIndex() {
		throw new UnsupportedOperationException();
	}

	public int getSiblingElementIndex() {
		final NodeList nodes = element.getParentNode().getChildNodes();
		int elementIndex = -1;
		for (int i = 0; i < nodes.getLength(); i++) {
			org.w3c.dom.Node node = nodes.item(i);
			if (node instanceof org.w3c.dom.Element) {
				elementIndex++;
				if (node.equals(element)) {
					return elementIndex;
				}
			}
		}
		throw new DOMException(DOMException.INVALID_STATE_ERR, "Element not found in its parent's list of children!");
	}

	public int getSiblingNameIndex() {
		throw new UnsupportedOperationException();
	}

	public Node getNextSibling() {
		throw new UnsupportedOperationException();
	}

	public Node getNextSiblingElement() {
		throw new UnsupportedOperationException();
	}

	public Node getNextSiblingName() {
		throw new UnsupportedOperationException();
	}

	public Node getPreviousSibling() {
		throw new UnsupportedOperationException();
	}

	public Node getPreviousSiblingElement() {
		throw new UnsupportedOperationException();
	}

	public Node getPreviousSiblingName() {
		throw new UnsupportedOperationException();
	}

	public String getTextContent() {
		throw new UnsupportedOperationException();
	}

	public void appendTextContent(Appendable appendable) {
		throw new UnsupportedOperationException();
	}

	public String getInnerHtml() {
		throw new UnsupportedOperationException();
	}

	protected void toInnerHtml(Appendable appendable) throws IOException {
		throw new UnsupportedOperationException();
	}

	public int getOffset() {
		throw new UnsupportedOperationException();
	}

	public int getDeepLevel() {
		throw new UnsupportedOperationException();
	}

	public Position getPosition() {
		throw new UnsupportedOperationException();
	}

	public String getPositionString() {
		throw new UnsupportedOperationException();
	}

	public String getCssPath() {
		throw new UnsupportedOperationException();
	}
}
