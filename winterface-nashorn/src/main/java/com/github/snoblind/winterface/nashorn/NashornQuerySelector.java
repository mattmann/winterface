package com.github.snoblind.winterface.nashorn;

import com.github.snoblind.winterface.ExtendedHTMLCollection;
import com.github.snoblind.winterface.spi.AbstractQuerySelector;
import com.github.snoblind.winterface.spi.QueryParser;
import com.github.snoblind.winterface.util.HTMLCollectionAdapter;
import com.github.snoblind.winterface.util.NodeUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;

public class NashornQuerySelector extends AbstractQuerySelector {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NashornQuerySelector.class);

	private final DocumentTraversal traversal = new NashornDocumentTraversal();
	private final QueryParser queryParser = new NashornQueryParser();

	private ExtendedHTMLCollection querySelectorAll(final Node rootNode, final List<NodeFilter> filters) {
		LOGGER.debug("querySelectorAll({}, {})", rootNode, filters);
		final Collection<Node> results = new LinkedHashSet<Node>();
		for (NodeFilter filter: filters) {
			final NodeIterator iterator = traversal.createNodeIterator(rootNode, NodeFilter.SHOW_ELEMENT, filter, false);
			for (Node node = iterator.nextNode(); node != null; node = iterator.nextNode()) {
				final boolean added = results.add(node);
				if (LOGGER.isDebugEnabled()) {
					if (added) {
						LOGGER.debug("Added node {} to results collection.", NodeUtils.describePath(node));
					}
					else {
						LOGGER.debug("Results collection already contains node {}.", NodeUtils.describePath(node));
					}
				}
			}
		}
		return new HTMLCollectionAdapter(new ArrayList<Node>(results));
	}

	public ExtendedHTMLCollection querySelectorAll(final Element element, final String query) {
		LOGGER.debug("querySelectorAll({}, {})", element, query);
		return querySelectorAll(element, queryParser.parseQuery(query));
	}

	public ExtendedHTMLCollection querySelectorAll(final Document document, final String query) {
		LOGGER.debug("querySelectorAll({}, {})", document, query);
		return querySelectorAll(document, queryParser.parseQuery(query));
	}
}
