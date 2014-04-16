package com.github.snoblind.winterface.abstracts;

import com.github.snoblind.winterface.ApplicationCache;
import com.github.snoblind.winterface.BarProp;
import com.github.snoblind.winterface.Event;
import com.github.snoblind.winterface.EventException;
import com.github.snoblind.winterface.EventListener;
import com.github.snoblind.winterface.External;
import com.github.snoblind.winterface.History;
import com.github.snoblind.winterface.Location;
import com.github.snoblind.winterface.Navigator;
import com.github.snoblind.winterface.OnErrorEventHandler;
import com.github.snoblind.winterface.Window;
import com.github.snoblind.winterface.WindowEnvironment;
import com.github.snoblind.winterface.event.EventDispatcher;
import java.io.IOException;
import java.util.AbstractMap;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public abstract class AbstractWindow extends AbstractMap<String, Object> implements Window {

	private BarProp locationbar;
	private BarProp menubar;
	private BarProp personalbar;
	private BarProp scrollbars;
	private BarProp statusbar;
	private BarProp toolbar;
 	private ApplicationCache applicationCache;
 	private EventDispatcher eventDispatcher;
 	private External external;
	private EventListener onabort;
	private EventListener onafterprint;
	private EventListener onbeforeprint;
	private EventListener onbeforeunload;
	private EventListener onblur;
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
	private EventListener onfocus;
	private EventListener onfullscreenchange;
	private EventListener onfullscreenerror;
	private EventListener onhashchange;
	private EventListener oninput;
	private EventListener oninvalid;
	private EventListener onkeydown;
	private EventListener onkeypress;
	private EventListener onkeyup;
	private EventListener onload;
	private EventListener onloadeddata;
	private EventListener onloadedmetadata;
	private EventListener onloadstart;
	private EventListener onmessage;
	private EventListener onmousedown;
	private EventListener onmouseenter;
	private EventListener onmouseleave;
	private EventListener onmousemove;
	private EventListener onmouseout;
	private EventListener onmouseover;
	private EventListener onmouseup;
	private EventListener onmousewheel;
	private EventListener onoffline;
	private EventListener ononline;
	private EventListener onpagehide;
	private EventListener onpageshow;
	private EventListener onpause;
	private EventListener onplay;
	private EventListener onplaying;
	private EventListener onpopstate;
	private EventListener onprogress;
	private EventListener onratechange;
	private EventListener onreset;
	private EventListener onresize;
	private EventListener onscroll;
	private EventListener onseeked;
	private EventListener onseeking;
	private EventListener onselect;
	private EventListener onshow;
	private EventListener onsort;
	private EventListener onstalled;
	private EventListener onstorage;
	private EventListener onsubmit;
	private EventListener onsuspend;
	private EventListener ontimeupdate;
	private EventListener onunload;
	private EventListener onvolumechange;
	private EventListener onwaiting;
 	private History history;
 	private Location location;
 	private Navigator navigator;
	private OnErrorEventHandler onerror;
	private Window opener;
	private Window parent;
	private Window top;
 	private WindowEnvironment windowEnvironment;

	public EventDispatcher getEventDispatcher() {
		return eventDispatcher;
	}

	public void setEventDispatcher(EventDispatcher eventDispatcher) {
		this.eventDispatcher = eventDispatcher;
	}

	public Window getOpener() {
		return opener;
	}

	public void setOpener(Window opener) {
		this.opener = opener;
	}

	public Window getParent() {
		return parent;
	}

	public void setParent(Window parent) {
		this.parent = parent;
	}

	public Window getTop() {
		return top;
	}

	public void setTop(Window top) {
		this.top = top;
	}

	public BarProp getLocationbar() {
		return locationbar;
	}

	public void setLocationbar(BarProp locationbar) {
		this.locationbar = locationbar;
	}

	public BarProp getMenubar() {
		return menubar;
	}

	public void setMenubar(BarProp menubar) {
		this.menubar = menubar;
	}

	public BarProp getPersonalbar() {
		return personalbar;
	}

	public void setPersonalbar(BarProp personalbar) {
		this.personalbar = personalbar;
	}

	public BarProp getScrollbars() {
		return scrollbars;
	}

	public void setScrollbars(BarProp scrollbars) {
		this.scrollbars = scrollbars;
	}

	public BarProp getStatusbar() {
		return statusbar;
	}

	public void setStatusbar(BarProp statusbar) {
		this.statusbar = statusbar;
	}

	public BarProp getToolbar() {
		return toolbar;
	}

	public void setToolbar(BarProp toolbar) {
		this.toolbar = toolbar;
	}

	public EventListener getOnabort() {
		return onabort;
	}

	public void setOnabort(EventListener onabort) {
		this.onabort = onabort;
	}

	public EventListener getOnblur() {
		return onblur;
	}

	public void setOnblur(EventListener onblur) {
		this.onblur = onblur;
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

	public EventListener getOnload() {
		return onload;
	}

	public void setOnload(EventListener onload) {
		this.onload = onload;
		eventDispatcher.addEventListener(this, "load", onload, false);
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

	public EventListener getOnscroll() {
		return onscroll;
	}

	public void setOnscroll(EventListener onscroll) {
		this.onscroll = onscroll;
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

	public EventListener getOnafterprint() {
		return onafterprint;
	}

	public void setOnafterprint(EventListener onafterprint) {
		this.onafterprint = onafterprint;
	}

	public EventListener getOnbeforeprint() {
		return onbeforeprint;
	}

	public void setOnbeforeprint(EventListener onbeforeprint) {
		this.onbeforeprint = onbeforeprint;
	}

	public EventListener getOnbeforeunload() {
		return onbeforeunload;
	}

	public void setOnbeforeunload(EventListener onbeforeunload) {
		this.onbeforeunload = onbeforeunload;
	}

	public EventListener getOnfullscreenchange() {
		return onfullscreenchange;
	}

	public void setOnfullscreenchange(EventListener onfullscreenchange) {
		this.onfullscreenchange = onfullscreenchange;
	}

	public EventListener getOnfullscreenerror() {
		return onfullscreenerror;
	}

	public void setOnfullscreenerror(EventListener onfullscreenerror) {
		this.onfullscreenerror = onfullscreenerror;
	}

	public EventListener getOnhashchange() {
		return onhashchange;
	}

	public void setOnhashchange(EventListener onhashchange) {
		this.onhashchange = onhashchange;
	}

	public EventListener getOnmessage() {
		return onmessage;
	}

	public void setOnmessage(EventListener onmessage) {
		this.onmessage = onmessage;
	}

	public EventListener getOnoffline() {
		return onoffline;
	}

	public void setOnoffline(EventListener onoffline) {
		this.onoffline = onoffline;
	}

	public EventListener getOnonline() {
		return ononline;
	}

	public void setOnonline(EventListener ononline) {
		this.ononline = ononline;
	}

	public EventListener getOnpagehide() {
		return onpagehide;
	}

	public void setOnpagehide(EventListener onpagehide) {
		this.onpagehide = onpagehide;
	}

	public EventListener getOnpageshow() {
		return onpageshow;
	}

	public void setOnpageshow(EventListener onpageshow) {
		this.onpageshow = onpageshow;
	}

	public EventListener getOnpopstate() {
		return onpopstate;
	}

	public void setOnpopstate(EventListener onpopstate) {
		this.onpopstate = onpopstate;
	}

	public EventListener getOnresize() {
		return onresize;
	}

	public void setOnresize(EventListener onresize) {
		this.onresize = onresize;
	}

	public EventListener getOnstorage() {
		return onstorage;
	}

	public void setOnstorage(EventListener onstorage) {
		this.onstorage = onstorage;
	}

	public EventListener getOnunload() {
		return onunload;
	}

	public void setOnunload(EventListener onunload) {
		this.onunload = onunload;
	}

	public ApplicationCache getApplicationCache() {
		return applicationCache;
	}

	public External getExternal() {
		return external;
	}

	public History getHistory() {
		return history;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Navigator getNavigator() {
		return navigator;
	}

	public Window getSelf() {
		return this;
	}

	public Window getWindow() {
		return this;
	}

	public Window open(String url, String target, String features, boolean replace) throws IOException {
		return windowEnvironment.open(url, target, features, replace);
	}

	public void addEventListener(String type, EventListener listener, boolean useCapture) {
		eventDispatcher.addEventListener(this, type, listener, useCapture);
	}

	public void removeEventListener(String type, EventListener listener, boolean useCapture) {
		eventDispatcher.removeEventListener(this, type, listener, useCapture);
	}

	public boolean dispatchEvent(Event event) throws EventException {
		return eventDispatcher.dispatchEvent(event);
	}

	public String toString() {
		final ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
		return builder.toString();
	}
}