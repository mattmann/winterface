package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.Comment;

public class NashornComment extends NashornCharacterData implements Comment {

	public NashornComment(final String data, final NashornDocument ownerDocument) {
		super(data, ownerDocument);
	}

	public short getNodeType() {
		return COMMENT_NODE;
	}

	public String getNodeName() {
		return "#comment";
	}

	public String toString() {
		return String.format("<!--%s-->", getData());
	}
}
