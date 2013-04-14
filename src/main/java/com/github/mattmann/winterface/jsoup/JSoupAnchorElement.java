package com.github.mattmann.winterface.jsoup;


import org.jsoup.nodes.Element;

import com.github.mattmann.winterface.Event;
import com.github.mattmann.winterface.HTMLAnchorElement;

public class JSoupAnchorElement extends JSoupElement implements HTMLAnchorElement {

	public JSoupAnchorElement(Element element, JSoupDocument ownerDocument) {
		super(element, ownerDocument);
	}

	public CharSequence getAccessKey() {
		return getAttribute("accessKey");
	}

	public void setAccessKey(CharSequence accessKey) {
		setAttribute("accessKey", accessKey);
	}

	public CharSequence getCharset() {
		return getAttribute("charset");
	}

	public void setCharset(CharSequence charset) {
		setAttribute("charset", charset);
	}

	public CharSequence getCoords() {
		return getAttribute("coords");
	}

	public void setCoords(CharSequence coords) {
		setAttribute("coords", coords);
	}

	public CharSequence getHref() {
		return getAttribute("href");
	}

	public void setHref(CharSequence href) {
		setAttribute("href", href);
	}

	public CharSequence getHreflang() {
		return getAttribute("hreflang");
	}

	public void setHreflang(CharSequence hreflang) {
		setAttribute("hreflang", hreflang);
	}

	public CharSequence getName() {
		return getAttribute("name");
	}

	public void setName(CharSequence name) {
		setAttribute("name", name);
	}

	public CharSequence getMedia() {
		return getAttribute("media");
	}

	public void setMedia(CharSequence media) {
		setAttribute("media", media);
	}

	public CharSequence getRel() {
		return getAttribute("rel");
	}

	public void setRel(CharSequence rel) {
		setAttribute("rel", rel);
	}

	public CharSequence getRev() {
		return getAttribute("rev");
	}

	public void setRev(CharSequence rev) {
		setAttribute("rev", rev);
	}

	public CharSequence getShape() {
		return getAttribute("shape");
	}

	public void setShape(CharSequence shape) {
		setAttribute("shape", shape);
	}

	public int getTabIndex() {
		CharSequence value = getAttribute("tabIndex");
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

	public CharSequence getTarget() {
		return getAttribute("target");
	}

	public void setTarget(CharSequence target) {
		setAttribute("target", target);
	}

	public CharSequence getType() {
		return getAttribute("type");
	}

	public void setType(CharSequence type) {
		setAttribute("type", type);
	}

	public void click() {
		Event event = ownerDocument.createEvent("Event");
		event.initEvent("click", true, true);
		dispatchEvent(event);
		CharSequence href = getHref(); 
		if (href.length() > 0) {
			getLocation().setHref(href);
		}
	}

	public void blur() {
		throw new UnsupportedOperationException();
	}

	public void focus() {
		throw new UnsupportedOperationException();
	}

	protected JSoupLocation getLocation() {
		return (JSoupLocation)getOwnerDocument().getDefaultView().getLocation();
	}
}
