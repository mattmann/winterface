package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.NamedNodeMap;
import static org.apache.commons.lang.Validate.notNull;

public class NashornDocumentFragment extends NashornNode implements DocumentFragment {

	private final NashornDocument ownerDocument;
	
	public NashornDocumentFragment(NashornDocument ownerDocument) {
		notNull(ownerDocument);
		this.ownerDocument = ownerDocument;
	}

	public Document getOwnerDocument() {
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
}
