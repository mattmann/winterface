package com.github.snoblind.winterface.jsoup;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import static org.apache.commons.lang.Validate.notNull;

public class JSoupAttributes implements NamedNodeMap {

	private final Element ownerElement;
    private final Attributes attributes;

    public JSoupAttributes(Element ownerElement, Attributes attributes) {
    	notNull(this.ownerElement = ownerElement);
		notNull(this.attributes = attributes);
	}

	public Node getNamedItem(final String name) {
		final String value = attributes.get(name);
		if (value == null) {
			return null;
		}
		return new JSoupAttr(ownerElement, name, value);
	}

	public Node setNamedItem(Node arg) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Node removeNamedItem(String name) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Node item(int index) {
		Attribute attribute = attributes.asList().get(index);
		if (attribute == null) {
			return null;
		}
		return new JSoupAttr(ownerElement, attribute);
	}

	public int getLength() {
		return attributes.size();
	}

	public Node getNamedItemNS(String namespaceURI, String localName) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Node setNamedItemNS(Node arg) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Node removeNamedItemNS(String namespaceURI, String localName) throws DOMException {
		throw new UnsupportedOperationException();
	}
}
