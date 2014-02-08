package com.github.snoblind.winterface.abstracts;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

public class AbstractNode implements Node {

	public String getNodeName() {
		throw new UnsupportedOperationException();
	}

	public String getNodeValue() throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void setNodeValue(String nodeValue) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public short getNodeType() {
		throw new UnsupportedOperationException();
	}

	public Node getParentNode() {
		throw new UnsupportedOperationException();
	}

	public NodeList getChildNodes() {
		throw new UnsupportedOperationException();
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

	public Document getOwnerDocument() {
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

	public boolean isSupported(String feature, String version) {
		throw new UnsupportedOperationException();
	}

	public String getNamespaceURI() {
		throw new UnsupportedOperationException();
	}

	public String getPrefix() {
		throw new UnsupportedOperationException();
	}

	public void setPrefix(String prefix) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public String getLocalName() {
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
		throw new UnsupportedOperationException();
	}

	public void setTextContent(String textContent) throws DOMException {
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
}
