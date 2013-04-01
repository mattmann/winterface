package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.Element;

import com.github.mattmann.winterface.HTMLBodyElement;

public class JSoupBodyElement extends JSoupElement implements HTMLBodyElement {

	public JSoupBodyElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public CharSequence getALink() {
		return node.attr("alink");
	}

	public void setALink(CharSequence aLink) {
		node.attr("alink", aLink.toString());
	}

	public CharSequence getBackground() {
		return node.attr("background");
	}

	public void setBackground(CharSequence background) {
		node.attr("background", background.toString());
	}

	public CharSequence getBgColor() {
		return node.attr("bgcolor");
	}

	public void setBgColor(CharSequence bgColor) {
		node.attr("bgcolor", bgColor.toString());
	}

	public CharSequence getLink() {
		return node.attr("link");
	}

	public void setLink(CharSequence link) {
		node.attr("link", link.toString());
	}

	public CharSequence getText() {
		return node.attr("text");
	}

	public void setText(CharSequence text) {
		node.attr("text", text.toString());
	}

	public CharSequence getVLink() {
		return node.attr("vlink");
	}

	public void setVLink(CharSequence vLink) {
		node.attr("vlink", vLink.toString());
	}
}
