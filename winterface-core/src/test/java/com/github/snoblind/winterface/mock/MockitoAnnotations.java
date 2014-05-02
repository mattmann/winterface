package com.github.snoblind.winterface.mock;

import java.lang.reflect.Field;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.stubbing.Answer;
import org.springframework.beans.DirectFieldAccessor;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.RETURNS_DEFAULTS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class MockitoAnnotations {

	private MockitoAnnotations() {
	}

	public static void initMocks(Object object, Answer<?> defaultAnswer) {
		final DirectFieldAccessor accessor = new DirectFieldAccessor(object);
		for (Field field: object.getClass().getDeclaredFields()) {
			if (field.getAnnotation(Mock.class) != null) {
				accessor.setPropertyValue(field.getName(), mock(field.getType(), defaultAnswer));
			}
			else if (field.getAnnotation(Spy.class) != null) {
				final Object instance = accessor.getPropertyValue(field.getName());
				if (instance == null) {
					accessor.setPropertyValue(field.getName(), mock(field.getType(), CALLS_REAL_METHODS));
				}
				else {
					accessor.setPropertyValue(field.getName(), spy(instance));
				}
			}
		}
	}

	public static void initMocks(Object object) {
		initMocks(object, RETURNS_DEFAULTS);
	}
}