package com.github.snoblind.winterface.rhino;

import org.springframework.beans.factory.annotation.Required;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

public class RhinoNode<N extends Node> implements Node {

	protected RhinoDocument ownerDocument;
	protected N node;

	@Required
	public N getNode() {
		return node;
	}
	
	public Document getOwnerDocument() {
		return ownerDocument;
	}

	public String getInnerText() {
		return ownerDocument.getParser().getInnerText(this);
	}

	public void setInnerText(String text) {
		ownerDocument.getParser().setInnerText(this, text);
	}

	public String getTextContent() throws DOMException {
		return getInnerText();
	}

	public void setTextContent(String textContent) throws DOMException {
		setInnerText(textContent);
	}

	protected Node adapt(Node node) {
		return ownerDocument.getNodeAdapterFactory().adapt(node);
	}

	public NodeList getChildNodes() {
		final NodeList childNodes = node.getChildNodes();
		return new NodeList() {

			public Node item(int index) {
				return adapt(childNodes.item(index));
			}

			public int getLength() {
				return childNodes.getLength();
			}
		};
	}

	public String getNodeName() {
		return node.getNodeName();
	}

	public String getNodeValue() throws DOMException {
		return node.getNodeValue();
	}

	public void setNodeValue(String nodeValue) throws DOMException {
		node.setNodeValue(nodeValue);
	}

	public short getNodeType() {
		return node.getNodeType();
	}

	public Node getParentNode() {
		return adapt(node.getParentNode());
	}

	public Node getFirstChild() {
		return adapt(node.getFirstChild());
	}

	public Node getLastChild() {
		return adapt(node.getLastChild());
	}

	public Node getPreviousSibling() {
		return adapt(node.getPreviousSibling());
	}

	public Node getNextSibling() {
		return adapt(node.getNextSibling());
	}

	public boolean hasChildNodes() {
		return node.hasChildNodes();
	}

	public Object setUserData(String key, Object data, UserDataHandler handler) {
		return node.setUserData(key, data, handler);
	}

	public Object getUserData(String key) {
		return node.getUserData(key);
	}

	public Object getFeature(String feature, String version) {
		return node.getFeature(feature, version);
	}

	public boolean isSupported(String feature, String version) {
		return node.isSupported(feature, version);
	}

	public String getBaseURI() {
		return node.getBaseURI();
	}

	public boolean isDefaultNamespace(String namespaceURI) {
		return node.isDefaultNamespace(namespaceURI);
	}

	public String lookupPrefix(String namespaceURI) {
		return node.lookupPrefix(namespaceURI);
	}

	public String lookupNamespaceURI(String prefix) {
		return node.lookupNamespaceURI(prefix);
	}

	public String getPrefix() {
		return node.getPrefix();
	}

	public void setPrefix(String prefix) throws DOMException {
		node.setPrefix(prefix);
	}

	public String getLocalName() {
		return node.getLocalName();
	}

	public void normalize() {
		node.normalize();
	}

	public boolean hasAttributes() {
		return node.hasAttributes();
	}

	public String getNamespaceURI() {
		return node.getNamespaceURI();
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

	public Node cloneNode(boolean deep) {
		throw new UnsupportedOperationException();
	}

	public short compareDocumentPosition(Node other) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public boolean isSameNode(Node other) {
		throw new UnsupportedOperationException();
	}

	public boolean isEqualNode(Node arg) {
		throw new UnsupportedOperationException();
	}
}
