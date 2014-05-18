package com.github.snoblind.winterface.event;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventTarget;

public interface ExtendedEvent extends Event {

	void setTarget(EventTarget target);

	boolean isDefaultPrevented();
}
