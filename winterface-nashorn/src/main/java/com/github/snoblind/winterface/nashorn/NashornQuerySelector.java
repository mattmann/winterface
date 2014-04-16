package com.github.snoblind.winterface.nashorn;

import com.github.snoblind.winterface.ExtendedHTMLCollection;
import com.github.snoblind.winterface.spi.AbstractQuerySelector;
import java.util.List;
import java.util.NoSuchElementException;
import jodd.lagarto.dom.DOMBuilder;
import jodd.lagarto.dom.LagartoDOMBuilder;
import jodd.lagarto.dom.NodeSelector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class NashornQuerySelector extends AbstractQuerySelector {

	private static final Logger LOGGER = LoggerFactory.getLogger(NashornQuerySelector.class);

	private final DOMBuilder parser = new LagartoDOMBuilder();
	
	public ExtendedHTMLCollection querySelectorAll(Document d1, String query) {
		final String html = ((NashornElement) d1.getDocumentElement()).getOuterHTML();
		final jodd.lagarto.dom.Document d2 = parser.parse(html);
		final NodeSelector nodeSelector = new NodeSelector(d2);
		return querySelectorAll(nodeSelector, query, (NashornDocument) d1);
	}

	public ExtendedHTMLCollection querySelectorAll(Element element, String query) {
		throw new UnsupportedOperationException();
	}
	
	private ExtendedHTMLCollection querySelectorAll(final NodeSelector nodeSelector, final String query, final NashornDocument document) {
		LOGGER.debug("querySelectorAll({}, {})", nodeSelector, query);
		final List<jodd.lagarto.dom.Node> nodes = nodeSelector.select(query);
		LOGGER.debug("Selected {} nodes using query {}.", nodes.size(), query);
		return new ExtendedHTMLCollection() {

			public int getLength() {
				return nodes.size();
			}

			public Node item(int index) {
				LOGGER.debug("item({})", index);
				final jodd.lagarto.dom.Node n1 = nodes.get(index);
				throw new NoSuchElementException(n1.getHtml());
			}

			public org.w3c.dom.Node namedItem(String name) {
				LOGGER.debug("namedItem({})", name);
				throw new UnsupportedOperationException();
			}

			public String toString() {
				return nodes.toString();
			}
		};
	}
}
