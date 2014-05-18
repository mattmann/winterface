package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.DocumentFragment;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;
import static org.apache.commons.lang.Validate.notNull;

public class NashornDocumentFragment extends NashornNode implements DocumentFragment {

	private final NashornDocument ownerDocument;
	
	public NashornDocumentFragment(NashornDocument ownerDocument) {
		notNull(ownerDocument);
		this.ownerDocument = ownerDocument;
	}

	public NashornDocument getOwnerDocument() {
		return ownerDocument;
	}

	public String getNodeValue() {
		return null;
	}

	public void setNodeValue(String nodeValue) {
	}

	public String getNamespaceURI() {
		return null;
	}

	public String getLocalName() {
		return null;
	}

	public short getNodeType() {
		return DOCUMENT_FRAGMENT_NODE;
	}

	public String getNodeName() {
		return "#document-fragment";
	}

	public NamedNodeMap getAttributes() {
		return null;
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
