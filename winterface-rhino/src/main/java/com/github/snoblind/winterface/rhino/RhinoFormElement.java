package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.ExtendedHTMLFormElement;
import com.github.snoblind.winterface.util.NodeListUtils;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLInputElement;

public class RhinoFormElement extends RhinoElement implements ExtendedHTMLFormElement {

	private static final String ELEMENTS = "button, datalist, input, keygen, select, output, textarea";

	public RhinoFormElement(Element element, RhinoDocument ownerDocument) {
		super(element, ownerDocument, ExtendedHTMLFormElement.class);
	}

	public void submit() {
		getLocation().submit(getMethod(), getAction(), getData());
	}

	private String getData() {
		final NodeList elements = querySelectorAll(ELEMENTS);
		if (0 == elements.getLength()) {
			return StringUtils.EMPTY;
		}
		final StringBuilder builder = new StringBuilder();
		for (Node node: NodeListUtils.iterable(elements)) {
			if (builder.length() > 0) {
				builder.append("&");
			}
			final String name = getName((HTMLElement) node);
			final String value = getValue((HTMLElement) node);
			builder.append(encodeURIComponent(name)).append("=").append(encodeURIComponent(value));
		}
		return builder.toString();
	}

	private String getName(HTMLElement element) {
		if (element instanceof HTMLInputElement) {
			return ((HTMLInputElement) element).getName();
		}
		throw new IllegalArgumentException(element.getClass().getName());
	}

	private String getValue(HTMLElement element) {
		if (element instanceof HTMLInputElement) {
			return ((HTMLInputElement) element).getValue();
		}
		throw new IllegalArgumentException(element.getClass().getName());
	}
	
	public HTMLCollection getElements() {
		return NodeListUtils.toHTMLCollection(querySelectorAll(ELEMENTS));
	}

	public String getMethod() {
		return getAttribute("method");
	}

	public void setMethod(String method) {
		setAttribute("method", method);
	}

	public int getLength() {
		return getElements().getLength();
	}

	public String getName() {
		return getAttribute("name");
	}

	public void setName(String name) {
		setAttribute("name", name);
	}

	public String getAcceptCharset() {
		return getAttribute("accept-charset");
	}

	public void setAcceptCharset(String acceptCharset) {
		setAttribute("accept-charset", acceptCharset);
	}

	public String getAction() {
		return getAttribute("action");
	}

	public void setAction(String action) {
		setAttribute("action", action);
	}

	public String getEnctype() {
		return getAttribute("enctype");
	}

	public void setEnctype(String enctype) {
		setAttribute("enctype", enctype);
	}

	public String getTarget() {
		return getAttribute("target");
	}

	public void setTarget(String target) {
		setAttribute("target", target);
	}

	public void reset() {
		throw new UnsupportedOperationException();
	}
}
