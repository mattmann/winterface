package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.DOMException;
import org.w3c.dom.Text;

public class NashornText extends NashornCharacterData implements Text {

	public NashornText(final String data, final NashornDocument ownerDocument) {
		super(data, ownerDocument);
	}

	public short getNodeType() {
		return TEXT_NODE;
	}

	public String getNodeName() {
		return "#text";
	}

	public Text splitText(int offset) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public boolean isElementContentWhitespace() {
		throw new UnsupportedOperationException();
	}

	public String getWholeText() {
		throw new UnsupportedOperationException();
	}

	public Text replaceWholeText(String content) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public String toString() {
		return getData();
	}
}
