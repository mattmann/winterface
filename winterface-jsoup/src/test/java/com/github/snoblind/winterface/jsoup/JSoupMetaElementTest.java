package com.github.snoblind.winterface.jsoup;

import java.io.IOException;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.github.snoblind.winterface.jsoup.JSoupDocument;
import com.github.snoblind.winterface.jsoup.JSoupMetaElement;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class JSoupMetaElementTest {

	private JSoupMetaElement metaElement;
	
	@Mock private JSoupDocument ownerDocument;

	@Before
	public void setUp() throws IOException {
		initMocks(this);
		metaElement = new JSoupMetaElement(new Element(Tag.valueOf("meta"), "about:blank", new Attributes()), ownerDocument);
	}

	@Test
	public void test() {
		metaElement.setName("James Joyce");
		assertEquals(metaElement.getName(), "James Joyce");
		metaElement.setContent("text/html; charset=utf-8");
		assertEquals(metaElement.getContent(), "text/html; charset=utf-8");
		metaElement.setHttpEquiv("content-type");
		assertEquals(metaElement.getHttpEquiv(), "content-type");
		metaElement.setScheme("ISBN");
		assertEquals(metaElement.getScheme(), "ISBN");
		metaElement.setCharset("UTF-8");
		assertEquals(metaElement.getCharset(), "UTF-8");
	}
}