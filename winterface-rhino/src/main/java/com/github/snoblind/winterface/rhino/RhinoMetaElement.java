package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.ExtendedHTMLMetaElement;
import org.w3c.dom.Element;

public class RhinoMetaElement extends RhinoElement implements ExtendedHTMLMetaElement {

	private static final long serialVersionUID = -9154447520953327183L;

	public RhinoMetaElement(Element element, RhinoDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public String getContent() {
		return getAttribute("content");
	}

	public void setContent(String content) {
		setAttribute("content", content);
	}

	public String getHttpEquiv() {
		return getAttribute("http-equiv");
	}

	public void setHttpEquiv(String httpEquiv) {
		setAttribute("http-equiv", httpEquiv);
	}

	public String getName() {
		return getAttribute("name");
	}

	public void setName(String name) {
		this.setAttribute("name", name);
	}

	public String getScheme() {
		return getAttribute("scheme");
	}

	public void setScheme(String scheme) {
		setAttribute("scheme", scheme);
	}

	public String getCharset() {
		return getAttribute("charset");
	}

	public void setCharset(String charset) {
		setAttribute("charset", charset);
	}
}
