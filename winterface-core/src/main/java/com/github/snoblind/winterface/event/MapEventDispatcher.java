package com.github.snoblind.winterface.event;

import java.util.HashSet;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import com.github.snoblind.winterface.Event;
import com.github.snoblind.winterface.EventListener;
import com.github.snoblind.winterface.EventTarget;
import static org.apache.commons.lang.Validate.notNull;
import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;
import static java.lang.String.format;

public class MapEventDispatcher extends AbstractEventDispatcher {
	
	private final Map<Key, Collection<EventListener>> map;

	public MapEventDispatcher(final Map<Key, Collection<EventListener>> map) {
		notNull(map);
		this.map = map;
	}

	public MapEventDispatcher() {
		this(new HashMap<Key, Collection<EventListener>>());
	}
	
	public Event createEvent(String eventInterface) {
		if ("Event".equals(eventInterface)) {
			return new EventImpl();
		}
		throw new IllegalArgumentException(String.valueOf(eventInterface));
	}

	public void addEventListener(EventTarget target, String type, EventListener listener, boolean useCapture) {
		Key key = new Key(target, type, useCapture);
		Collection<EventListener> listeners = map.get(key);
		if (listeners == null) {
			map.put(key, listeners = new HashSet<EventListener>());
		}
		listeners.add(listener);
	}

	public void removeEventListener(EventTarget target, String type, EventListener listener, boolean useCapture) {
		Key key = new Key(target, type, useCapture);
		Collection<EventListener> list = map.get(key);
		if (list != null) {
			list.remove(listener);
		}
	}

	protected Collection<EventListener> getEventListeners(EventTarget target, String type, boolean useCapture) {
		Key key = new Key(target, type, useCapture);
		Collection<EventListener> listeners = map.get(key);
		if (listeners == null) {
			listeners = Collections.emptyList();
		}
		else {
			listeners = Collections.unmodifiableCollection(listeners);
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(format("%,d event listeners registered for target %s.", listeners.size(), target));
		}
		return listeners;
	}

	private static class Key {

		@SuppressWarnings("unused") private final EventTarget target;
		@SuppressWarnings("unused") private final String type;
		@SuppressWarnings("unused") private final boolean useCapture;

		private Key(EventTarget target, String type, boolean useCapture) {
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
