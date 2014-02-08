package com.github.snoblind.winterface.jsoup;

import org.w3c.dom.CharacterData;

public abstract class JSoupCharacterData<T extends org.jsoup.nodes.Node> extends JSoupNode<T> implements CharacterData {

	public JSoupCharacterData(T node, JSoupDocument ownerDocument) {
		super(node, ownerDocument);
	}
}
