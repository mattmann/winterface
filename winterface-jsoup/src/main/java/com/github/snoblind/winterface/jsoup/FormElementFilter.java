package com.github.snoblind.winterface.jsoup;

import java.util.Arrays;
import java.util.Collection;
import org.jsoup.nodes.Element;

public class FormElementFilter extends ElementFilter {
	
	private static final Collection<String> TAG_NAMES = Arrays.asList(
		"button",
		"input",
		"textarea",
		"select"
	);

	protected boolean accept(Element element) {
		return TAG_NAMES.contains(element.tagName());
	}
}
