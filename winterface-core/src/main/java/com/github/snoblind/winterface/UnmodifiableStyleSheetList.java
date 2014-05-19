package com.github.snoblind.winterface;

import java.util.AbstractList;
import java.util.List;
import org.w3c.dom.stylesheets.StyleSheet;
import org.w3c.dom.stylesheets.StyleSheetList;
import static org.apache.commons.lang.Validate.notNull;

public class UnmodifiableStyleSheetList extends AbstractList<StyleSheet> implements StyleSheetList {

	private final List<StyleSheet> list;
	
	public UnmodifiableStyleSheetList(final List<StyleSheet> list) {
		notNull(list);
		this.list = list;
	}

	public StyleSheet get(int index) {
		return list.get(index);
	}

	public int size() {
		return list.size();
	}

	public int getLength() {
		return size();
	}

	public StyleSheet item(int index) {
		try {
			return get(index);
		}
		catch (IndexOutOfBoundsException x) {
			return null;
		}
	}
}
