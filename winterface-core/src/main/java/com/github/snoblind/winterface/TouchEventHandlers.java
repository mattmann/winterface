package com.github.snoblind.winterface;

import org.w3c.dom.events.EventListener;

public interface TouchEventHandlers {

	EventListener getOntouchstart();
	void setOntouchstart(EventListener handler);

	EventListener getOntouchend();
	void setOntouchend(EventListener handler);

	EventListener getOntouchmove();
	void setOntouchmove(EventListener handler);

	EventListener getOntouchenter();
	void setOntouchenter(EventListener handler);

	EventListener getOntouchleave();
	void setOntouchleave(EventListener handler);

	EventListener getOntouchcancel();
	void setOntouchcancel(EventListener handler);
}