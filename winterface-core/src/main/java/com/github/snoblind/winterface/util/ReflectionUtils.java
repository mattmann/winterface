package com.github.snoblind.winterface.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.toLowerCase;
import static java.lang.String.format;
import static org.apache.commons.lang.Validate.notNull;

public final class ReflectionUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtils.class);

	public static Method findMethod(final Class<?> objectClass, final String name) throws NoSuchMethodException {
		notNull(objectClass);
		notNull(name);
		for (Method method: objectClass.getMethods()) {
			if (method.getName().equals(name)) {
				return method;
			}
		}
		throw new NoSuchMethodException(format("Class %s has no method named \"%s\".", objectClass.getName(), name));
	}

	public static Map<String, PropertyDescriptor> propertyDescriptorsByName(final Class<?> beanClass) {
		final Map<String, PropertyDescriptor> map = new TreeMap<String, PropertyDescriptor>();
		for (Method method: beanClass.getMethods()) {
			final boolean readMethod = isReadMethod(method);
			final boolean writeMethod = !readMethod && isWriteMethod(method);
//			LOGGER.debug("isReadMethod({}) => {}", method, readMethod);
//			LOGGER.debug("isWriteMethod({}) => {}", method, writeMethod);
			if (!(readMethod || writeMethod)) {
				continue;
			}
			final String name = propertyName(method);
			LOGGER.debug("{} => {}", method, name);
			try {
				PropertyDescriptor descriptor = map.get(name);
				if (descriptor == null) {
					map.put(name, descriptor = new PropertyDescriptor(name, beanClass, null, null));
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
		return Collections.unmodifiableMap(map);
	}

	public static boolean isReadMethod(final Method method) {
		notNull(method);
		return (method.getName().length() > 3 && method.getName().startsWith("get")
					|| method.getName().length() > 2 && method.getName().startsWith("is"))
				&& 0 == method.getParameterTypes().length;
	}

	public static boolean isWriteMethod(final Method method) {
		notNull(method);
		return method.getName().length() > 3
				&& method.getName().startsWith("set")
				&& 1 == method.getParameterTypes().length;
	}

	public static String propertyName(final Method method) {
		final String property = method.getName().substring(method.getName().startsWith("is") ? 2 : 3);
		if (1 == property.length()) {
			return property.toLowerCase();
		}
		if (isUpperCase(property.charAt(0)) && isUpperCase(property.charAt(1))) {
			return property;
		}
		return new StringBuilder().append(toLowerCase(property.charAt(0))).append(property.substring(1)).toString();
	}
	
	public static Object getPropertyValue(final Object bean, final PropertyDescriptor descriptor) {
		notNull(bean);
		notNull(descriptor);
		final String name = descriptor.getName();
		LOGGER.debug("{}.getPropertyValue({}, {})", bean.getClass().getName(), name);
		final Method method = descriptor.getReadMethod();
		if (method == null) {
			throw new RuntimeException(format("No read method for property \"%s\"?", name));
		}
		try {
			return method.invoke(bean);
		}
		catch (IllegalAccessException x) {
			LOGGER.error(String.format("An exception was thrown while attempting to get property %s of an instance of %s.", name, bean.getClass()), x);
			throw new RuntimeException(x);
		}
		catch (IllegalArgumentException x) {
			LOGGER.error(String.format("An exception was thrown while attempting to get property %s of an instance of %s.", name, bean.getClass()), x);
			throw new RuntimeException(x);
		}
		catch (InvocationTargetException x) {
			LOGGER.error(String.format("An exception was thrown while attempting to get property %s of an instance of %s.", name, bean.getClass()), x);
			throw new RuntimeException(x);
		}
	}

	public static void setPropertyValue(final Object bean, final PropertyDescriptor descriptor, Object value) {
		notNull(bean);
		notNull(descriptor);
		final String name = descriptor.getName();
		final Class<?> type = descriptor.getPropertyType();
		if (value != null && !type.isAssignableFrom(value.getClass())) {
			try {
				value = ConvertUtils.convert(value, type);
				LOGGER.debug("Successfully converted value of type {} to type {}.", value.getClass().getName(), type);
			}
			catch (ConversionException x) {
				throw new IllegalArgumentException(format("Cannot convert value %s (instance of %s) to type %s.", value, value.getClass(), type), x);
			}
		}
		LOGGER.debug("{}.setPropertyValue({}, {}, {})", bean.getClass().getName(), name, value);
		final Method method = descriptor.getWriteMethod();
		if (method == null) {
			throw new RuntimeException(format("No write method for property \"%s\"?", name));
		}
		try {
			method.invoke(bean, value);
		}
		catch (IllegalAccessException x) {
			LOGGER.error(String.format("An exception was thrown while attempting to set property %s to value %s of an instance of %s.", name, value, bean.getClass()), x);
			throw new RuntimeException(x);
		}
		catch (IllegalArgumentException x) {
			LOGGER.error(String.format("An exception was thrown while attempting to set property %s to value %s of an instance of %s.", name, value, bean.getClass()), x);
			throw new RuntimeException(x);
		}
		catch (InvocationTargetException x) {
			LOGGER.error(String.format("An exception was thrown while attempting to set property %s to value %s of an instance of %s.", name, value, bean.getClass()), x);
			throw new RuntimeException(x);
		}
	}
}