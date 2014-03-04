package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.util.ReflectionUtils;
import java.beans.PropertyDescriptor;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.css.CSSValue;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.Validate.notNull;

public class RhinoCSSStyleDeclaration extends ScriptableObject implements CSSStyleDeclaration, Scriptable {

	private static final long serialVersionUID = -4250115519196359134L;
	private static final Logger LOGGER = LoggerFactory.getLogger(RhinoCSSStyleDeclaration.class);
	private static final Map<String, PropertyDescriptor> PROPERTY_DESCRIPTORS_BY_NAME = ReflectionUtils.propertyDescriptorsByName(RhinoCSSStyleDeclaration.class);

	private final Element element;

	public RhinoCSSStyleDeclaration(Element element) {
		notNull(this.element = element);
	}

	public String getBackgroundClip() {
		return getPropertyValue("background-clip");
	}
	
	public String getCssText() {
		throw new UnsupportedOperationException();
	}

	public void setCssText(String cssText) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public String getPropertyValue(final String name) {
		LOGGER.debug("{}.getPropertyValue({})", getClass().getName(), name);
		final String style = element.getAttribute("style");
		if (isBlank(style)) {
			return StringUtils.EMPTY;
		}
		for (String token: style.split(";")) {
			token = token.trim();
			if (token.startsWith(name)) {
				int index = token.indexOf(':');
				if (index > 0) {
					return token.substring(index + 1).trim();
				}
			}
		}
		return StringUtils.EMPTY;
	}

	public CSSValue getPropertyCSSValue(String propertyName) {
		throw new UnsupportedOperationException();
	}

	public String removeProperty(String propertyName) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public String getPropertyPriority(String propertyName) {
		throw new UnsupportedOperationException();
	}

	public void setProperty(String propertyName, String value, String priority) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public int getLength() {
		throw new UnsupportedOperationException();
	}

	public String item(int index) {
		throw new UnsupportedOperationException();
	}

	public CSSRule getParentRule() {
		throw new UnsupportedOperationException();
	}

	public Object get(String name, Scriptable start) {
		LOGGER.debug("{}.has({}, {})", getClass().getName(), name, start);
		if (PROPERTY_DESCRIPTORS_BY_NAME.containsKey(name)) {
			LOGGER.info("Instances of {} have a property named \"{}\".", getClass(), name);
			return ReflectionUtils.getPropertyValue(this, PROPERTY_DESCRIPTORS_BY_NAME.get(name));
		}
		final Object result = super.get(name, start);
		if (NOT_FOUND.equals(result)) {
			LOGGER.warn("Window has no such member \"{}\".", name);
		}
		return result;
	}

	public boolean has(String name, Scriptable start) {
		LOGGER.debug("{}.has({}, {})", getClass().getName(), name, start);
		return PROPERTY_DESCRIPTORS_BY_NAME.containsKey(name) || super.has(name, start);
	}

	public String getClassName() {
		throw new UnsupportedOperationException();
	}
}
