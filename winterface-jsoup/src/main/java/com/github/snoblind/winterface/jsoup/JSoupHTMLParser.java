package com.github.snoblind.winterface.jsoup;

import com.github.snoblind.winterface.Wrapper;
import com.github.snoblind.winterface.event.EventDispatcher;
import com.github.snoblind.winterface.spi.HTMLParser;
import com.github.snoblind.winterface.util.NodeListUtils;
import org.jsoup.parser.Parser;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import static org.apache.commons.lang.Validate.notNull;

public class JSoupHTMLParser implements HTMLParser {

	private final EventDispatcher eventDispatcher;
	
	public JSoupHTMLParser(EventDispatcher eventDispatcher) {
		notNull(this.eventDispatcher = eventDispatcher);
	}

	public Document parse(String html, String baseUri) {
		return new JSoupDocument(Parser.parse(html, baseUri), eventDispatcher);
	}

	public String getInnerText(Node node) {
		if (node instanceof CharacterData) {
			return ((CharacterData)node).getData();
		}
		final StringBuilder builder = new StringBuilder();
		for (Node childNode: NodeListUtils.iterable(node.getChildNodes())) {
			builder.append(getInnerText(childNode));
		}
		return builder.toString();
	}

	public void setInnerText(Node node, String innerText) {
		throw new UnsupportedOperationException();
	}

	public String getInnerHTML(final Element element) {
		if (element instanceof JSoupElement) {
			return ((JSoupElement) element).node.html();
		}
		if (element instanceof Wrapper && ((Wrapper) element).isWrapperFor(Element.class)) {
			return getInnerHTML(((Wrapper) element).unwrap(Element.class));
		}
		throw new IllegalArgumentException(element.getClass().getName());
	}

	public void setInnerHTML(Element element, String html) {
		((JSoupElement) element).node.html(html);
	}

	public String getOuterHTML(Element element) {
		if (element instanceof JSoupElement) {
			return ((JSoupElement) element).node.outerHtml();
		}
		if (element instanceof Wrapper && ((Wrapper) element).isWrapperFor(Element.class)) {
			return getOuterHTML(((Wrapper) element).unwrap(Element.class));
		}
		throw new IllegalArgumentException(element.getClass().getName());
	}

	public Node setOuterHTML(Element element, String outerHTML) {
		throw new UnsupportedOperationException();
	}
}
