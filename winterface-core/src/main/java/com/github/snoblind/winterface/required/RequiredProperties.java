package com.github.snoblind.winterface.required;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.DirectFieldAccessor;
import org.springframework.beans.factory.annotation.Required;
import static java.lang.String.format;

public class RequiredProperties {

	public static void assertRequiredProperties(Object bean) {
		final DirectFieldAccessor accessor = new DirectFieldAccessor(bean);
		final PropertyDescriptor[] descriptors = BeanUtils.getPropertyDescriptors(bean.getClass());
		for (PropertyDescriptor descriptor: descriptors) {
			if (isRequired(descriptor) && accessor.getPropertyValue(descriptor.getName()) == null) {
				throw new IllegalArgumentException(format("Property \"%s\" is required for instances of %s.", descriptor.getName(), bean.getClass()));
			}
		}
	}

	private static boolean isRequired(final PropertyDescriptor descriptor) {
		return !descriptor.getName().equals("class")
				&& (isRequired(descriptor.getWriteMethod())
						|| isRequired(descriptor.getReadMethod()));
	}

	private static boolean isRequired(final Method method) {
		return !(method == null || method.getAnnotation(Required.class) == null);
	}
}
