package com.github.snoblind.winterface.spi;

import org.w3c.dom.Node;

public interface NodeAdapterFactory<N> {
	Node adapt(N node);
}
