package com.github.snoblind.winterface.jsoup;

import com.github.snoblind.winterface.spi.AbstractQuerySelector;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLDocument;
import org.w3c.dom.html.HTMLElement;

public class JSoupQuerySelector extends AbstractQuerySelector {

	private <T extends Element> NodeList querySelectorAll(final JSoupNode<T> node, final String query) {
		final Elements elements = node.node.select(query.toString());
		return new NodeList() {

			public Node item(int index) {
				return node.adapt(elements.get(index));
			}

			public int getLength() {
				return elements.size();
			}
		};
	}
	
	@SuppressWarnings("unchecked")
	public NodeList querySelectorAll(final HTMLDocument document, final String query) {
		if (document instanceof JSoupDocument) {
			return querySelectorAll((JSoupNode<Document>) document, query);
		}
		throw new IllegalArgumentException(String.valueOf(document));
	}

	@SuppressWarnings("unchecked")
	public NodeList querySelectorAll(HTMLElement element, String query) {
		if (element instanceof JSoupElement) {
			return querySelectorAll((JSoupNode<Element>) element, query);
		}
		throw new IllegalArgumentException(String.valueOf(element));
	}
}