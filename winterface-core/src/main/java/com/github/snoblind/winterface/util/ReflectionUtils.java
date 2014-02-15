package com.github.snoblind.winterface.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.beanutils.PropertyUtils;
import static java.lang.String.format;
import static java.util.Collections.emptyMap;
import static org.apache.commons.lang.Validate.notNull;

public final class ReflectionUtils {

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
		final PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(bean);
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
}