package com.github.snoblind.winterface.jsoup;

import org.jsoup.nodes.DataNode;
import org.w3c.dom.CDATASection;
import org.w3c.dom.DOMException;
import org.w3c.dom.Text;

public class JSoupCDATASection extends JSoupCharacterData<DataNode> implements CDATASection {

	public JSoupCDATASection(DataNode node, JSoupDocument ownerDocument) {
		super(node, ownerDocument);
	}

	public short getNodeType() {
		return CDATA_SECTION_NODE;
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

	public String getData() throws DOMException {
		return node.getWholeData();
	}

	public void setData(String data) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public int getLength() {
		throw new UnsupportedOperationException();
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

	public void replaceData(int offset, int count, String arg)
			throws DOMException {
		throw new UnsupportedOperationException();
	}
}