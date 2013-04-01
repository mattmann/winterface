package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.Node;

public interface NodeFilter {

	boolean accept(Node node);
}
