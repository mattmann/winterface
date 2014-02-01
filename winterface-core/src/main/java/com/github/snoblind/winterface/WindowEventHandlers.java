package com.github.snoblind.winterface;

public interface WindowEventHandlers {

	EventListener getOnafterprint();
	void setOnafterprint(EventListener handler);

	EventListener getOnbeforeprint();
	void setOnbeforeprint(EventListener handler);

	EventListener getOnbeforeunload();
	void setOnbeforeunload(EventListener handler);

	EventListener getOnfullscreenchange();
	void setOnfullscreenchange(EventListener handler);

	EventListener getOnfullscreenerror();
	void setOnfullscreenerror(EventListener handler);

	EventListener getOnhashchange();
	void setOnhashchange(EventListener handler);

	EventListener getOnmessage();
	void setOnmessage(EventListener handler);

	EventListener getOnoffline();
	void setOnoffline(EventListener handler);

	EventListener getOnonline();
	void setOnonline(EventListener handler);

	EventListener getOnpagehide();
	void setOnpagehide(EventListener handler);

	EventListener getOnpageshow();
	void setOnpageshow(EventListener handler);

	EventListener getOnpopstate();
	void setOnpopstate(EventListener handler);

	EventListener getOnresize();
	void setOnresize(EventListener handler);

	EventListener getOnstorage();
	void setOnstorage(EventListener handler);

	EventListener getOnunload();
	void setOnunload(EventListener handler);
}
