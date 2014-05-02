package com.github.snoblind.winterface.spi;

import java.util.List;
import org.w3c.dom.traversal.NodeFilter;

public interface QueryParser {
	
	List<NodeFilter> parseQuery(String query);
}
