package com.github.snoblind.winterface.event;

import com.github.snoblind.winterface.ExtendedHTMLDocument;
import com.github.snoblind.winterface.ExtendedHTMLElement;
import com.github.snoblind.winterface.Window;
import com.github.snoblind.winterface.XMLHttpRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import static org.w3c.dom.events.Event.AT_TARGET;
import static org.w3c.dom.events.Event.BUBBLING_PHASE;
import static org.w3c.dom.events.Event.CAPTURING_PHASE;
import static java.lang.String.format;
import static org.apache.commons.lang.Validate.isTrue;
import static org.apache.commons.lang.Validate.notNull;

public abstract class AbstractEventDispatcher implements EventDispatcher {
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractEventDispatcher.class);

	public abstract void addEventListener(EventTarget target, String type, EventListener listener, boolean useCapture);

	public abstract void removeEventListener(EventTarget target, String type, EventListener listener, boolean useCapture);

	public abstract ExtendedEvent createEvent(String eventInterface);
	
	protected abstract Collection<EventListener> getEventListeners(EventTarget target, String type, boolean useCapture);

	protected Collection<EventListener> getEventListeners(Event event) {
		return getEventListeners(event.getCurrentTarget(), event.getType(), CAPTURING_PHASE == event.getEventPhase());
	}

	public boolean dispatchEvent(Event event) {
		if (event instanceof ExtendedEvent) {
			return dispatchEvent((ExtendedEvent) event);
		}
		throw new IllegalArgumentException(String.valueOf(event));
	}
	
	protected boolean dispatchEvent(ExtendedEvent event) {
		notNull(event.getTarget());
		LOGGER.debug("dispatchEvent({})", event);
		Collection<EventTarget> propagationPath = getPropagationPath(event);
		if (propagate((DefaultEvent)event, CAPTURING_PHASE, propagationPath)) {
			if (doTargetPhase((DefaultEvent)event)) {
				List<EventTarget> reverseProparationPath = new ArrayList<EventTarget>(propagationPath);
				Collections.reverse(reverseProparationPath);
				propagate((DefaultEvent)event, BUBBLING_PHASE, reverseProparationPath);
			}
		}
		return event.isDefaultPrevented();
	}

	protected Collection<EventTarget> getPropagationPath(Event event) {
		LinkedList<EventTarget> list = new LinkedList<EventTarget>();
		EventTarget target = event.getTarget();
		do {
			list.addFirst(target);
		}
		while ((target = next(target)) != null);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(format("Propagation path for \"%s\" event with target %s has %,d elements.", event.getType(), event.getTarget(), list.size()));
		}
		return list;
	}
	
	protected EventTarget next(EventTarget target) {
		notNull(target);
		if (target instanceof ExtendedHTMLElement) {
			return (EventTarget)((ExtendedHTMLElement)target).getParentNode();
		}
		if (target instanceof ExtendedHTMLDocument) {
			return ((ExtendedHTMLDocument)target).getDefaultView();
		}
		if (target instanceof Window) {
			return null;
		}
		if (target instanceof XMLHttpRequest) {
			return null;
		}
		throw new IllegalArgumentException(target.getClass().getName());
	}

	protected boolean dispatchEvent(ExtendedEvent event, Collection<EventListener> listeners) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(format("Current target is %s.", event.getCurrentTarget()));
		}
		for (EventListener listener: listeners) {
			listener.handleEvent(event);
			if (event.isDefaultPrevented()) {
				return false;
			}
		}
		return true;
	}

	protected boolean propagate(DefaultEvent event, short phase, Collection<EventTarget> targets) throws EventException {
		isTrue(phase == CAPTURING_PHASE || phase == BUBBLING_PHASE);
		for (EventTarget currentTarget: targets) {
			if (event.getTarget().equals(currentTarget)) {
				continue;
			}
			event.setEventPhase(phase);
			event.setCurrentTarget(currentTarget);
			Collection<EventListener> listeners = getEventListeners(event);
			if (!dispatchEvent(event, listeners)) {
				return false;
			}
		}
		return true;
	}
	
	protected boolean doTargetPhase(DefaultEvent event) throws EventException {
		event.setEventPhase(AT_TARGET);
		event.setCurrentTarget(event.getTarget());
		Collection<EventListener> listeners = getEventListeners(event);
		return dispatchEvent(event, listeners);
	}
}
