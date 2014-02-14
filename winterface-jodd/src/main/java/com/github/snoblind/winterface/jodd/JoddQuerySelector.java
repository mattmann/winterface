package com.github.snoblind.winterface.jodd;

import com.github.snoblind.winterface.spi.AbstractQuerySelector;
import com.github.snoblind.winterface.util.NodeListUtils;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import jodd.lagarto.dom.Node;
import jodd.lagarto.dom.NodeSelector;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class JoddQuerySelector extends AbstractQuerySelector {

	public NodeList querySelectorAll(final Document document, final String query) {
		return querySelectorAll(new NodeSelector(new DocumentAdapter(document)), query);
	}

	public NodeList querySelectorAll(final Element element, final String query) {
		final jodd.lagarto.dom.Document document = new DocumentAdapter(element.getOwnerDocument());
		final jodd.lagarto.dom.Element adapter = new ElementAdapter(document, element);
		return querySelectorAll(new NodeSelector(adapter), query);
	}

	private NodeList querySelectorAll(final NodeSelector nodeSelector, final String query) {
		final List<Node> nodes = nodeSelector.select(query);
		final Set<org.w3c.dom.Node> set = new LinkedHashSet<org.w3c.dom.Node>(nodes.size());
		for (Node node: nodes) {
			set.add(NodeAdapter.unwrap(node));
		}
		return NodeListUtils.fromList(new ArrayList<org.w3c.dom.Node>(set));
	}
}
