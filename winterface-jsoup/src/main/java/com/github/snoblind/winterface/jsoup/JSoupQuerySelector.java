package com.github.snoblind.winterface.jsoup;

import com.github.snoblind.winterface.ExtendedHTMLCollection;
import com.github.snoblind.winterface.spi.AbstractQuerySelector;
import java.util.AbstractList;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Node;
import static org.apache.commons.lang.Validate.notNull;

public class JSoupQuerySelector extends AbstractQuerySelector {

	private <T extends Element> ExtendedHTMLCollection querySelectorAll(final JSoupNode<T> node, final String query) {
		return new ExtendedHTMLCollectionImpl(node.node.select(query.toString()), node.ownerDocument);
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

	private static class ExtendedHTMLCollectionImpl extends AbstractList<org.w3c.dom.Node> implements ExtendedHTMLCollection {

		private final Elements elements;
		private final JSoupDocument ownerDocument;
		
		public ExtendedHTMLCollectionImpl(final Elements elements, final JSoupDocument ownerDocument) {
			notNull(elements);
			notNull(ownerDocument);
			this.elements = elements;
			this.ownerDocument = ownerDocument;
		}

		public Node get(int index) {
			return ownerDocument.getNodeAdapterFactory().adapt(elements.get(index), ownerDocument);
		}

		public Node item(int index) {
			try {
				return get(index);
			}
			catch (IndexOutOfBoundsException x) {
				return null;
			}
		}

		public int getLength() {
			return elements.size();
		}

		public int size() {
			return elements.size();
		}

		public Node namedItem(String name) {
			throw new UnsupportedOperationException();
		}
	}
}