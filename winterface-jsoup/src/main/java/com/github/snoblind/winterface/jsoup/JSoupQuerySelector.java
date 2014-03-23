package com.github.snoblind.winterface.jsoup;

import com.github.snoblind.winterface.ExtendedHTMLCollection;
import com.github.snoblind.winterface.spi.AbstractQuerySelector;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Node;

public class JSoupQuerySelector extends AbstractQuerySelector {

	private <T extends Element> ExtendedHTMLCollection querySelectorAll(final JSoupNode<T> node, final String query) {
		final Elements elements = node.node.select(query.toString());
		return new ExtendedHTMLCollection() {

			public Node item(int index) {
				return node.adapt(elements.get(index));
			}

			public int getLength() {
				return elements.size();
			}

			public Node namedItem(String name) {
				throw new UnsupportedOperationException();
			}
		};
	}
	
	@SuppressWarnings("unchecked")
	public ExtendedHTMLCollection querySelectorAll(final org.w3c.dom.Document document, final String query) {
		if (document instanceof JSoupDocument) {
			return querySelectorAll((JSoupNode<Document>) document, query);
		}
		throw new IllegalArgumentException(String.valueOf(document));
	}

	@SuppressWarnings("unchecked")
	public ExtendedHTMLCollection querySelectorAll(final org.w3c.dom.Element element, String query) {
		if (element instanceof JSoupElement) {
			return querySelectorAll((JSoupNode<Element>) element, query);
		}
		throw new IllegalArgumentException(String.valueOf(element));
	}
}