package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.ExtendedHTMLImageElement;
import org.w3c.dom.Element;

public class RhinoImageElement extends RhinoElement implements ExtendedHTMLImageElement {

	public RhinoImageElement(Element element, RhinoDocument ownerDocument) {
		super(element, ownerDocument, ExtendedHTMLImageElement.class);
	}

	public String getLowSrc() {
		throw new UnsupportedOperationException();
	}

	public void setLowSrc(String lowSrc) {
		throw new UnsupportedOperationException();
	}

	public String getName() {
		throw new UnsupportedOperationException();
	}

	public void setName(String name) {
		throw new UnsupportedOperationException();
	}

	public String getAlign() {
		throw new UnsupportedOperationException();
	}

	public void setAlign(String align) {
		throw new UnsupportedOperationException();
	}

	public String getAlt() {
		throw new UnsupportedOperationException();
	}

	public void setAlt(String alt) {
		throw new UnsupportedOperationException();
	}

	public String getBorder() {
		throw new UnsupportedOperationException();
	}

	public void setBorder(String border) {
		throw new UnsupportedOperationException();
	}

	public String getHeight() {
		throw new UnsupportedOperationException();
	}

	public void setHeight(String height) {
		throw new UnsupportedOperationException();
	}

	public String getHspace() {
		throw new UnsupportedOperationException();
	}

	public void setHspace(String hspace) {
		throw new UnsupportedOperationException();
	}

	public boolean getIsMap() {
		throw new UnsupportedOperationException();
	}

	public void setIsMap(boolean isMap) {
		throw new UnsupportedOperationException();
	}

	public String getLongDesc() {
		throw new UnsupportedOperationException();
	}

	public void setLongDesc(String longDesc) {
		throw new UnsupportedOperationException();
	}

	public String getSrc() {
		throw new UnsupportedOperationException();
	}

	public void setSrc(String src) {
		throw new UnsupportedOperationException();
	}

	public String getUseMap() {
		throw new UnsupportedOperationException();
	}

	public void setUseMap(String useMap) {
		throw new UnsupportedOperationException();
	}

	public String getVspace() {
		throw new UnsupportedOperationException();
	}

	public void setVspace(String vspace) {
		throw new UnsupportedOperationException();
	}

	public String getWidth() {
		throw new UnsupportedOperationException();
	}

	public void setWidth(String width) {
		throw new UnsupportedOperationException();
	}

	public boolean isComplete() {
		throw new UnsupportedOperationException();
	}
}
