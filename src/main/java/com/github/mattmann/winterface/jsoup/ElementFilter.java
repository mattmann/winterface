package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public abstract class ElementFilter implements NodeFilter {

	public final boolean accept(Node node) {
		return node instanceof Element && accept((Element)node);
	}
	
	protected abstract boolean accept(Element element);
}
