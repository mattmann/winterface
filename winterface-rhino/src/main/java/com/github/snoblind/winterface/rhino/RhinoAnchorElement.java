package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.ExtendedHTMLAnchorElement;
import org.w3c.dom.Element;

public class RhinoAnchorElement extends RhinoElement implements ExtendedHTMLAnchorElement {

	public RhinoAnchorElement(Element element, RhinoDocument ownerDocument) {
		super(element, ownerDocument, ExtendedHTMLAnchorElement.class);
	}

	public String getAccessKey() {
		return getAttribute("accessKey");
	}

	public void setAccessKey(String accessKey) {
		setAttribute("accessKey", accessKey);
	}

	public String getCharset() {
		return getAttribute("charset");
	}

	public void setCharset(String charset) {
		setAttribute("charset", charset);
	}

	public String getCoords() {
		return getAttribute("coords");
	}

	public void setCoords(String coords) {
		setAttribute("coords", coords);
	}

	public String getHref() {
		return getAttribute("href");
	}

	public void setHref(String href) {
		setAttribute("href", href);
	}

	public String getHreflang() {
		return getAttribute("hreflang");
	}

	public void setHreflang(String hreflang) {
		setAttribute("hreflang", hreflang);
	}

	public String getName() {
		return getAttribute("name");
	}

	public void setName(String name) {
		setAttribute("name", name);
	}

	public String getMedia() {
		return getAttribute("media");
	}

	public void setMedia(String media) {
		setAttribute("media", media);
	}

	public String getRel() {
		return getAttribute("rel");
	}

	public void setRel(String rel) {
		setAttribute("rel", rel);
	}

	public String getRev() {
		return getAttribute("rev");
	}

	public void setRev(String rev) {
		setAttribute("rev", rev);
	}

	public String getShape() {
		return getAttribute("shape");
	}

	public void setShape(String shape) {
		setAttribute("shape", shape);
	}

	public int getTabIndex() {
		String value = getAttribute("tabIndex");
		if (value == null) {
			return 0;
		}
		try {
			return Integer.parseInt(value.toString());
		}
		catch (NumberFormatException x) {
			return 0;
		}
	}

	public void setTabIndex(int tabIndex) {
		setAttribute("tabIndex", String.valueOf(tabIndex));
	}

	public String getTarget() {
		return getAttribute("target");
	}

	public void setTarget(String target) {
		setAttribute("target", target);
	}

	public String getType() {
		return getAttribute("type");
	}

	public void setType(String type) {
		setAttribute("type", type);
	}

	public void click() {
		throw new UnsupportedOperationException();
	}

	public void blur() {
		throw new UnsupportedOperationException();
	}

	public void focus() {
		throw new UnsupportedOperationException();
	}
}
