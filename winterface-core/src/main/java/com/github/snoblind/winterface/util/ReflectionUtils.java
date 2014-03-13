package com.github.snoblind.winterface.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static java.lang.String.format;
import static java.util.Collections.emptyMap;
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

	public static Map<String, PropertyDescriptor> propertyDescriptorsByName(final Object bean) {
		return propertyDescriptorsByName(bean.getClass());
	}

	public static Map<String, PropertyDescriptor> propertyDescriptorsByName(final Class<?> beanClass) {
		final PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(beanClass);
		if (descriptors.length == 0) {
			return emptyMap();
		}
		final Map<String, PropertyDescriptor> map = new TreeMap<String, PropertyDescriptor>();
		for (PropertyDescriptor descriptor: descriptors) {
			final String name = descriptor.getName();
			if (!"class".equals(name)) {
				map.put(name, descriptor);
			}
		}
		return Collections.unmodifiableMap(map);
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
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException x) {
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
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException x) {
			LOGGER.error(String.format("An exception was thrown while attempting to set property %s to value %s of an instance of %s.", name, value, bean.getClass()), x);
			throw new RuntimeException(x);
		}
	}
}