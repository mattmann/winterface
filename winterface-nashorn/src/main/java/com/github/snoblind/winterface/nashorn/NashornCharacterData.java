package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.CharacterData;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import static org.apache.commons.lang.Validate.notNull;

public abstract class NashornCharacterData extends NashornNode implements CharacterData {

	private final NashornDocument ownerDocument;
	private String data;

	public NashornCharacterData(final String data, final NashornDocument ownerDocument) {
		notNull(ownerDocument);
		this.ownerDocument = ownerDocument;
		setData(data);
	}

	public Document getOwnerDocument() {
		return ownerDocument;
	}

	public String getNamespaceURI() {
		return null;
	}

	public String getLocalName() {
		return null;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		notNull(data);
		this.data = data;
	}

	public String getNodeValue() {
		return getData();
	}

	public void setNodeValue(final String data) {
		setData(data);
	}

	public int getLength() {
		throw new UnsupportedOperationException();
	}

	public NamedNodeMap getAttributes() {
		return null;
	}
	
	public String substringData(int offset, int count) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void appendData(String arg) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void insertData(int offset, String arg) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void deleteData(int offset, int count) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void replaceData(int offset, int count, String arg) throws DOMException {
		throw new UnsupportedOperationException();
	}

}
