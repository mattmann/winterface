package com.github.snoblind.winterface.rhino;

import static com.github.snoblind.winterface.util.ReflectionUtils.getPropertyValue;
import static com.github.snoblind.winterface.util.ReflectionUtils.propertyDescriptorsByName;
import com.github.snoblind.winterface.Navigator;
import java.beans.PropertyDescriptor;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RhinoNavigator extends ScriptableObject implements Navigator {

	private static Logger LOGGER = LoggerFactory.getLogger(RhinoNavigator.class);

	private static final long serialVersionUID = 446125723636744005L;

	private static final Map<String, PropertyDescriptor> PROPERTY_DESCRIPTORS_BY_NAME = propertyDescriptorsByName(RhinoNavigator.class);

	public String getAppName() {
		throw new UnsupportedOperationException();
	}

	public String getAppVersion() {
		throw new UnsupportedOperationException();
	}

	public String getPlatform() {
		throw new UnsupportedOperationException();
	}

	public String getUserAgent() {
		return StringUtils.EMPTY;
	}

	public String getLanguage() {
		throw new UnsupportedOperationException();
	}

	public boolean isOnLine() {
		throw new UnsupportedOperationException();
	}

	public String isContentHandlerRegistered(String mimeType, String url) {
		throw new UnsupportedOperationException();
	}

	public String isProtocolHandlerRegistered(String scheme, String url) {
		throw new UnsupportedOperationException();
	}

	public void registerContentHandler(String mimeType, String url, String title) {
		throw new UnsupportedOperationException();
	}

	public void registerProtocolHandler(String scheme, String url, String title) {
		throw new UnsupportedOperationException();
	}

	public void unregisterContentHandler(String mimeType, String url) {
		throw new UnsupportedOperationException();
	}

	public void unregisterProtocolHandler(String scheme, String url) {
		throw new UnsupportedOperationException();
	}

	public void yieldForStorageUpdates() {
		throw new UnsupportedOperationException();
	}

	public String getClassName() {
		return getClass().getName();
	}

	public Object get(String name, Scriptable start) {
		LOGGER.debug("get({}, {})", name, start);
		if (PROPERTY_DESCRIPTORS_BY_NAME.containsKey(name)) {
			LOGGER.info("Instances of {} have a property named \"{}\".", getClass(), name);
			return getPropertyValue(this, PROPERTY_DESCRIPTORS_BY_NAME.get(name));
		}
		final Object result = super.get(name, start);
		if (NOT_FOUND.equals(result)) {
			LOGGER.warn("Navigator has no such member \"{}\".", name);
		}
		return result;
	}

	public Object get(int index, Scriptable start) {
		LOGGER.debug("get({}, {})", index, start);
		return super.get(index, start);
	}

	public boolean has(String name, Scriptable start) {
		LOGGER.debug("has({}, {})", name, start);
		return super.has(name, start);
	}

	public boolean has(int index, Scriptable start) {
		LOGGER.debug("has({}, {})", index, start);
		return super.has(index, start);
	}

	public void put(String name, Scriptable start, Object value) {
		throw new UnsupportedOperationException();
	}

	public void put(int index, Scriptable start, Object value) {
		throw new UnsupportedOperationException();
	}

	public void delete(String name) {
		throw new UnsupportedOperationException();
	}

	public void delete(int index) {
		throw new UnsupportedOperationException();
	}

	public Scriptable getPrototype() {
		final Scriptable prototype = super.getPrototype();
		LOGGER.debug("getPrototype() => {}", prototype);
		return prototype;
	}

	public void setPrototype(Scriptable prototype) {
		throw new UnsupportedOperationException();
	}

	public Scriptable getParentScope() {
		throw new UnsupportedOperationException();
	}

	public void setParentScope(Scriptable parent) {
		throw new UnsupportedOperationException();
	}

	public Object[] getIds() {
		throw new UnsupportedOperationException();
	}

	public Object getDefaultValue(Class<?> hint) {
		throw new UnsupportedOperationException();
	}

	public boolean hasInstance(Scriptable instance) {
		throw new UnsupportedOperationException();
	}
}