package com.github.snoblind.winterface.jsoup;

import org.jsoup.nodes.Node;
import org.jsoup.nodes.Element;
import org.jsoup.select.NodeVisitor;

public abstract class ElementVisitor implements NodeVisitor {

	public final void head(Node node, int depth) {
		if (node instanceof Element) {
			head((Element)node, depth);
		}
	}

	protected void head(Element element, int depth) {}

	public final void tail(Node node, int depth) {
		if (node instanceof Element) {
			tail((Element)node, depth);
		}
	}

	protected void tail(Element element, int depth) {}
}
