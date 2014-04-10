package com.github.snoblind.winterface.jodd;

import com.github.snoblind.winterface.ExtendedHTMLCollection;
import com.github.snoblind.winterface.spi.AbstractQuerySelector;
import java.util.List;
import jodd.lagarto.dom.Node;
import jodd.lagarto.dom.NodeSelector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class JoddQuerySelector extends AbstractQuerySelector {

	private static Logger LOGGER = LoggerFactory.getLogger(JoddQuerySelector.class);
	
	public ExtendedHTMLCollection querySelectorAll(final Document document, final String query) {
		LOGGER.debug("querySelectorAll({}, {})", document, query);
		return querySelectorAll(new NodeSelector(new DocumentAdapter(document)), query);
	}

	public ExtendedHTMLCollection querySelectorAll(final Element element, final String query) {
		LOGGER.debug("querySelectorAll({}, {})", element, query);
		final jodd.lagarto.dom.Document document = new DocumentAdapter(element.getOwnerDocument());
		final jodd.lagarto.dom.Element adapter = new ElementAdapter(document, element);
		return querySelectorAll(new NodeSelector(adapter), query);
	}

	private ExtendedHTMLCollection querySelectorAll(final NodeSelector nodeSelector, final String query) {
		LOGGER.debug("querySelectorAll({}, {})", nodeSelector, query);
		final List<Node> nodes = nodeSelector.select(query);
		LOGGER.debug("Selected {} nodes using query {}.", nodes.size(), query);
		return new ExtendedHTMLCollection() {

			public int getLength() {
				return nodes.size();
			}

			public org.w3c.dom.Node item(int index) {
				try {
					return NodeAdapter.unwrap(nodes.get(index));
				}
				catch (IndexOutOfBoundsException x) {
					return null;
				}
			}

			public org.w3c.dom.Node namedItem(String name) {
				for (Node node: nodes) {
					if (node.getAttribute("id").equals(name) || node.getAttribute("name").equals(name)) {
						return NodeAdapter.unwrap(node);
					}
				}
				return null;
			}
		};
	}
}
