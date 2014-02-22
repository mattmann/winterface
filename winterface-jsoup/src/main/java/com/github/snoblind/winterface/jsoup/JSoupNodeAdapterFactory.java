package com.github.snoblind.winterface.jsoup;

import com.github.snoblind.winterface.spi.NodeAdapterFactory;
import java.util.regex.Pattern;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import static org.apache.commons.lang.Validate.notNull;

public class JSoupNodeAdapterFactory implements NodeAdapterFactory<Node, JSoupDocument> {

	private static final Pattern HEADING_PATTERN = Pattern.compile("h[1-6]", Pattern.CASE_INSENSITIVE);

	public JSoupNode<? extends Node> adapt(final Node node, final JSoupDocument ownerDocument) {
		notNull(node);
		if (node instanceof Document) {
			if (ownerDocument.node.equals(node)) {
				return ownerDocument;
			}
			throw new IllegalArgumentException(node.getClass().getName());
		}
		if (node instanceof Element) {
			return adapt((Element) node, ownerDocument);
		}
		if (node instanceof TextNode) {
			return new JSoupText((TextNode) node, ownerDocument);
		}
		if (node instanceof Comment) {
			return new JSoupComment((Comment) node, ownerDocument);
		}
		if (node instanceof DataNode) {
			return new JSoupCDATASection((DataNode) node, ownerDocument);
		}
		if (node instanceof DocumentType) {
			return new JSoupDocumentType((DocumentType) node, ownerDocument);
		}
		throw new IllegalArgumentException(node.getClass().getName());
	}

	protected JSoupElement adapt(final Element element, final JSoupDocument ownerDocument) {
		final String tagName = element.tagName();
		if ("a".equals(tagName)) {
			return new JSoupAnchorElement(element, ownerDocument);
		}
		if ("applet".equals(tagName)) {
			return new JSoupAppletElement(element, ownerDocument);
		}
		if ("b".equals(tagName)) {
			return new JSoupElement(element, ownerDocument);
		}
		if ("body".equals(tagName)) {
			return new JSoupBodyElement(element, ownerDocument);
		}
		if ("br".equals(tagName)) {
			return new JSoupBRElement(element, ownerDocument);
		}
		if ("div".equals(tagName)) {
			return new JSoupDivElement(element, ownerDocument);
		}
		if ("font".equals(tagName)) {
			return new JSoupFontElement(element, ownerDocument);
		}
		if ("label".equals(tagName)) {
			return new JSoupLabelElement(element, ownerDocument);
		}
		if ("li".equals(tagName)) {
			return new JSoupLIElement(element, ownerDocument);
		}
		if ("link".equals(tagName)) {
			return new JSoupLinkElement(element, ownerDocument);
		}
		if ("img".equals(tagName)) {
			return new JSoupImageElement(element, ownerDocument);
		}
		if ("input".equals(tagName)) {
			return new JSoupInputElement(element, ownerDocument);
		}
		if ("form".equals(tagName)) {
			return new JSoupFormElement(element, ownerDocument);
		}
		if ("head".equals(tagName)) {
			return new JSoupHeadElement(element, ownerDocument);
		}
		if ("html".equals(tagName)) {
			return new JSoupHtmlElement(element, ownerDocument);
		}
		if ("meta".equals(tagName)) {
			return new JSoupMetaElement(element, ownerDocument);
		}
		if ("noscript".equals(tagName)) {
			return new JSoupElement(element, ownerDocument);
		}
		if ("ol".equals(tagName)) {
			return new JSoupOLElement(element, ownerDocument);
		}
		if ("option".equals(tagName)) {
			return new JSoupOptionElement(element, ownerDocument);
		}
		if ("p".equals(tagName)) {
			return new JSoupParagraphElement(element, ownerDocument);
		}
		if ("script".equals(tagName)) {
			return new JSoupScriptElement(element, ownerDocument);
		}
		if ("select".equals(tagName)) {
			return new JSoupSelectElement(element, ownerDocument);
		}
		if ("span".equals(tagName)) {
			return new JSoupElement(element, ownerDocument);
		}
		if ("style".equals(tagName)) {
			return new JSoupStyleElement(element, ownerDocument);
		}
		if ("table".equals(tagName)) {
			return new JSoupTableElement(element, ownerDocument);
		}
		if ("tbody".equals(tagName)) {
			return new JSoupTableSectionElement(element, ownerDocument);
		}
		if ("td".equals(tagName)) {
			return new JSoupTableCellElement(element, ownerDocument);
		}
		if ("title".equals(tagName)) {
			return new JSoupTitleElement(element, ownerDocument);
		}
		if ("tr".equals(tagName)) {
			return new JSoupTableRowElement(element, ownerDocument);
		}
		if ("ul".equals(tagName)) {
			return new JSoupUListElement(element, ownerDocument);
		}
		if (HEADING_PATTERN.matcher(tagName).matches()) {
			return new JSoupHeadingElement(element, ownerDocument);
		}
//		throw new IllegalArgumentException(element.tagName());
		return new JSoupElement(element, ownerDocument);
	}
}
