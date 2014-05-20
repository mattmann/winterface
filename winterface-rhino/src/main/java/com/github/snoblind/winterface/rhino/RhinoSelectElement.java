package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.ExtendedHTMLElement;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLFormElement;
import org.w3c.dom.html.HTMLOptionElement;
import org.w3c.dom.html.HTMLSelectElement;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

public class RhinoSelectElement extends RhinoElement implements HTMLSelectElement {

	public RhinoSelectElement(Element element, RhinoDocument ownerDocument) {
		super(element, ownerDocument, HTMLSelectElement.class, ExtendedHTMLElement.class);
	}

	public int getSelectedIndex() {
		HTMLCollection options = getOptions();
		for (int i = 0; i < options.getLength(); i++) {
			HTMLOptionElement option = (HTMLOptionElement)options.item(i);
			if (option.getSelected()) {
				return i;
			}
		}
		return 0;
	}

	protected HTMLOptionElement getSelectedOption() {
		HTMLCollection options = getOptions();
		if (options.getLength() == 0) {
			return null;
		}
		for (int i = 0; i < options.getLength(); i++) {
			HTMLOptionElement option = (HTMLOptionElement)options.item(i);
			if (option.getSelected()) {
				return option;
			}
		}
		return (HTMLOptionElement)options.item(0);
	}

	public String getValue() {
		HTMLOptionElement option = getSelectedOption();
		if (option == null) {
			return null;
		}
		return option.getValue();
	}

	public HTMLCollection getOptions() {
		return querySelectorAll("option");
	}

	public String getName() {
		final String name = getAttribute("name").toString();
		final String id = getAttribute("id").toString();
		if (isNotEmpty(name)) {
			return name;
		}
		if (isNotEmpty(id)) {
			return id;
		}
		return name;
	}

	public String getType() {
		throw new UnsupportedOperationException();
	}

	public void setSelectedIndex(int selectedIndex) {
		throw new UnsupportedOperationException();
	}

	public void setValue(String value) {
		throw new UnsupportedOperationException();
	}

	public int getLength() {
		throw new UnsupportedOperationException();
	}

	public HTMLFormElement getForm() {
		throw new UnsupportedOperationException();
	}

	public boolean getDisabled() {
		return getAttribute("disabled").equals("disabled");
	}

	public void setDisabled(boolean disabled) {
		setAttribute("disabled", disabled);
	}

	public boolean getMultiple() {
		throw new UnsupportedOperationException();
	}

	public void setMultiple(boolean multiple) {
		throw new UnsupportedOperationException();
	}

	public void setName(String name) {
		throw new UnsupportedOperationException();
	}

	public int getSize() {
		throw new UnsupportedOperationException();
	}

	public void setSize(int size) {
		throw new UnsupportedOperationException();
	}

	public int getTabIndex() {
		throw new UnsupportedOperationException();
	}

	public void setTabIndex(int tabIndex) {
		throw new UnsupportedOperationException();
	}

	public void add(HTMLElement element, HTMLElement before) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void remove(int index) {
		throw new UnsupportedOperationException();
	}

	public void blur() {
		throw new UnsupportedOperationException();
	}

	public void focus() {
		throw new UnsupportedOperationException();
	}

}
