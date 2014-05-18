package com.github.snoblind.winterface.event;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

public interface EventDispatcher {
	ExtendedEvent createEvent(String eventInterface);
	boolean dispatchEvent(Event event) throws EventException;
	void addEventListener(EventTarget target, String type, EventListener listener, boolean useCapture);
	void removeEventListener(EventTarget target, String type, EventListener listener, boolean useCapture);
	EventListener getEventListener(EventTarget target, String type, boolean useCapture);
}
