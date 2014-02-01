package com.github.snoblind.winterface;

/*
 * http://www.w3.org/TR/DOM-Level-3-Events/#event-interfaces
 */
public interface Event {

	public static final int NONE = 0;
	public static final int AT_TARGET = 1;
	public static final int BUBBLING_PHASE = 2;
	public static final int CAPTURING_PHASE = 3;

	boolean isBubbles();
	boolean isCancelable();
	boolean isDefaultPrevented();
	boolean isTrusted();
	EventTarget getTarget();
	void setTarget(EventTarget target);
	EventTarget getCurrentTarget();
	int getEventPhase();
	long getTimeStamp();
	String getType();
	void initEvent(String eventType, boolean canBubble, boolean cancelableArg);
	void preventDefault();
	void stopImmediatePropagation();
	void stopPropagation();
}
