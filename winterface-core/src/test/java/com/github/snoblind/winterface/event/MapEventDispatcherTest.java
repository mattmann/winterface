package com.github.snoblind.winterface.event;

import org.mockito.Mockito;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.github.snoblind.winterface.Event;
import com.github.snoblind.winterface.EventListener;
import com.github.snoblind.winterface.ExtendedHTMLElement;
import com.github.snoblind.winterface.event.EventImpl;
import com.github.snoblind.winterface.event.MapEventDispatcher;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MapEventDispatcherTest {
	
	private MapEventDispatcher eventDispatcher;

	@Before
	public void setUp() {
		eventDispatcher = new MapEventDispatcher();
	}
	
	@After
	public void tearDown() {
		eventDispatcher = null;
	}
	
	@Test
	public void test1() {
		final ExtendedHTMLElement target = Mockito.mock(ExtendedHTMLElement.class);
		final String type = "click";
		final EventListener listener = Mockito.mock(EventListener.class);
		final boolean useCapture = false;
		eventDispatcher.addEventListener(target, "click", listener, useCapture);
		assertEquals(1, eventDispatcher.getEventListeners(target, type, useCapture).size());
		EventImpl event = (EventImpl)eventDispatcher.createEvent("Event");
		event.initEvent(type, true, true);
		event.setTarget(target);
		eventDispatcher.dispatchEvent(event);
		Mockito.verify(listener).handleEvent(Mockito.any(Event.class));
		eventDispatcher.removeEventListener(target, type, listener, useCapture);
		assertTrue(eventDispatcher.getEventListeners(target, type, useCapture).isEmpty());
	}
}
