package com.github.mattmann.winterface.rhino;

import org.jsoup.nodes.Document;

import com.github.mattmann.winterface.NodeList;
import com.github.mattmann.winterface.event.EventDispatcher;
import com.github.mattmann.winterface.jsoup.JSoupDocument;

public class RhinoDocument extends JSoupDocument {

	public RhinoDocument(Document document, EventDispatcher eventDispatcher) {
		super(document, eventDispatcher);
	}

	public NodeList querySelectorAll(CharSequence query) {
		return new ScriptableNodeList(super.querySelectorAll(query));
	}

}
