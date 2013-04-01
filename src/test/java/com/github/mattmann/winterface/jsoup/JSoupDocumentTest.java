package com.github.mattmann.winterface.jsoup;

import java.io.IOException;
import org.jsoup.parser.Parser;

import com.github.mattmann.winterface.HTMLDocument;
import com.github.mattmann.winterface.HTMLDocumentTest;
import com.github.mattmann.winterface.jsoup.JSoupDocument;
import com.github.mattmann.winterface.jsoup.JSoupEventDispatcher;

public class JSoupDocumentTest extends HTMLDocumentTest {

	protected HTMLDocument parseDocument(String html, String baseURI) throws IOException {
		return new JSoupDocument(Parser.htmlParser().parseInput(html, baseURI), new JSoupEventDispatcher());
	}
}