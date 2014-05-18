package com.github.snoblind.winterface;

import org.w3c.dom.events.EventListener;

public interface NodeEventHandlers {
	EventListener getOnblur();
	void setOnblur(EventListener onblur);
	
	OnErrorEventHandler getOnerror();
	void setOnerror(OnErrorEventHandler onerror);

	EventListener getOnfocus();
	void setOnfocus(EventListener onfocus);

	EventListener getOnload();
	void setOnload(EventListener onload);

	EventListener getOnscroll();
	void setOnscroll(EventListener onscroll);
}
