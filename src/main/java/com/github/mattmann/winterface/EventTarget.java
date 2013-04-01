package com.github.mattmann.winterface;

/*
 * http://www.w3.org/TR/DOM-Level-3-Events/#event-flow 
 */
public interface EventTarget {
	void addEventListener(CharSequence type, EventListener listener, boolean useCapture);
	void removeEventListener(CharSequence type, EventListener listener, boolean useCapture);
	boolean dispatchEvent(Event event) throws EventException;
}
