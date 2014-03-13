package com.github.snoblind.winterface.rhino;

import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLBodyElement;

public class RhinoBodyElement extends RhinoElement implements HTMLBodyElement {

	private static final long serialVersionUID = 1525438149388688203L;

	public RhinoBodyElement(Element element, RhinoDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public String getALink() {
		return getAttribute("alink");
	}

	public void setALink(String aLink) {
		setAttribute("alink", aLink);
	}

	public String getBackground() {
		return getAttribute("background");
	}

	public void setBackground(String background) {
		setAttribute("background", background);
	}

	public String getBgColor() {
		return getAttribute("bgcolor");
	}

	public void setBgColor(String bgColor) {
		setAttribute("bgcolor", bgColor);
	}

	public String getLink() {
		return getAttribute("link");
	}

	public void setLink(String link) {
		setAttribute("link", link);
	}

	public String getText() {
		return getAttribute("text");
	}

	public void setText(String text) {
		setAttribute("text", text);
	}

	public String getVLink() {
		return getAttribute("vlink");
	}

	public void setVLink(String vLink) {
		setAttribute("vlink", vLink);
	}
}