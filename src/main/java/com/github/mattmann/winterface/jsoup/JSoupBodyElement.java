package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.Element;
import org.w3c.dom.html.HTMLBodyElement;

public class JSoupBodyElement extends JSoupElement implements HTMLBodyElement {

	public JSoupBodyElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public String getALink() {
		return node.attr("alink");
	}

	public void setALink(String aLink) {
		node.attr("alink", aLink.toString());
	}

	public String getBackground() {
		return node.attr("background");
	}

	public void setBackground(String background) {
		node.attr("background", background.toString());
	}

	public String getBgColor() {
		return node.attr("bgcolor");
	}

	public void setBgColor(String bgColor) {
		node.attr("bgcolor", bgColor.toString());
	}

	public String getLink() {
		return node.attr("link");
	}

	public void setLink(String link) {
		node.attr("link", link.toString());
	}

	public String getText() {
		return node.attr("text");
	}

	public void setText(String text) {
		node.attr("text", text.toString());
	}

	public String getVLink() {
		return node.attr("vlink");
	}

	public void setVLink(String vLink) {
		node.attr("vlink", vLink.toString());
	}
}
