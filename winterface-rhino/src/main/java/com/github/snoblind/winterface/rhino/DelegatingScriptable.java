package com.github.snoblind.winterface.rhino;

import org.mozilla.javascript.Scriptable;
import static org.apache.commons.lang.Validate.notNull;

public class DelegatingScriptable implements Scriptable {

	protected Scriptable scriptable;
	
	public DelegatingScriptable(Scriptable scriptable) {
		notNull(scriptable);
		this.scriptable = scriptable;
	}

	protected DelegatingScriptable() {
	}
	
	public String getClassName() {
		return scriptable.getClassName();
	}

	public Object get(String name, Scriptable start) {
		return scriptable.get(name, start);
	}

	public Object get(int index, Scriptable start) {
		return scriptable.get(index, start);
	}

	public boolean has(String name, Scriptable start) {
		return scriptable.has(name, start);
	}

	public boolean has(int index, Scriptable start) {
		return scriptable.has(index, start);
	}

	public void put(String name, Scriptable start, Object value) {
		scriptable.put(name, start, value);
	}

	public void put(int index, Scriptable start, Object value) {
		scriptable.put(index, start, value);
	}

	public void delete(String name) {
		scriptable.delete(name);
	}

	public void delete(int index) {
		scriptable.delete(index);
	}

	public Scriptable getPrototype() {
		return scriptable.getPrototype();
	}

	public void setPrototype(Scriptable prototype) {
		scriptable.setPrototype(prototype);
	}

	public Scriptable getParentScope() {
		return scriptable.getParentScope();
	}

	public void setParentScope(Scriptable parent) {
		scriptable.setParentScope(parent);
	}

	public Object[] getIds() {
		return scriptable.getIds();
	}

	public Object getDefaultValue(Class<?> hint) {
		return scriptable.getDefaultValue(hint);
	}

	public boolean hasInstance(Scriptable instance) {
		return scriptable.hasInstance(instance);
	}
}
