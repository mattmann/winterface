package com.github.snoblind.winterface.nashorn;

import org.w3c.dom.html.HTMLFieldSetElement;
import org.w3c.dom.html.HTMLFormElement;

public class NashornFieldSetElement extends NashornElement implements HTMLFieldSetElement {

	public NashornFieldSetElement(NashornDocument ownerDocument) {
		super("fieldset", ownerDocument);
	}

	public HTMLFormElement getForm() {
		throw new UnsupportedOperationException();
	}
}
