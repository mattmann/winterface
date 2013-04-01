package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.Document;

import com.github.mattmann.winterface.HTMLFormElement;
import com.github.mattmann.winterface.HTMLFormElementTest;
import com.github.mattmann.winterface.jsoup.JSoupDocument;
import com.github.mattmann.winterface.jsoup.JSoupEventDispatcher;


public class JSoupFormElementTest extends HTMLFormElementTest {

	protected HTMLFormElement createHTMLFormElement() {
		return (HTMLFormElement)new JSoupDocument(new Document(""), new JSoupEventDispatcher()).createElement("form");
	}
}
