package com.github.snoblind.winterface.nashorn;

import java.util.List;
import jodd.csselly.CssSelector;
import jodd.csselly.Selector;
import jodd.csselly.selector.AttributeSelector;
import jodd.csselly.selector.PseudoClass;
import jodd.csselly.selector.PseudoClassSelector;
import jodd.csselly.selector.PseudoFunction;
import jodd.csselly.selector.PseudoFunctionSelector;
import jodd.util.StringPool;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeFilter;

public class JoddNodeFilter implements NodeFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(JoddNodeFilter.class);

	private final List<CssSelector> selectors;
	
	public JoddNodeFilter(final List<CssSelector> selectors) {
		this.selectors = selectors;
	}

	public short acceptNode(final Node node) {
		if (Node.ELEMENT_NODE != node.getNodeType()) {
			return FILTER_SKIP;
		}
		if (accept(node, selectors)) {
			return FILTER_ACCEPT;
		}
		return FILTER_SKIP;
	}

	private boolean accept(Node node, final List<CssSelector> selectors) {
		final CssSelector s1 = selectors.get(selectors.size() - 1);
		if (accept(node, s1)) {
			if (1 == selectors.size()) {
				return true;
			}
			final CssSelector s2 = selectors.get(selectors.size() - 2);
			switch (s2.getCombinator()) {
			case CHILD:
				return accept(node.getParentNode(), selectors.subList(0, selectors.size() - 1));
			case DESCENDANT:
				node = node.getParentNode();
				while (node != null) {
					if (accept(node, s2)) {
						return accept(node, selectors.subList(0, selectors.size() - 1));
					}
					node = node.getParentNode();
				}
				return false;
			default:
				throw new UnsupportedOperationException(String.format("%s: %s", s2, s2.getCombinator()));
			}
		}
		return false;
	}

	private boolean accept(final Node node, final CssSelector cssSelector) {
		// match element name with node name
		if (!matchElement(cssSelector, node)) {
			return false;
		}

		// match attributes
		int totalSelectors = cssSelector.selectorsCount();
		for (int i = 0; i < totalSelectors; i++) {
			Selector selector = cssSelector.getSelector(i);
			switch (selector.getType()) {
			case ATTRIBUTE:
				if (!accept((AttributeSelector) selector, (Element) node)) {
					return false;
				}
				break;
			case PSEUDO_CLASS:
				if (!accept((PseudoClassSelector) selector, (Element) node)) {
					return false;
				}
				break;
			case PSEUDO_FUNCTION:
				if (!accept((PseudoFunctionSelector<?>) selector, node)) {
					return false;
				}
				break;
			default:
				throw new IllegalArgumentException(String.valueOf(selector.getType()));
			}
		}
		return true;
	}

	private boolean accept(final PseudoClassSelector selector, final Element element) {
		if (selector.getPseudoClass() instanceof PseudoClass.FIRST_CHILD) {
			return getSiblingElementIndex(element) == 0;
		}
		throw new IllegalArgumentException(selector.getPseudoClass().getClass().getName());
	}

	private boolean accept(final AttributeSelector selector, final Element node) {
		final String name = selector.getName();
		final String value = selector.getValue();
		final boolean result = node.hasAttribute(name) && (value == null || selector.getMatch().compare(node.getAttribute(name), value));
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("accept({}, {}) => {}", ToStringBuilder.reflectionToString(selector, ToStringStyle.MULTI_LINE_STYLE), node, result);
		}
		return result;
	}

	private boolean accept(PseudoFunctionSelector<?> selector, Node node) {
		if (selector.getPseudoFunction() instanceof PseudoFunction.CONTAINS) {
			return node.getTextContent().contains((String) selector.getParsedExpression());
		}
		throw new UnsupportedOperationException(selector.getPseudoFunction().toString());
	}

	private boolean matchElement(final CssSelector cssSelector, final Node node) {
		if (node.getNodeType() != Node.ELEMENT_NODE) {
			return false;
		}
		String element = cssSelector.getElement();
		String nodeName = node.getNodeName();
		return element.equals(StringPool.STAR) || element.equals(nodeName);
	}

	private int getSiblingElementIndex(final Element element) {
		final NodeList nodes = element.getParentNode().getChildNodes();
		int elementIndex = -1;
		for (int i = 0; i < nodes.getLength(); i++) {
			final Node node = nodes.item(i);
			if (Node.ELEMENT_NODE == node.getNodeType()) {
				elementIndex++;
				if (node.equals(element)) {
					return elementIndex;
				}
			}
		}
		throw new DOMException(DOMException.INVALID_STATE_ERR, "Element not found in its parent's list of children!");
	}
}
