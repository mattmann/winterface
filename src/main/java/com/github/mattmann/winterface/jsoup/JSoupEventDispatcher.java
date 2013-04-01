package com.github.mattmann.winterface.jsoup;

import java.util.HashSet;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

import com.github.mattmann.winterface.Event;
import com.github.mattmann.winterface.EventListener;
import com.github.mattmann.winterface.EventTarget;
import com.github.mattmann.winterface.event.AbstractEventDispatcher;
//import org.jsoup.nodes.Node;
//import static org.apache.commons.lang.Validate.isTrue;
import static org.apache.commons.lang.Validate.notNull;
import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

public class JSoupEventDispatcher extends AbstractEventDispatcher {
	
	private Map<Key, Collection<EventListener>> map = new HashMap<Key, Collection<EventListener>>();

	public Event createEvent(CharSequence eventInterface) {
		if ("Event".equals(eventInterface)) {
			return new JSoupEvent();
		}
		throw new IllegalArgumentException(String.valueOf(eventInterface));
	}

	public void addEventListener(EventTarget target, CharSequence type, EventListener listener, boolean useCapture) {
//		isTrue(target instanceof JSoupNode);
//		Key key = new Key(((JSoupNode<?>)target).node, type, useCapture);
		Key key = new Key(target, type, useCapture);
		Collection<EventListener> listeners = map.get(key);
		if (listeners == null) {
			map.put(key, listeners = new HashSet<EventListener>());
		}
		listeners.add(listener);
	}

	public void removeEventListener(EventTarget target, CharSequence type, EventListener listener, boolean useCapture) {
		Key key = new Key(target, type, useCapture);
		Collection<EventListener> list = map.get(key);
		if (list != null) {
			list.remove(listener);
		}
	}

	protected Collection<EventListener> getEventListeners(EventTarget target, CharSequence type, boolean useCapture) {
		Key key = new Key(target, type, useCapture);
		Collection<EventListener> listeners = map.get(key);
		if (listeners == null) {
			listeners = Collections.emptyList();
		}
		else {
			listeners = Collections.unmodifiableCollection(listeners);
		}
		System.out.printf("%,d event listeners registered for target %s.\n", listeners.size(), target);
		return listeners;
	}

	@SuppressWarnings("unused")
	private static class Key {

		private final EventTarget target;
		private final CharSequence type;
		private final boolean useCapture;

		private Key(EventTarget target, CharSequence type, boolean useCapture) {
			notNull(this.target = target);
			notNull(this.type = type);
			this.useCapture = useCapture;
		}

		public boolean equals(Object object) {
			return reflectionEquals(this, object);
		}

		public int hashCode() {
			return reflectionHashCode(this);
		}
	}
}
