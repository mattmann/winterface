package com.github.mattmann.winterface.rhino;

import org.jsoup.nodes.Document;

import com.github.mattmann.winterface.HTMLCollection;
import com.github.mattmann.winterface.NodeList;
import com.github.mattmann.winterface.event.EventDispatcher;
import com.github.mattmann.winterface.jsoup.JSoupDocument;

public class RhinoDocument extends JSoupDocument {

	public RhinoDocument(Document document, EventDispatcher eventDispatcher) {
		super(document, eventDispatcher);
	}

	public NodeList querySelectorAll(String query) {
		return new ScriptableNodeList(super.querySelectorAll(query));
	}

	public HTMLCollection getLinks() {
		return new ScriptableHTMLCollection(super.getLinks());
	}

}
