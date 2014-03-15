package com.github.snoblind.winterface.util;

import com.github.snoblind.winterface.Event;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;
import org.junit.Test;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ReflectionUtilsTest {

	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

	@Test
	public void getPropertyValue() throws NoSuchMethodException {
		final Interface bean = mock(Interface.class);
		final Method readMethod = Interface.class.getMethod("getURL");
		final PropertyDescriptor descriptor = mock(PropertyDescriptor.class);
		doReturn("URL").when(descriptor).getName();
		doReturn(readMethod).when(descriptor).getReadMethod();
		doReturn(ALPHABET).when(bean).getURL();
		assertEquals(ALPHABET, ReflectionUtils.getPropertyValue(bean, descriptor));
	}
	
	@Test
	public void setPropertyValue() throws NoSuchMethodException {
		final Interface bean = mock(Interface.class);
		final Method writeMethod = Interface.class.getMethod("setB", int.class);
		final PropertyDescriptor descriptor = mock(PropertyDescriptor.class);
		doReturn("b").when(descriptor).getName();
		doReturn(int.class).when(descriptor).getPropertyType();
		doReturn(writeMethod).when(descriptor).getWriteMethod();
		ReflectionUtils.setPropertyValue(bean, descriptor, 666);
		verify(bean).setB(666);
	}
	
	@Test
	public void findMethod_NoSuchMethod() {
		final Class<?> targetClass = Event.class;
		final String name = "methodDoesNotExist";
		try {
			ReflectionUtils.findMethod(targetClass, name);
			fail();
		}
		catch (NoSuchMethodException x) {
			assertEquals(format("Class %s has no method named \"%s\".", targetClass.getName(), name), x.getMessage());
		}
	}
	
	@Test
	public void findMethod_Event_initEvent() throws NoSuchMethodException {
		final Method method1 = Event.class.getMethod("initEvent", String.class, boolean.class, boolean.class);
		final Method method2 = ReflectionUtils.findMethod(Event.class, "initEvent");
		assertEquals(method1, method2);
	}
	
	@Test
	public void propertyDescriptorsByName() {
		final Map<String, PropertyDescriptor> map = ReflectionUtils.propertyDescriptorsByName(Interface.class);
		assertEquals(5, map.size());
		
		PropertyDescriptor descriptor;
		descriptor = map.get("a");
		assertNotNull(descriptor);
		assertNotNull(descriptor.getReadMethod());
		assertNotNull(descriptor.getWriteMethod());
		
		descriptor = map.get("b");
		assertNotNull(descriptor);
		assertNotNull(descriptor.getReadMethod());
		assertNotNull(descriptor.getWriteMethod());

		descriptor = map.get("URL");
		assertNotNull(descriptor);
		assertNotNull(descriptor.getReadMethod());
		assertNull(descriptor.getWriteMethod());

		descriptor = map.get("value");
		assertNotNull(descriptor);
		assertNull(descriptor.getReadMethod());
		assertNotNull(descriptor.getWriteMethod());

		descriptor = map.get("short");
		assertNotNull(descriptor);
		assertNotNull(descriptor.getReadMethod());
		assertNull(descriptor.getWriteMethod());
	}

	public static interface Interface {
		boolean isA();
		void setA(boolean a);

		int getB();
		void setB(int b);

		short get();
		
		float is();

		byte getC(int i);
		void setC(int i, byte c);
		
		String getURL();
		
		void setValue(String value);
		
		short getshort();
	}
}
