package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.Event;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mozilla.javascript.Scriptable;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.MockitoAnnotations.initMocks;

public class ReflectingScriptableObjectTest {

	private ReflectingScriptableObject object;

	@Mock private Event event;
	@Mock private Scriptable scriptable;
	
	@Before
	public void setUp() {
		initMocks(this/*, ANSWER*/);
		object = new ReflectingScriptableObject(event, Event.class);
	}

	@Test
	public void getFunctionsByName() {
		final Iterator<String> iterator = object.getFunctionsByName().keySet().iterator();
		assertEquals("initEvent", iterator.next());
		assertEquals("preventDefault", iterator.next());
		assertEquals("stopImmediatePropagation", iterator.next());
		assertEquals("stopPropagation", iterator.next());
		assertFalse(iterator.hasNext());
	}
	
	@Test
	public void getPropertyDescriptorsByName() {
		final Iterator<String> iterator = object.getPropertyDescriptorsByName().keySet().iterator();
		assertEquals("bubbles", iterator.next());
		assertEquals("cancelable", iterator.next());
		assertEquals("currentTarget", iterator.next());
		assertEquals("defaultPrevented", iterator.next());
		assertEquals("eventPhase", iterator.next());
		assertEquals("target", iterator.next());
		assertEquals("timeStamp", iterator.next());
		assertEquals("trusted", iterator.next());
		assertEquals("type", iterator.next());
		assertFalse(iterator.hasNext());
	}
}
