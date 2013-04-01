package com.github.mattmann.winterface;

public interface HTMLImageElement extends HTMLElement {
	
	boolean isComplete();

	CharSequence getLowsrc();
	void setLowsrc(CharSequence lowsrc);

	CharSequence getName();
	void setName(CharSequence name);

	CharSequence getAlign();
	void setAlign(CharSequence align);

	CharSequence getAlt();
	void setAlt(CharSequence alt);

	CharSequence getBorder();
	void setBorder(CharSequence border);

	CharSequence getHeight();
	void setHeight(CharSequence height);

	CharSequence getHspace();
	void setHspace(CharSequence hspace);

	boolean getIsMap();
	void setIsMap(boolean isMap);

	CharSequence getLongDesc();
	void setLongDesc(CharSequence longDesc);

	CharSequence getSrc();
	void setSrc(CharSequence src);

	CharSequence getUseMap();
	void setUseMap(CharSequence useMap);

	CharSequence getVspace();
	void setVspace(CharSequence vspace);

	CharSequence getWidth();
	void setWidth(CharSequence width);
	
	EventListener getOnabort();
	void setOnabort(EventListener handler);
	
	OnErrorEventHandler getOnerror();
	void setOnerror(OnErrorEventHandler handler);
	
	EventListener getOnload();
	void setOnload(EventListener handler);
}
