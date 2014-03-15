package com.github.snoblind.winterface.rhino;

import org.w3c.dom.DocumentFragment;
import static org.apache.commons.lang.Validate.notNull;

public class RhinoDocumentFragment extends RhinoNode<DocumentFragment> implements DocumentFragment {

	private final RhinoDocument ownerDocument;
	
	public RhinoDocumentFragment(DocumentFragment documentFragment, RhinoDocument ownerDocument) {
		super(documentFragment, DocumentFragment.class);
		notNull(this.ownerDocument = ownerDocument);
	}

	public RhinoDocument getOwnerDocument() {
		return ownerDocument;
	}

	public String getClassName() {
		throw new UnsupportedOperationException();
	}
}
