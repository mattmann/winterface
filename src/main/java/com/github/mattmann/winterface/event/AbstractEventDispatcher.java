package com.github.mattmann.winterface.event;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Collection;

import com.github.mattmann.winterface.Event;
import com.github.mattmann.winterface.EventException;
import com.github.mattmann.winterface.EventListener;
import com.github.mattmann.winterface.EventTarget;
import com.github.mattmann.winterface.HTMLDocument;
import com.github.mattmann.winterface.HTMLElement;
import com.github.mattmann.winterface.Window;

import static com.github.mattmann.winterface.Event.AT_TARGET;
import static com.github.mattmann.winterface.Event.BUBBLING_PHASE;
import static com.github.mattmann.winterface.Event.CAPTURING_PHASE;
import static org.apache.commons.lang.Validate.isTrue;
import static org.apache.commons.lang.Validate.notNull;

public abstract class AbstractEventDispatcher implements EventDispatcher {

	public abstract void addEventListener(EventTarget target, CharSequence type, EventListener listener, boolean useCapture);

	public abstract void removeEventListener(EventTarget target, CharSequence type, EventListener listener, boolean useCapture);

	public abstract Event createEvent(CharSequence eventInterface);
	
	protected abstract Collection<EventListener> getEventListeners(EventTarget target, CharSequence type, boolean useCapture);

	protected Collection<EventListener> getEventListeners(Event event) {
		return getEventListeners(event.getCurrentTarget(), event.getType(), CAPTURING_PHASE == event.getEventPhase());
	}

	public boolean dispatchEvent(Event event) throws EventException {
		notNull(event.getTarget());
		Collection<EventTarget> propagationPath = getPropagationPath(event);
		if (propagate((AbstractEvent)event, CAPTURING_PHASE, propagationPath)) {
			if (doTargetPhase((AbstractEvent)event)) {
				List<EventTarget> reverseProparationPath = new ArrayList<EventTarget>(propagationPath);
				Collections.reverse(reverseProparationPath);
				propagate((AbstractEvent)event, BUBBLING_PHASE, reverseProparationPath);
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
		System.out.printf("Propagation path for \"%s\" event with target %s has %,d elements.\n", event.getType(), event.getTarget(), list.size());
		return list;
	}
	
	protected EventTarget next(EventTarget target) {
		notNull(target);
		if (target instanceof HTMLElement) {
			return (EventTarget)((HTMLElement)target).getParentNode();
		}
		if (target instanceof HTMLDocument) {
			return ((HTMLDocument)target).getDefaultView();
		}
		if (target instanceof Window) {
			return null;
		}
		throw new IllegalArgumentException(target.getClass().getName());
	}

	protected boolean dispatchEvent(Event event, Collection<EventListener> listeners) {
		System.out.printf("Current target is %s.\n", event.getCurrentTarget());
		for (EventListener listener: listeners) {
			listener.handleEvent(event);
			if (event.isDefaultPrevented()) {
				return false;
			}
		}
		return true;
	}

	protected boolean propagate(AbstractEvent event, int phase, Collection<EventTarget> targets) throws EventException {
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
	
	protected boolean doTargetPhase(AbstractEvent event) throws EventException {
		event.setEventPhase(AT_TARGET);
		event.setCurrentTarget(event.getTarget());
		Collection<EventListener> listeners = getEventListeners(event);
		return dispatchEvent(event, listeners);
	}
}
