package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.Element;

import com.github.mattmann.winterface.EventListener;
import com.github.mattmann.winterface.HTMLImageElement;
import com.github.mattmann.winterface.OnErrorEventHandler;

public class JSoupImageElement extends JSoupElement implements HTMLImageElement {

	public JSoupImageElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public CharSequence getLowsrc() {
		return node.attr("lowsrc");
	}

	public void setLowsrc(CharSequence lowsrc) {
		node.attr("lowsrc", lowsrc.toString());
	}

	public CharSequence getName() {
		return node.attr("name");
	}

	public void setName(CharSequence name) {
		node.attr("name", name.toString());
	}

	public CharSequence getAlign() {
		return node.attr("align");
	}

	public void setAlign(CharSequence align) {
		node.attr("align", align.toString());
	}

	public CharSequence getAlt() {
		return node.attr("alt");
	}

	public void setAlt(CharSequence alt) {
		node.attr("alt", alt.toString());
	}

	public CharSequence getBorder() {
		return node.attr("border");
	}

	public void setBorder(CharSequence border) {
		node.attr("border", border.toString());
	}

	public CharSequence getHeight() {
		return node.attr("height");
	}

	public void setHeight(CharSequence height) {
		node.attr("height", height.toString());
	}

	public CharSequence getHspace() {
		return node.attr("hspace");
	}

	public void setHspace(CharSequence hspace) {
		node.attr("hspace", hspace.toString());
	}

	public boolean getIsMap() {
		return node.hasAttr("isMap");
	}

	public void setIsMap(boolean isMap) {
		if (isMap) {
			node.attr("isMap", "");
		}
		else {
			node.removeAttr("isMap");
		}
	}

	public CharSequence getLongDesc() {
		return node.attr("longDesc");
	}

	public void setLongDesc(CharSequence longDesc) {
		node.attr("longDesc", longDesc.toString());
	}

	public CharSequence getSrc() {
		return node.attr("src");
	}

	public void setSrc(CharSequence src) {
		node.attr("src", src.toString());
	}

	public CharSequence getUseMap() {
		return node.attr("useMap");
	}

	public void setUseMap(CharSequence useMap) {
		node.attr("useMap", useMap.toString());
	}

	public CharSequence getVspace() {
		return node.attr("space");
	}

	public void setVspace(CharSequence vspace) {
		node.attr("vspace", vspace.toString());
	}

	public CharSequence getWidth() {
		return node.attr("width");
	}

	public void setWidth(CharSequence width) {
		node.attr("width", width.toString());
	}

	public boolean isComplete() {
		return false;
	}
	
	private EventListener onabort;

	public EventListener getOnabort() {
		return onabort;
	}

	public void setOnabort(EventListener onabort) {
		this.onabort = onabort;
	}
	
	private OnErrorEventHandler onerror;

	public OnErrorEventHandler getOnerror() {
		return onerror;
	}

	public void setOnerror(OnErrorEventHandler onerror) {
		this.onerror = onerror;
	}

	private EventListener onload;

	public EventListener getOnload() {
		return onload;
	}

	public void setOnload(EventListener onload) {
		this.onload = onload;
	}
}