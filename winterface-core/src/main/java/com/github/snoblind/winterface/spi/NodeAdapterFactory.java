package com.github.snoblind.winterface.spi;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public interface NodeAdapterFactory<N, D extends Document> {
	Node adapt(N node, D ownerDocument);
}
