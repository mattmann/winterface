package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.TypeInfo;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;
import static org.apache.commons.lang.Validate.notNull;

public class NashornAttribute extends NashornNode implements Attr {

	private final String namespaceURI;
	private final String name;
	private final NashornDocument ownerDocument;
	private Element ownerElement;
	private String value;
	
	public NashornAttribute(final String namespaceURI, final String name, final NashornDocument ownerDocument) {
		notNull(name);
		notNull(ownerDocument);
		this.namespaceURI = namespaceURI;
		this.name = name;
		this.ownerDocument = ownerDocument;
	}

	public NashornAttribute(final String name, final NashornDocument ownerDocument) {
		this(null, name, ownerDocument);
	}

	public NamedNodeMap getAttributes() {
		return null;
	}

	public String getNodeValue() {
		return getValue();
	}

	public void setNodeValue(String value) {
		setValue(value);
	}

	public NashornDocument getOwnerDocument() {
		return ownerDocument;
	}

	public String getNamespaceURI() {
		return namespaceURI;
	}

	public String getLocalName() {
		return getName();
	}

	public String getNodeName() {
		return getName();
	}

	public short getNodeType() {
		return ATTRIBUTE_NODE;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Element getOwnerElement() {
		return ownerElement;
	}

	public void setOwnerElement(Element ownerElement) {
		this.ownerElement = ownerElement;
	}

	public boolean getSpecified() {
		throw new UnsupportedOperationException();
	}

	public TypeInfo getSchemaTypeInfo() {
		throw new UnsupportedOperationException();
	}

	public boolean isId() {
		throw new UnsupportedOperationException();
	}

	public void addEventListener(String type, EventListener listener, boolean useCapture) {
		ownerDocument.getEventDispatcher().addEventListener(this, type, listener, useCapture);
	}

	public void removeEventListener(String type, EventListener listener, boolean useCapture) {
		ownerDocument.getEventDispatcher().removeEventListener(this, type, listener, useCapture);
	}

	public boolean dispatchEvent(Event event) throws EventException {
		return ownerDocument.getEventDispatcher().dispatchEvent(event);
	}
}
