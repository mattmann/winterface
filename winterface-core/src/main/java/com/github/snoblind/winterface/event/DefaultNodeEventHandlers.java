package com.github.snoblind.winterface.event;

import com.github.snoblind.winterface.NodeEventHandlers;
import com.github.snoblind.winterface.OnErrorEventHandler;
import org.w3c.dom.events.EventListener;

public class DefaultNodeEventHandlers implements NodeEventHandlers {
	
	private EventListener onblur;
	private OnErrorEventHandler onerror;
	private EventListener onfocus;
	private EventListener onload;
	private EventListener onscroll;

	public EventListener getOnblur() {
		return onblur;
	}

	public void setOnblur(EventListener onblur) {
		this.onblur = onblur;
	}

	public OnErrorEventHandler getOnerror() {
		return onerror;
	}

	public void setOnerror(OnErrorEventHandler onerror) {
		this.onerror = onerror;
	}

	public EventListener getOnfocus() {
		return onfocus;
	}

	public void setOnfocus(EventListener onfocus) {
		this.onfocus = onfocus;
	}

	public EventListener getOnload() {
		return onload;
	}

	public void setOnload(EventListener onload) {
		this.onload = onload;
	}

	public EventListener getOnscroll() {
		return onscroll;
	}

	public void setOnscroll(EventListener onscroll) {
		this.onscroll = onscroll;
	}
}
