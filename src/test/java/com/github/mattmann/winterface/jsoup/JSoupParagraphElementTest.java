package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.Test;
import org.mockito.Mock;

import com.github.mattmann.winterface.jsoup.JSoupDocument;
import com.github.mattmann.winterface.jsoup.JSoupParagraphElement;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class JSoupParagraphElementTest {
	
	@Mock JSoupDocument ownerDocument;

	@Test
	public void test() {
		initMocks(this);
		Element element = new Element(Tag.valueOf("p"), "about:blank");
		element.text("The quick brown fox jumped over the lazy dog.");
		JSoupParagraphElement paragraph = new JSoupParagraphElement(element, ownerDocument);
		paragraph.setAlign("center");
		assertEquals("center", paragraph.getAlign());
		assertEquals("<p align=\"center\">The quick brown fox jumped over the lazy dog.</p>", paragraph.getOuterHTML());
	}
}
