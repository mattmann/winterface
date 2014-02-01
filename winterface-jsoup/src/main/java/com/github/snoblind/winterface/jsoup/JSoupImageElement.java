package com.github.snoblind.winterface.jsoup;

import com.github.snoblind.winterface.EventListener;
import com.github.snoblind.winterface.ExtendedHTMLImageElement;
import com.github.snoblind.winterface.OnErrorEventHandler;

import org.jsoup.nodes.Element;

public class JSoupImageElement extends JSoupElement implements ExtendedHTMLImageElement {

	public JSoupImageElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public String getLowSrc() {
		return node.attr("lowsrc");
	}

	public void setLowSrc(String lowsrc) {
		node.attr("lowsrc", lowsrc.toString());
	}

	public String getName() {
		return node.attr("name");
	}

	public void setName(String name) {
		node.attr("name", name.toString());
	}

	public String getAlign() {
		return node.attr("align");
	}

	public void setAlign(String align) {
		node.attr("align", align.toString());
	}

	public String getAlt() {
		return node.attr("alt");
	}

	public void setAlt(String alt) {
		node.attr("alt", alt.toString());
	}

	public String getBorder() {
		return node.attr("border");
	}

	public void setBorder(String border) {
		node.attr("border", border.toString());
	}

	public String getHeight() {
		return node.attr("height");
	}

	public void setHeight(String height) {
		node.attr("height", height.toString());
	}

	public String getHspace() {
		return node.attr("hspace");
	}

	public void setHspace(String hspace) {
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

	public String getLongDesc() {
		return node.attr("longDesc");
	}

	public void setLongDesc(String longDesc) {
		node.attr("longDesc", longDesc.toString());
	}

	public String getSrc() {
		return node.attr("src");
	}

	public void setSrc(String src) {
		node.attr("src", src.toString());
	}

	public String getUseMap() {
		return node.attr("useMap");
	}

	public void setUseMap(String useMap) {
		node.attr("useMap", useMap.toString());
	}

	public String getVspace() {
		return node.attr("space");
	}

	public void setVspace(String vspace) {
		node.attr("vspace", vspace.toString());
	}

	public String getWidth() {
		return node.attr("width");
	}

	public void setWidth(String width) {
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