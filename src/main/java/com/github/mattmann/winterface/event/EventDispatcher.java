package com.github.mattmann.winterface.event;

import com.github.mattmann.winterface.Event;
import com.github.mattmann.winterface.EventException;
import com.github.mattmann.winterface.EventListener;
import com.github.mattmann.winterface.EventTarget;

public interface EventDispatcher {
	Event createEvent(String eventInterface);
	boolean dispatchEvent(Event event) throws EventException;
	void addEventListener(EventTarget target, String type, EventListener listener, boolean useCapture);
	void removeEventListener(EventTarget target, String type, EventListener listener, boolean useCapture);
}
