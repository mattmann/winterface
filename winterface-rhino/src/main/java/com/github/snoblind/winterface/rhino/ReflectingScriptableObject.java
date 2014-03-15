package com.github.snoblind.winterface.rhino;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.github.snoblind.winterface.util.ReflectionUtils.isReadMethod;
import static com.github.snoblind.winterface.util.ReflectionUtils.isWriteMethod;
import static com.github.snoblind.winterface.util.ReflectionUtils.getPropertyValue;
import static com.github.snoblind.winterface.util.ReflectionUtils.setPropertyValue;
import static com.github.snoblind.winterface.util.ReflectionUtils.propertyName;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.apache.commons.lang.Validate.notEmpty;
import static org.apache.commons.lang.Validate.notNull;

public class ReflectingScriptableObject implements Scriptable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReflectingScriptableObject.class);

	private final Object instance;
	private final List<Class<?>> interfaces;
	private final Map<String, PropertyDescriptor> propertyDescriptorsByName;
	private final Map<String, Function> functionsByName;
	private final Map<String, Object> adHocValuesByName = new HashMap<String, Object>();

	private Scriptable prototype;
	
	public ReflectingScriptableObject(final Object instance, final Class<?>... interfaces) {
		notEmpty(interfaces);
		notNull(instance);
		this.instance = instance;
		this.interfaces = asList(interfaces);
		this.propertyDescriptorsByName = propertyDescriptorsByName();
		this.functionsByName = functionsByName();
	}
	
	public Map<String, PropertyDescriptor> getPropertyDescriptorsByName() {
		return propertyDescriptorsByName;
	}

	public Map<String, Function> getFunctionsByName() {
		return functionsByName;
	}

	private Map<String, PropertyDescriptor> propertyDescriptorsByName() {
		final Map<String, PropertyDescriptor> map = new TreeMap<String, PropertyDescriptor>();
		for (Class<?> i: interfaces)  {
			for (Method method: i.getMethods()) {
				final boolean readMethod = isReadMethod(method);
				final boolean writeMethod = !readMethod && isWriteMethod(method);
				if (!(readMethod || writeMethod)) {
					continue;
				}
				final String name = propertyName(method);
				LOGGER.debug("{} => {}", method, name);
				try {
					PropertyDescriptor descriptor = map.get(name);
					if (descriptor == null) {
						map.put(name, descriptor = new PropertyDescriptor(name, i, null, null));
					}
					if (readMethod) {
						descriptor.setReadMethod(method);
					}
					else {
						descriptor.setWriteMethod(method);
					}
				}
				catch (IntrospectionException x) {
					throw new RuntimeException(x);
				}
			}
		}
		return Collections.unmodifiableMap(map);
	}

	private Map<String, Function> functionsByName() {
		final Map<String, Function> map = new TreeMap<String, Function>();
		for (Class<?> i: interfaces)  {
			final Method[] methods = i.getMethods();
			for (Method method: methods) {
				if (isReadMethod(method) || isWriteMethod(method)) {
					continue;
				}
				final String name = method.getName();
				if (method.getDeclaringClass().equals(Object.class)) {
					LOGGER.debug("Skipping java.lang.Object method \"{}\".", name);
					continue;
				}
				if (map.containsKey(name)) {
					LOGGER.info("Method functions by name for {} already contains key \"{}\".", i.getName(), name);
				}
				else {
					map.put(name, new MethodFunction(instance, method));
				}
			}
		}
		return Collections.unmodifiableMap(map);
	}

	public Scriptable getPrototype() {
		return prototype;
	}

	public void setPrototype(Scriptable prototype) {
		this.prototype = prototype;
	}

	public Object get(String name, Scriptable start) {
		LOGGER.debug("get({}, {})", name, start);
		if (propertyDescriptorsByName.containsKey(name)) {
			LOGGER.debug("Instances of {} have a property named \"{}\".", instance.getClass(), name);
			return getPropertyValue(instance, propertyDescriptorsByName.get(name));
		}
		if (functionsByName.containsKey(name)) {
			LOGGER.debug("Found function named \"{}\" in Map.", name);
			return functionsByName.get(name);
		}
		if (start == instance) {
			if (adHocValuesByName.containsKey(name)) {
				return adHocValuesByName.get(name);
			}
			else {
				return NOT_FOUND;
			}
		}
		LOGGER.warn("get(\"{}\", {}) => NOT FOUND", name, start);
		return NOT_FOUND;
	}

	public void put(String name, Scriptable start, Object value) {
		LOGGER.debug("{}.put({}, {}, {})", getClass().getName(), name, start, value);
		if (propertyDescriptorsByName.containsKey(name)) {
			LOGGER.info("Instances of {} have a property named \"{}\".", instance.getClass(), name);
			final PropertyDescriptor descriptor = propertyDescriptorsByName.get(name);
			final Class<?> type = descriptor.getPropertyType();
			setPropertyValue(instance, descriptor, RhinoTypeConverter.getInstance().convert(Context.getCurrentContext(), start, start, value, type));
		}
		else if (start == instance) {
			adHocValuesByName.put(name, value);
		}
		else {
			throw new UnsupportedOperationException(format("put(\"%s\", %s, %s)", name, start, value));
		}
	}

	public Object getDefaultValue(Class<?> type) {
		if (String.class.equals(type)) {
			return toString();
		}
		throw new UnsupportedOperationException();
	}

	public String getClassName() {
		return instance.getClass().getName();
	}

	public Object get(int index, Scriptable start) {
		throw new UnsupportedOperationException();
	}

	public boolean has(String name, Scriptable start) {
		LOGGER.debug("has({}, {})", name, start);
		if (propertyDescriptorsByName.containsKey(name)) {
			return true;
		}
		if (functionsByName.containsKey(name)) {
			return true;
		}
		if (start == instance) {
			return adHocValuesByName.containsKey(name);
		}
		throw new UnsupportedOperationException(format("has(\"%s\", %s)", name, start));
	}

	public boolean has(int index, Scriptable start) {
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

	public Scriptable getParentScope() {
		throw new UnsupportedOperationException();
	}

	public void setParentScope(Scriptable parent) {
		throw new UnsupportedOperationException();
	}

	public Object[] getIds() {
		throw new UnsupportedOperationException();
	}

	public boolean hasInstance(Scriptable instance) {
		throw new UnsupportedOperationException();
	}
}
