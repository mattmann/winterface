package com.github.snoblind.winterface.event;

import org.w3c.dom.events.EventTarget;
import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang.builder.ToStringStyle.MULTI_LINE_STYLE;

public class DefaultEvent implements ExtendedEvent {

	public void initEvent(String eventType, boolean canBubble, boolean cancelableArg) {
		setType(eventType);
		setBubbles(canBubble);
		setCancelable(cancelableArg);
	}

	private String type;

	public String getType() {
		return type;
	}

	protected void setType(String type) {
		this.type = type;
	}

	private boolean bubbles;

	public boolean getBubbles() {
		return bubbles;
	}

	protected void setBubbles(boolean bubbles) {
		this.bubbles = bubbles;
	}

	private boolean cancelable;

	public boolean getCancelable() {
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

	public void setTarget(EventTarget target) {
		this.target = target;
	}

	private EventTarget currentTarget;

	public EventTarget getCurrentTarget() {
		return currentTarget;
	}

	protected void setCurrentTarget(EventTarget currentTarget) {
		this.currentTarget = currentTarget;
	}
	
	private short eventPhase;

	public short getEventPhase() {
		return eventPhase;
	}

	protected void setEventPhase(short eventPhase) {
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

	public String toString() {
		return reflectionToString(this, MULTI_LINE_STYLE);
	}
}
