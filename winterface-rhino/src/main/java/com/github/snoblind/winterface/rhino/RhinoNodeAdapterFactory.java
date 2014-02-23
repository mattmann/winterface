package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.spi.NodeAdapterFactory;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

public class RhinoNodeAdapterFactory implements NodeAdapterFactory<Node, RhinoDocument> {

//	private static final Pattern HEADING_PATTERN = Pattern.compile("h[1-6]", Pattern.CASE_INSENSITIVE);

	public RhinoNode<? extends Node> adapt(final Node node, final RhinoDocument ownerDocument) {
		if (node == null) {
			return null;
		}
		if (node instanceof Document) {
			if (ownerDocument.node.equals(node)) {
				return ownerDocument;
			}
			throw new IllegalArgumentException(node.getClass().getName());
		}
		if (node instanceof Element) {
			return adapt((Element) node, ownerDocument);
		}
		if (node instanceof Text) {
			return new RhinoText((Text) node, ownerDocument);
		}
		if (node instanceof Comment) {
			return new RhinoComment((Comment) node, ownerDocument);
		}
//		if (node instanceof DataNode) {
//			return new RhinoCDATASection((DataNode) node, ownerDocument);
//		}
//		if (node instanceof DocumentType) {
//			return new RhinoDocumentType((DocumentType) node, ownerDocument);
//		}
		if (node instanceof DocumentFragment) {
			return new RhinoDocumentFragment((DocumentFragment) node, ownerDocument);
		}
		throw new IllegalArgumentException(node.getClass().getName());
	}

	private RhinoElement adapt(final Element element, final RhinoDocument ownerDocument) {
		final String tagName = element.getTagName();
		if ("html".equals(tagName)) {
			return new RhinoHtmlElement(element, ownerDocument);
		}
//		if ("a".equals(tagName)) {
//			return new RhinoAnchorElement(element, ownerDocument);
//		}
//		if ("applet".equals(tagName)) {
//			return new RhinoAppletElement(element, ownerDocument);
//		}
//		if ("b".equals(tagName)) {
//			return new RhinoElement(element, ownerDocument);
//		}
//		if ("body".equals(tagName)) {
//			return new RhinoBodyElement(element, ownerDocument);
//		}
//		if ("br".equals(tagName)) {
//			return new RhinoBRElement(element, ownerDocument);
//		}
		if ("div".equals(tagName)) {
			return new RhinoDivElement(element, ownerDocument);
		}
//		if ("font".equals(tagName)) {
//			return new RhinoFontElement(element, ownerDocument);
//		}
//		if ("label".equals(tagName)) {
//			return new RhinoLabelElement(element, ownerDocument);
//		}
//		if ("li".equals(tagName)) {
//			return new RhinoLIElement(element, ownerDocument);
//		}
//		if ("link".equals(tagName)) {
//			return new RhinoLinkElement(element, ownerDocument);
//		}
//		if ("img".equals(tagName)) {
//			return new RhinoImageElement(element, ownerDocument);
//		}
//		if ("input".equals(tagName)) {
//			return new RhinoInputElement(element, ownerDocument);
//		}
//		if ("form".equals(tagName)) {
//			return new RhinoFormElement(element, ownerDocument);
//		}
//		if ("head".equals(tagName)) {
//			return new RhinoHeadElement(element, ownerDocument);
//		}
//		if ("html".equals(tagName)) {
//			return new RhinoHtmlElement(element, ownerDocument);
//		}
//		if ("meta".equals(tagName)) {
//			return new RhinoMetaElement(element, ownerDocument);
//		}
//		if ("noscript".equals(tagName)) {
//			return new RhinoElement(element, ownerDocument);
//		}
//		if ("ol".equals(tagName)) {
//			return new RhinoOLElement(element, ownerDocument);
//		}
//		if ("option".equals(tagName)) {
//			return new RhinoOptionElement(element, ownerDocument);
//		}
//		if ("p".equals(tagName)) {
//			return new RhinoParagraphElement(element, ownerDocument);
//		}
//		if ("script".equals(tagName)) {
//			return new RhinoScriptElement(element, ownerDocument);
//		}
//		if ("select".equals(tagName)) {
//			return new RhinoSelectElement(element, ownerDocument);
//		}
//		if ("span".equals(tagName)) {
//			return new RhinoElement(element, ownerDocument);
//		}
//		if ("style".equals(tagName)) {
//			return new RhinoStyleElement(element, ownerDocument);
//		}
//		if ("table".equals(tagName)) {
//			return new RhinoTableElement(element, ownerDocument);
//		}
//		if ("tbody".equals(tagName)) {
//			return new RhinoTableSectionElement(element, ownerDocument);
//		}
//		if ("td".equals(tagName)) {
//			return new RhinoTableCellElement(element, ownerDocument);
//		}
//		if ("title".equals(tagName)) {
//			return new RhinoTitleElement(element, ownerDocument);
//		}
//		if ("tr".equals(tagName)) {
//			return new RhinoTableRowElement(element, ownerDocument);
//		}
//		if ("ul".equals(tagName)) {
//			return new RhinoUListElement(element, ownerDocument);
//		}
//		if (HEADING_PATTERN.matcher(tagName).matches()) {
//			return new RhinoHeadingElement(element, ownerDocument);
//		}
//		throw new IllegalArgumentException(element.tagName());
		return new RhinoElement(element, ownerDocument);
	}
}