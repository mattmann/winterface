package com.github.mattmann.winterface.jsoup;

import org.w3c.dom.CharacterData;
import static org.apache.commons.lang.Validate.notNull;

public abstract class JSoupCharacterData<T extends org.jsoup.nodes.Node> extends JSoupNode<T> implements CharacterData {

	private final JSoupDocument ownerDocument;

	public JSoupCharacterData(T node, JSoupDocument ownerDocument) {
		super(node);
		notNull(this.ownerDocument = ownerDocument);
	}

	public JSoupDocument getOwnerDocument() {
		return ownerDocument;
	}
}
