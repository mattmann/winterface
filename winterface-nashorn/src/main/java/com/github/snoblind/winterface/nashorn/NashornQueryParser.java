package com.github.snoblind.winterface.nashorn;

import com.github.snoblind.winterface.spi.QueryParser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jodd.csselly.CSSelly;
import jodd.csselly.CssSelector;
import org.w3c.dom.traversal.NodeFilter;

public class NashornQueryParser implements QueryParser {

	public List<NodeFilter> parseQuery(String query) {
		final Collection<List<CssSelector>> collection = CSSelly.parse(query);
		final List<NodeFilter> filters = new ArrayList<NodeFilter>(collection.size());
		for (List<CssSelector> selectors: collection) {
			filters.add(new JoddNodeFilter(selectors));
		}
		return filters;
	}
}
