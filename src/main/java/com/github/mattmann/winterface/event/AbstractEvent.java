package com.github.mattmann.winterface.event;

import com.github.mattmann.winterface.Event;
import com.github.mattmann.winterface.EventTarget;

public abstract class AbstractEvent implements Event {

	public void initEvent(CharSequence eventType, boolean canBubble, boolean cancelableArg) {
		setType(eventType);
		setBubbles(canBubble);
		setCancelable(cancelableArg);
	}

	private CharSequence type;

	public CharSequence getType() {
		return type;
	}

	protected void setType(CharSequence type) {
		this.type = type;
	}

	private boolean bubbles;

	public boolean isBubbles() {
		return bubbles;
	}

	protected void setBubbles(boolean bubbles) {
		this.bubbles = bubbles;
	}

	private boolean cancelable;

	public boolean isCancelable() {
		return cancelable;
	}

	protected void setCancelable(boolean cancelable) {
		this.cancelable = cancelable;
	}

	private boolean trusted;

	public boolean isTrusted() {
		return trusted;
	}

	protected EventTarget target;

	public EventTarget getTarget() {
		return target;
	}

	private EventTarget currentTarget;

	public EventTarget getCurrentTarget() {
		return currentTarget;
	}

	protected void setCurrentTarget(EventTarget currentTarget) {
		this.currentTarget = currentTarget;
	}
	
	private int eventPhase;

	public int getEventPhase() {
		return eventPhase;
	}

	protected void setEventPhase(int eventPhase) {
		this.eventPhase = eventPhase;
	}
	
	private long timeStamp;

	public long getTimeStamp() {
		return timeStamp;
	}
	
	protected void setTimesStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	private boolean defaultPrevented;

	public boolean isDefaultPrevented() {
		return defaultPrevented;
	}

	protected void setDefaultPrevented(boolean defaultPrevented) {
		this.defaultPrevented = defaultPrevented;
	}

	public void preventDefault() {
		throw new UnsupportedOperationException();
	}

	public void stopImmediatePropagation() {
		throw new UnsupportedOperationException();
	}

	public void stopPropagation() {
		throw new UnsupportedOperationException();
	}
}
