package com.github.mattmann.winterface.jsoup;

import java.util.List;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLCollection;
import static org.apache.commons.lang.Validate.notNull;

public class HTMLCollectionAdapter implements HTMLCollection, NodeList {
	
	private final List<Node> nodes;

	public HTMLCollectionAdapter(List<Node> list) {
		notNull(this.nodes = list);
	}

	public int getLength() {
		return nodes.size();
	}

	public Node item(int index) {
		return nodes.get(index);
	}

	public Node namedItem(String name) {
		notNull(name);
		for (Node node: nodes) {
			Attr attr = (Attr)node.getAttributes().getNamedItem("id");
			if (attr == null) {
				continue;
			}
			String id = attr.getValue();
			if (name.equals(id)) {
				return node;
			}
		}
		for (Node node: nodes) {
			Attr attr = (Attr)node.getAttributes().getNamedItem("name");
			if (attr == null) {
				continue;
			}
			String id = attr.getValue();
			if (name.equals(id)) {
				return node;
			}
		}
		return null;
	}

}
