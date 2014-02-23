package com.github.snoblind.winterface.jodd;

import java.io.IOException;
import jodd.lagarto.LagartoLexer.Position;
import jodd.lagarto.dom.Attribute;
import jodd.lagarto.dom.Comment;
import jodd.lagarto.dom.Document;
import jodd.lagarto.dom.Element;
import jodd.lagarto.dom.Node;
import static jodd.lagarto.dom.Node.NodeType.COMMENT;
import static org.apache.commons.lang.Validate.notNull;

public class CommentAdapter extends jodd.lagarto.dom.Comment {

	private final org.w3c.dom.Comment comment;
	
	public CommentAdapter(jodd.lagarto.dom.Document ownerDocument, org.w3c.dom.Comment comment) {
		super(ownerDocument, comment == null ? null : comment.getData());
		notNull(comment);
		this.comment = comment;
	}

	public NodeType getNodeType() {
		return COMMENT;
	}

	public int getChildNodesCount() {
		return comment.getChildNodes().getLength();
	}

	public Comment clone() {
		throw new UnsupportedOperationException();
	}

	public boolean isConditionalComment() {
		throw new UnsupportedOperationException();
	}

	public boolean isDownlevelHidden() {
		throw new UnsupportedOperationException();
	}

	public void toHtml(Appendable appendable) throws IOException {
		throw new UnsupportedOperationException();
	}

	protected <T extends Node> T cloneTo(T dest) {
		throw new UnsupportedOperationException();
	}

	public String getNodeName() {
		throw new UnsupportedOperationException();
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
		throw new UnsupportedOperationException();
	}

	public String getAttribute(String name) {
		throw new UnsupportedOperationException();
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

	public int getChildElementsCount() {
		throw new UnsupportedOperationException();
	}

	public int getChildElementsCount(String elementName) {
		throw new UnsupportedOperationException();
	}

	public Node[] getChildNodes() {
		throw new UnsupportedOperationException();
	}

	public Element[] getChildElements() {
		throw new UnsupportedOperationException();
	}

	public Node getChild(int index) {
		throw new UnsupportedOperationException();
	}

	public Node getChild(int... indexes) {
		throw new UnsupportedOperationException();
	}

	public Element getChildElement(int index) {
		throw new UnsupportedOperationException();
	}

	public Node getFirstChild() {
		throw new UnsupportedOperationException();
	}

	public Element getFirstChildElement() {
		throw new UnsupportedOperationException();
	}

	public Element getFirstChildElement(String elementName) {
		throw new UnsupportedOperationException();
	}

	public Node getLastChild() {
		throw new UnsupportedOperationException();
	}

	public Element getLastChildElement() {
		throw new UnsupportedOperationException();
	}

	public Element getLastChildElement(String elementName) {
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
		throw new UnsupportedOperationException();
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

	public String getHtml() {
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
