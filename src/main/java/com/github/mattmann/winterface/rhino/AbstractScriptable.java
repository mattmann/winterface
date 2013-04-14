package com.github.mattmann.winterface.rhino;

import org.mozilla.javascript.Scriptable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractScriptable implements Scriptable {

	private static final Logger logger = LoggerFactory.getLogger(AbstractScriptable.class);

	public String getClassName() {
		return getClass().getName();
	}

	public Object get(String name, Scriptable start) {
		logger.debug("get({}, {})", name, start);
//		throw new UnsupportedOperationException();
		return null;
	}

	public Object get(int index, Scriptable start) {
		logger.debug("get({}, {})", index, start);
		throw new UnsupportedOperationException();
	}

	public boolean has(String name, Scriptable start) {
		logger.debug("has({}, {})", name, start);
		return false;
//		throw new UnsupportedOperationException();
	}

	public boolean has(int index, Scriptable start) {
		logger.debug("get({}, {})", index, start);
		throw new UnsupportedOperationException();
	}

	public void put(String name, Scriptable start, Object value) {
		logger.debug("put({}, {}, {})", name, start, value);
		throw new UnsupportedOperationException();
	}

	public void put(int index, Scriptable start, Object value) {
		logger.debug("put({}, {}, {})", index, start, value);
		throw new UnsupportedOperationException();
	}

	public void delete(String name) {
		logger.debug("{}.delete({})", getClass().getName(), name);
		throw new UnsupportedOperationException();
	}

	public void delete(int index) {
		logger.debug("delete({})", index);
		throw new UnsupportedOperationException();
	}

	public Scriptable getPrototype() {
		logger.debug("getPrototype()");
		return null;
//		throw new UnsupportedOperationException();
	}

	public void setPrototype(Scriptable prototype) {
		logger.debug("setPrototype({})", prototype);
		throw new UnsupportedOperationException();
	}

	public Scriptable getParentScope() {
		logger.debug("getParentScope()");
		throw new UnsupportedOperationException();
	}

	public void setParentScope(Scriptable parent) {
		logger.debug("setParentScope({})", parent);
		throw new UnsupportedOperationException();
	}

	public Object[] getIds() {
		logger.debug("getIds()");
		throw new UnsupportedOperationException();
	}

	public boolean hasInstance(Scriptable instance) {
		logger.debug("hasInstance({})", instance);
		throw new UnsupportedOperationException();
	}

	public Object getDefaultValue(Class<?> hint) {
		logger.debug("getDefaultValue({})", hint);
		return toString();
	}
}