package com.github.mattmann.winterface.event;

import com.github.mattmann.winterface.EventListener;
import com.github.mattmann.winterface.GlobalEventHandlers;

public abstract class AbstractGlobalEventHandlers extends AbstractNodeEventHandlers implements GlobalEventHandlers {

	private EventListener onabort;
	private EventListener oncancel;
	private EventListener oncanplay;
	private EventListener oncanplaythrough;
	private EventListener onchange;
	private EventListener onclick;
	private EventListener onclose;
	private EventListener oncontextmenu;
	private EventListener oncuechange;
	private EventListener ondblclick;
	private EventListener ondrag;
	private EventListener ondragend;
	private EventListener ondragenter;
	private EventListener ondragleave;
	private EventListener ondragover;
	private EventListener ondragstart;
	private EventListener ondrop;
	private EventListener ondurationchange;
	private EventListener onemptied;
	private EventListener onended;
	private EventListener oninput;
	private EventListener oninvalid;
	private EventListener onkeydown;
	private EventListener onkeypress;
	private EventListener onkeyup;
	private EventListener onloadeddata;
	private EventListener onloadedmetadata;
	private EventListener onloadstart;
	private EventListener onmousedown;
	private EventListener onmouseenter;
	private EventListener onmouseleave;
	private EventListener onmousemove;
	private EventListener onmouseout;
	private EventListener onmouseover;
	private EventListener onmouseup;
	private EventListener onmousewheel;
	private EventListener onpause;
	private EventListener onplay;
	private EventListener onplaying;
	private EventListener onprogress;
	private EventListener onratechange;
	private EventListener onreset;
	private EventListener onseeked;
	private EventListener onseeking;
	private EventListener onselect;
	private EventListener onshow;
	private EventListener onsort;
	private EventListener onstalled;
	private EventListener onsubmit;
	private EventListener onsuspend;
	private EventListener ontimeupdate;
	private EventListener onvolumechange;
	private EventListener onwaiting;

	public EventListener getOnabort() {
		return onabort;
	}

	public void setOnabort(EventListener onabort) {
		this.onabort = onabort;
	}

	public EventListener getOncancel() {
		return oncancel;
	}

	public void setOncancel(EventListener oncancel) {
		this.oncancel = oncancel;
	}

	public EventListener getOncanplay() {
		return oncanplay;
	}

	public void setOncanplay(EventListener oncanplay) {
		this.oncanplay = oncanplay;
	}

	public EventListener getOncanplaythrough() {
		return oncanplaythrough;
	}

	public void setOncanplaythrough(EventListener oncanplaythrough) {
		this.oncanplaythrough = oncanplaythrough;
	}

	public EventListener getOnchange() {
		return onchange;
	}

	public void setOnchange(EventListener onchange) {
		this.onchange = onchange;
	}

	public EventListener getOnclick() {
		return onclick;
	}

	public void setOnclick(EventListener onclick) {
		this.onclick = onclick;
	}

	public EventListener getOnclose() {
		return onclose;
	}

	public void setOnclose(EventListener onclose) {
		this.onclose = onclose;
	}

	public EventListener getOncontextmenu() {
		return oncontextmenu;
	}

	public void setOncontextmenu(EventListener oncontextmenu) {
		this.oncontextmenu = oncontextmenu;
	}

	public EventListener getOncuechange() {
		return oncuechange;
	}

	public void setOncuechange(EventListener oncuechange) {
		this.oncuechange = oncuechange;
	}

	public EventListener getOndblclick() {
		return ondblclick;
	}

	public void setOndblclick(EventListener ondblclick) {
		this.ondblclick = ondblclick;
	}

	public EventListener getOndrag() {
		return ondrag;
	}

	public void setOndrag(EventListener ondrag) {
		this.ondrag = ondrag;
	}

	public EventListener getOndragend() {
		return ondragend;
	}

	public void setOndragend(EventListener ondragend) {
		this.ondragend = ondragend;
	}

	public EventListener getOndragenter() {
		return ondragenter;
	}

	public void setOndragenter(EventListener ondragenter) {
		this.ondragenter = ondragenter;
	}

	public EventListener getOndragleave() {
		return ondragleave;
	}

	public void setOndragleave(EventListener ondragleave) {
		this.ondragleave = ondragleave;
	}

	public EventListener getOndragover() {
		return ondragover;
	}

	public void setOndragover(EventListener ondragover) {
		this.ondragover = ondragover;
	}

	public EventListener getOndragstart() {
		return ondragstart;
	}

	public void setOndragstart(EventListener ondragstart) {
		this.ondragstart = ondragstart;
	}

	public EventListener getOndrop() {
		return ondrop;
	}

	public void setOndrop(EventListener ondrop) {
		this.ondrop = ondrop;
	}

	public EventListener getOndurationchange() {
		return ondurationchange;
	}

	public void setOndurationchange(EventListener ondurationchange) {
		this.ondurationchange = ondurationchange;
	}

	public EventListener getOnemptied() {
		return onemptied;
	}

	public void setOnemptied(EventListener onemptied) {
		this.onemptied = onemptied;
	}

	public EventListener getOnended() {
		return onended;
	}

	public void setOnended(EventListener onended) {
		this.onended = onended;
	}

	public EventListener getOninput() {
		return oninput;
	}

	public void setOninput(EventListener oninput) {
		this.oninput = oninput;
	}

	public EventListener getOninvalid() {
		return oninvalid;
	}

	public void setOninvalid(EventListener oninvalid) {
		this.oninvalid = oninvalid;
	}

	public EventListener getOnkeydown() {
		return onkeydown;
	}

	public void setOnkeydown(EventListener onkeydown) {
		this.onkeydown = onkeydown;
	}

	public EventListener getOnkeypress() {
		return onkeypress;
	}

	public void setOnkeypress(EventListener onkeypress) {
		this.onkeypress = onkeypress;
	}

	public EventListener getOnkeyup() {
		return onkeyup;
	}

	public void setOnkeyup(EventListener onkeyup) {
		this.onkeyup = onkeyup;
	}

	public EventListener getOnloadeddata() {
		return onloadeddata;
	}

	public void setOnloadeddata(EventListener onloadeddata) {
		this.onloadeddata = onloadeddata;
	}

	public EventListener getOnloadedmetadata() {
		return onloadedmetadata;
	}

	public void setOnloadedmetadata(EventListener onloadedmetadata) {
		this.onloadedmetadata = onloadedmetadata;
	}

	public EventListener getOnloadstart() {
		return onloadstart;
	}

	public void setOnloadstart(EventListener onloadstart) {
		this.onloadstart = onloadstart;
	}

	public EventListener getOnmousedown() {
		return onmousedown;
	}

	public void setOnmousedown(EventListener onmousedown) {
		this.onmousedown = onmousedown;
	}

	public EventListener getOnmouseenter() {
		return onmouseenter;
	}

	public void setOnmouseenter(EventListener onmouseenter) {
		this.onmouseenter = onmouseenter;
	}

	public EventListener getOnmouseleave() {
		return onmouseleave;
	}

	public void setOnmouseleave(EventListener onmouseleave) {
		this.onmouseleave = onmouseleave;
	}

	public EventListener getOnmousemove() {
		return onmousemove;
	}

	public void setOnmousemove(EventListener onmousemove) {
		this.onmousemove = onmousemove;
	}

	public EventListener getOnmouseout() {
		return onmouseout;
	}

	public void setOnmouseout(EventListener onmouseout) {
		this.onmouseout = onmouseout;
	}

	public EventListener getOnmouseover() {
		return onmouseover;
	}

	public void setOnmouseover(EventListener onmouseover) {
		this.onmouseover = onmouseover;
	}

	public EventListener getOnmouseup() {
		return onmouseup;
	}

	public void setOnmouseup(EventListener onmouseup) {
		this.onmouseup = onmouseup;
	}

	public EventListener getOnmousewheel() {
		return onmousewheel;
	}

	public void setOnmousewheel(EventListener onmousewheel) {
		this.onmousewheel = onmousewheel;
	}

	public EventListener getOnpause() {
		return onpause;
	}

	public void setOnpause(EventListener onpause) {
		this.onpause = onpause;
	}

	public EventListener getOnplay() {
		return onplay;
	}

	public void setOnplay(EventListener onplay) {
		this.onplay = onplay;
	}

	public EventListener getOnplaying() {
		return onplaying;
	}

	public void setOnplaying(EventListener onplaying) {
		this.onplaying = onplaying;
	}

	public EventListener getOnprogress() {
		return onprogress;
	}

	public void setOnprogress(EventListener onprogress) {
		this.onprogress = onprogress;
	}

	public EventListener getOnratechange() {
		return onratechange;
	}

	public void setOnratechange(EventListener onratechange) {
		this.onratechange = onratechange;
	}

	public EventListener getOnreset() {
		return onreset;
	}

	public void setOnreset(EventListener onreset) {
		this.onreset = onreset;
	}

	public EventListener getOnseeked() {
		return onseeked;
	}

	public void setOnseeked(EventListener onseeked) {
		this.onseeked = onseeked;
	}

	public EventListener getOnseeking() {
		return onseeking;
	}

	public void setOnseeking(EventListener onseeking) {
		this.onseeking = onseeking;
	}

	public EventListener getOnselect() {
		return onselect;
	}

	public void setOnselect(EventListener onselect) {
		this.onselect = onselect;
	}

	public EventListener getOnshow() {
		return onshow;
	}

	public void setOnshow(EventListener onshow) {
		this.onshow = onshow;
	}

	public EventListener getOnsort() {
		return onsort;
	}

	public void setOnsort(EventListener onsort) {
		this.onsort = onsort;
	}

	public EventListener getOnstalled() {
		return onstalled;
	}

	public void setOnstalled(EventListener onstalled) {
		this.onstalled = onstalled;
	}

	public EventListener getOnsubmit() {
		return onsubmit;
	}

	public void setOnsubmit(EventListener onsubmit) {
		this.onsubmit = onsubmit;
	}

	public EventListener getOnsuspend() {
		return onsuspend;
	}

	public void setOnsuspend(EventListener onsuspend) {
		this.onsuspend = onsuspend;
	}

	public EventListener getOntimeupdate() {
		return ontimeupdate;
	}

	public void setOntimeupdate(EventListener ontimeupdate) {
		this.ontimeupdate = ontimeupdate;
	}

	public EventListener getOnvolumechange() {
		return onvolumechange;
	}

	public void setOnvolumechange(EventListener onvolumechange) {
		this.onvolumechange = onvolumechange;
	}

	public EventListener getOnwaiting() {
		return onwaiting;
	}

	public void setOnwaiting(EventListener onwaiting) {
		this.onwaiting = onwaiting;
	}
}