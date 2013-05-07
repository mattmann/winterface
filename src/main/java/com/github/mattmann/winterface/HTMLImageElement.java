package com.github.mattmann.winterface;

public interface HTMLImageElement extends HTMLElement {
	
	boolean isComplete();

	String getLowsrc();
	void setLowsrc(String lowsrc);

	String getName();
	void setName(String name);

	String getAlign();
	void setAlign(String align);

	String getAlt();
	void setAlt(String alt);

	String getBorder();
	void setBorder(String border);

	String getHeight();
	void setHeight(String height);

	String getHspace();
	void setHspace(String hspace);

	boolean getIsMap();
	void setIsMap(boolean isMap);

	String getLongDesc();
	void setLongDesc(String longDesc);

	String getSrc();
	void setSrc(String src);

	String getUseMap();
	void setUseMap(String useMap);

	String getVspace();
	void setVspace(String vspace);

	String getWidth();
	void setWidth(String width);
	
	EventListener getOnabort();
	void setOnabort(EventListener handler);
	
	OnErrorEventHandler getOnerror();
	void setOnerror(OnErrorEventHandler handler);
	
	EventListener getOnload();
	void setOnload(EventListener handler);
}
