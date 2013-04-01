package com.github.mattmann.winterface;

public interface GlobalEventHandlers {

	EventListener getOnabort();
	void setOnabort(EventListener handler);

	EventListener getOnblur();
	void setOnblur(EventListener handler);

	OnErrorEventHandler getOnerror();
	void setOnerror(OnErrorEventHandler handler);

	EventListener getOnfocus();
	void setOnfocus(EventListener handler);

	EventListener getOncancel();
	void setOncancel(EventListener handler);

	EventListener getOncanplay();
	void setOncanplay(EventListener handler);

	EventListener getOncanplaythrough();
	void setOncanplaythrough(EventListener handler);

	EventListener getOnchange();
	void setOnchange(EventListener handler);

	EventListener getOnclick();
	void setOnclick(EventListener handler);

	EventListener getOnclose();
	void setOnclose(EventListener handler);

	EventListener getOncontextmenu();
	void setOncontextmenu(EventListener handler);

	EventListener getOncuechange();
	void setOncuechange(EventListener handler);

	EventListener getOndblclick();
	void setOndblclick(EventListener handler);

	EventListener getOndrag();
	void setOndrag(EventListener handler);

	EventListener getOndragend();
	void setOndragend(EventListener handler);

	EventListener getOndragenter();
	void setOndragenter(EventListener handler);

	EventListener getOndragleave();
	void setOndragleave(EventListener handler);

	EventListener getOndragover();
	void setOndragover(EventListener handler);

	EventListener getOndragstart();
	void setOndragstart(EventListener handler);

	EventListener getOndrop();
	void setOndrop(EventListener handler);

	EventListener getOndurationchange();
	void setOndurationchange(EventListener handler);

	EventListener getOnemptied();
	void setOnemptied(EventListener handler);

	EventListener getOnended();
	void setOnended(EventListener handler);

	EventListener getOninput();
	void setOninput(EventListener handler);

	EventListener getOninvalid();
	void setOninvalid(EventListener handler);

	EventListener getOnkeydown();
	void setOnkeydown(EventListener handler);

	EventListener getOnkeypress();
	void setOnkeypress(EventListener handler);

	EventListener getOnkeyup();
	void setOnkeyup(EventListener handler);

	EventListener getOnload();
	void setOnload(EventListener handler);

	EventListener getOnloadeddata();
	void setOnloadeddata(EventListener handler);

	EventListener getOnloadedmetadata();
	void setOnloadedmetadata(EventListener handler);

	EventListener getOnloadstart();
	void setOnloadstart(EventListener handler);

	EventListener getOnmousedown();
	void setOnmousedown(EventListener handler);

	EventListener getOnmouseenter();
	void setOnmouseenter(EventListener handler);

	EventListener getOnmouseleave();
	void setOnmouseleave(EventListener handler);

	EventListener getOnmousemove();
	void setOnmousemove(EventListener handler);

	EventListener getOnmouseout();
	void setOnmouseout(EventListener handler);

	EventListener getOnmouseover();
	void setOnmouseover(EventListener handler);

	EventListener getOnmouseup();
	void setOnmouseup(EventListener handler);

	EventListener getOnmousewheel();
	void setOnmousewheel(EventListener handler);

	EventListener getOnpause();
	void setOnpause(EventListener handler);

	EventListener getOnplay();
	void setOnplay(EventListener handler);

	EventListener getOnplaying();
	void setOnplaying(EventListener handler);

	EventListener getOnprogress();
	void setOnprogress(EventListener handler);

	EventListener getOnratechange();
	void setOnratechange(EventListener handler);

	EventListener getOnreset();
	void setOnreset(EventListener handler);

	EventListener getOnscroll();
	void setOnscroll(EventListener handler);

	EventListener getOnseeked();
	void setOnseeked(EventListener handler);

	EventListener getOnseeking();
	void setOnseeking(EventListener handler);

	EventListener getOnselect();
	void setOnselect(EventListener handler);

	EventListener getOnshow();
	void setOnshow(EventListener handler);

	EventListener getOnsort();
	void setOnsort(EventListener handler);

	EventListener getOnstalled();
	void setOnstalled(EventListener handler);

	EventListener getOnsubmit();
	void setOnsubmit(EventListener handler);

	EventListener getOnsuspend();
	void setOnsuspend(EventListener handler);

	EventListener getOntimeupdate();
	void setOntimeupdate(EventListener handler);

	EventListener getOnvolumechange();
	void setOnvolumechange(EventListener handler);

	EventListener getOnwaiting();
	void setOnwaiting(EventListener handler);
}
