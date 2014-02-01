package com.github.snoblind.winterface;

/*
 * http://www.w3.org/TR/DOM-Level-3-Events/#event-flow 
 */
public interface EventTarget {
	void addEventListener(String type, EventListener listener, boolean useCapture);
	void removeEventListener(String type, EventListener listener, boolean useCapture);
	boolean dispatchEvent(Event event) throws EventException;
}
