package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.CDATASection;

public class NashornCDATASection extends NashornText implements CDATASection {

	public NashornCDATASection(final String data, final NashornDocument ownerDocument) {
		super(data, ownerDocument);
	}

	public short getNodeType() {
		return CDATA_SECTION_NODE;
	}
}
