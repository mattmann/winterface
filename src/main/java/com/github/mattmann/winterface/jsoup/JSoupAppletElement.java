package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.Element;

import com.github.mattmann.winterface.HTMLAppletElement;

public class JSoupAppletElement extends JSoupElement implements HTMLAppletElement {

	public JSoupAppletElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public CharSequence getAlign() {
		return getAttribute("align");
	}

	public void setAlign(CharSequence align) {
		setAttribute("align", align);
	}

	public CharSequence getAlt() {
		return getAttribute("alt");
	}

	public void setAlt(CharSequence alt) {
		setAttribute("alt", alt);
	}

	public CharSequence getArchive() {
		return getAttribute("archive");
	}

	public void setArchive(CharSequence archive) {
		setAttribute("archive", archive);
	}

	public CharSequence getCode() {
		return getAttribute("code");
	}

	public void setCode(CharSequence code) {
		setAttribute("code", code);
	}

	public CharSequence getCodeBase() {
		return getAttribute("codeBase");
	}

	public void setCodeBase(CharSequence codeBase) {
		setAttribute("codeBase", codeBase);
	}

	public CharSequence getHeight() {
		return getAttribute("height");
	}

	public void setHeight(CharSequence height) {
		setAttribute("height", height);
	}

	public CharSequence getHspace() {
		return getAttribute("hspace");
	}

	public void setHspace(CharSequence hspace) {
		setAttribute("hspace", hspace);
	}

	public CharSequence getName() {
		return getAttribute("name");
	}

	public void setName(CharSequence name) {
		setAttribute("name", name);
	}

	public CharSequence getObject() {
		return getAttribute("object");
	}

	public void setObject(CharSequence object) {
		setAttribute("object", object);
	}

	public CharSequence getVspace() {
		return getAttribute("vspace");
	}

	public void setVspace(CharSequence vspace) {
		setAttribute("vspace", vspace);
	}

	public CharSequence getWidth() {
		return getAttribute("width");
	}

	public void setWidth(CharSequence width) {
		setAttribute("width", width);
	}
}
