package com.github.mattmann.winterface.event;

import com.github.mattmann.winterface.Event;
import com.github.mattmann.winterface.EventException;
import com.github.mattmann.winterface.EventListener;
import com.github.mattmann.winterface.EventTarget;

public interface EventDispatcher {
	Event createEvent(CharSequence eventInterface);
	boolean dispatchEvent(Event event) throws EventException;
	void addEventListener(EventTarget target, CharSequence type, EventListener listener, boolean useCapture);
	void removeEventListener(EventTarget target, CharSequence type, EventListener listener, boolean useCapture);
}
