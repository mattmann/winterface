package com.github.snoblind.winterface.jsoup;

import org.jsoup.nodes.Node;

public interface NodeFilter {

	boolean accept(Node node);
}
