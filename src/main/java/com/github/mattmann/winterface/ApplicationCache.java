package com.github.mattmann.winterface;

public interface ApplicationCache {

	public static final short UNCACHED = 0;
	public static final short IDLE = 1;
	public static final short CHECKING = 2;
	public static final short DOWNLOADING = 3;
	public static final short UPDATEREADY = 4;
	public static final short OBSOLETE = 5;
	
	short getStatus();

	void update();
	void abort();
	void swapCache();

	EventListener getOnchecking();
	void setOnchecking(EventListener handler);

	EventListener getOnerror();
	void setOnerror(EventListener handler);

	EventListener getOnnoupdate();
	void setOnnoupdate(EventListener handler);

	EventListener getOndownloading();
	void setOndownloading(EventListener handler);

	EventListener getOnprogress();
	void setOnprogress(EventListener handler);

	EventListener getOnupdateready();
	void setOnupdateready(EventListener handler);

	EventListener getOncached();
	void setOncached(EventListener handler);

	EventListener getOnobsolete();
	void setOnobsolete(EventListener handler);
}
