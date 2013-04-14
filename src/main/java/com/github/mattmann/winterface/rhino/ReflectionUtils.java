package com.github.mattmann.winterface.rhino;

import java.lang.reflect.Method;
import static java.lang.String.format;
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
}