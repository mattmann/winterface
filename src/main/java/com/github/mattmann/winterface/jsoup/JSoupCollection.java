package com.github.mattmann.winterface.jsoup;

import com.github.mattmann.winterface.HTMLCollection;
import com.github.mattmann.winterface.Node;
import java.util.AbstractList;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import static org.apache.commons.lang.Validate.notNull;

public class JSoupCollection extends AbstractList<Node> implements HTMLCollection {

	private final JSoupNode<?> node;
	private final Elements elements;

	public JSoupCollection(JSoupNode<?> node, Elements elements) {
		notNull(this.node = node);
		notNull(this.elements = elements);
	}

	public int getLength() {
		return elements.size();
	}

	public Node item(int index) {
		return node.wrap(elements.get(index));
	}

	public Node namedItem(CharSequence name) {
		notNull(name);
		for (Element element: elements) {
			if (element.attr("id").equals(name)) {
				return node.wrap(element);
			}
		}
		for (Element element: elements) {
			if (element.attr("name").equals(name)) {
				return node.wrap(element);
			}
		}
		return null;
	}

	public Node get(int index) {
		return item(index);
	}

	public int size() {
		return elements.size();
	}
}
