package com.github.snoblind.winterface.event;

import com.github.snoblind.winterface.Event;
import com.github.snoblind.winterface.EventException;
import com.github.snoblind.winterface.EventListener;
import com.github.snoblind.winterface.EventTarget;

public interface EventDispatcher {
	Event createEvent(String eventInterface);
	boolean dispatchEvent(Event event) throws EventException;
	void addEventListener(EventTarget target, String type, EventListener listener, boolean useCapture);
	void removeEventListener(EventTarget target, String type, EventListener listener, boolean useCapture);
}
