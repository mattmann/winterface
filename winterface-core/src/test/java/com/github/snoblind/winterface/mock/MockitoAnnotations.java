package com.github.snoblind.winterface.mock;

import java.lang.reflect.Field;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.DirectFieldAccessor;
import static org.mockito.Mockito.RETURNS_DEFAULTS;
import static org.mockito.Mockito.mock;

public class MockitoAnnotations {

	private MockitoAnnotations() {
	}

	public static void initMocks(Object object, Answer<?> defaultAnswer) {
		final DirectFieldAccessor accessor = new DirectFieldAccessor(object);
		for (Field field: object.getClass().getDeclaredFields()) {
			if (field.getAnnotation(Mock.class) != null) {
				accessor.setPropertyValue(field.getName(), mock(field.getType(), defaultAnswer));
			}
		}
	}

	public static void initMocks(Object object) {
		final DirectFieldAccessor accessor = new DirectFieldAccessor(object);
		for (Field field: object.getClass().getDeclaredFields()) {
			if (field.getAnnotation(Mock.class) != null) {
				accessor.setPropertyValue(field.getName(), mock(field.getType(), RETURNS_DEFAULTS));
			}
		}
	}
}
