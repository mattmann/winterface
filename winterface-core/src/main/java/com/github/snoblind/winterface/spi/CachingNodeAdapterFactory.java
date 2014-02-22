package com.github.snoblind.winterface.spi;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import static org.apache.commons.lang.Validate.notNull;

public class CachingNodeAdapterFactory<N, D extends Document> implements NodeAdapterFactory<N, D> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CachingNodeAdapterFactory.class);

	private final Map<N, Node> cache = new HashMap<N, Node>();
	private final NodeAdapterFactory<N, D> nodeAdapterFactory;
	
	public CachingNodeAdapterFactory(NodeAdapterFactory<N, D> nodeAdapterFactory) {
		notNull(this.nodeAdapterFactory = nodeAdapterFactory);
	}

	public Node adapt(final N node1, final D ownerDocument) {
		if (node1 == null) {
			return null;
		}
		Node node2 = cache.get(node1);
		if (node2 == null) {
			cache.put(node1, node2 = nodeAdapterFactory.adapt(node1, ownerDocument));
		}
		else {
			LOGGER.debug("Found cached value for {}.", node1);
		}
		return node2;
	}
}