package com.github.mattmann.winterface;

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
