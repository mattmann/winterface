package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.ApplicationCache;
import com.github.snoblind.winterface.BarProp;
import com.github.snoblind.winterface.Event;
import com.github.snoblind.winterface.EventException;
import com.github.snoblind.winterface.EventListener;
import com.github.snoblind.winterface.External;
import com.github.snoblind.winterface.GlobalEventHandlers;
import com.github.snoblind.winterface.History;
import com.github.snoblind.winterface.Location;
import com.github.snoblind.winterface.Navigator;
import com.github.snoblind.winterface.NodeAdapterFactory;
import com.github.snoblind.winterface.OnErrorEventHandler;
import com.github.snoblind.winterface.Window;
import com.github.snoblind.winterface.WindowEventHandlers;
import com.github.snoblind.winterface.XMLHttpRequest;
import com.github.snoblind.winterface.event.EventDispatcher;
import com.github.snoblind.winterface.spi.HTMLParser;
import com.github.snoblind.winterface.spi.QuerySelector;
import java.io.IOException;
import java.util.Timer;
import org.apache.commons.collections4.Factory;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import static com.github.snoblind.winterface.required.RequiredProperties.assertRequiredProperties;
import static org.apache.commons.lang.Validate.notNull;

public class RhinoWindow extends ScriptableObject implements Cloneable, Window {

	private static final long serialVersionUID = 6419776873162088518L;
	private static final Logger LOG = LoggerFactory.getLogger(RhinoWindow.class);

	protected Timer timer;

	private Console console;
	private Location location;
	private EventDispatcher eventDispatcher;
	private Factory<HTMLParser> parserFactory;
	private Factory<XMLHttpRequest> xmlHttpRequestFactory;
	private GlobalEventHandlers globalEventHandlers;
	private NodeAdapterFactory<Node> nodeAdapterFactory;
	private QuerySelector querySelector;
	private RhinoDocument document;
	private WindowEventHandlers windowEventHandlers;

	@Required
	public QuerySelector getQuerySelector() {
		return querySelector;
	}
	
	@Required
	public Factory<HTMLParser> getParserFactory() {
		return parserFactory;
	}

	@Required
	public Factory<XMLHttpRequest> getXmlHttpRequestFactory() {
		return xmlHttpRequestFactory;
	}

	@Required
	public EventDispatcher getEventDispatcher() {
		return eventDispatcher;
	}

	@Required
	public NodeAdapterFactory<Node> getNodeAdapterFactory() {
		return nodeAdapterFactory;
	}

	@Required
	public Timer getTimer() {
		return timer;
	}

	@Required
	public Console getConsole() {
		return console;
	}

	@Required
	public GlobalEventHandlers getGlobalEventHandlers() {
		return globalEventHandlers;
	}

	@Required
	public WindowEventHandlers getWindowEventHandlers() {
		return windowEventHandlers;
	}
	
//	@Required
	public Navigator getNavigator() {
		throw new UnsupportedOperationException();
	}

	public Location getLocation() {
		return location;
	}

	protected void setLocation(Location location) {
		this.location = location;
	}
	
	public RhinoDocument getDocument() {
		return document;
	}

	protected void setDocument(final RhinoDocument document) {
		notNull(document);
		if (this.document != null) {
			this.document.setDefaultView(null);
		}
		this.document = document;
		this.document.setDefaultView(this);
	}

	public boolean has(String name, Scriptable start) {
		LOG.debug("has({}, {})", name, start);
		return super.has(name, start);
	}

	public Object get(String name, Scriptable start) {
		LOG.debug("get({}, {})", name, start);
		if ("alert".equals(name)) {
			return new MethodFunction(this, "alert");
		}
		if ("document".equals(name)) {
			return document;
		}
		if ("location".equals(name)) {
			return location;
		}
		if ("setTimeout".equals(name)) {
			return new SetTimeoutFunction(this);
		}
		return super.get(name, start);
	}

	public void put(String name, Scriptable start, Object value) {
		LOG.debug("put({}, {}, {})", name, start, value);
		super.put(name, start, value);
	}

	public String getClassName() {
		return getClass().getName();
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

	public EventListener getOnabort() {
		throw new UnsupportedOperationException();
	}

	public void setOnabort(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnblur() {
		throw new UnsupportedOperationException();
	}

	public void setOnblur(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public OnErrorEventHandler getOnerror() {
		throw new UnsupportedOperationException();
	}

	public void setOnerror(OnErrorEventHandler handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnfocus() {
		throw new UnsupportedOperationException();
	}

	public void setOnfocus(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOncancel() {
		throw new UnsupportedOperationException();
	}

	public void setOncancel(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOncanplay() {
		throw new UnsupportedOperationException();
	}

	public void setOncanplay(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOncanplaythrough() {
		throw new UnsupportedOperationException();
	}

	public void setOncanplaythrough(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnchange() {
		throw new UnsupportedOperationException();
	}

	public void setOnchange(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnclick() {
		throw new UnsupportedOperationException();
	}

	public void setOnclick(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnclose() {
		throw new UnsupportedOperationException();
	}

	public void setOnclose(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOncontextmenu() {
		throw new UnsupportedOperationException();
	}

	public void setOncontextmenu(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOncuechange() {
		throw new UnsupportedOperationException();
	}

	public void setOncuechange(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOndblclick() {
		throw new UnsupportedOperationException();
	}

	public void setOndblclick(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOndrag() {
		throw new UnsupportedOperationException();
	}

	public void setOndrag(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOndragend() {
		throw new UnsupportedOperationException();
	}

	public void setOndragend(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOndragenter() {
		throw new UnsupportedOperationException();
	}

	public void setOndragenter(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOndragleave() {
		throw new UnsupportedOperationException();
	}

	public void setOndragleave(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOndragover() {
		throw new UnsupportedOperationException();
	}

	public void setOndragover(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOndragstart() {
		throw new UnsupportedOperationException();
	}

	public void setOndragstart(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOndrop() {
		throw new UnsupportedOperationException();
	}

	public void setOndrop(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOndurationchange() {
		throw new UnsupportedOperationException();
	}

	public void setOndurationchange(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnemptied() {
		throw new UnsupportedOperationException();
	}

	public void setOnemptied(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnended() {
		throw new UnsupportedOperationException();
	}

	public void setOnended(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOninput() {
		throw new UnsupportedOperationException();
	}

	public void setOninput(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOninvalid() {
		throw new UnsupportedOperationException();
	}

	public void setOninvalid(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnkeydown() {
		throw new UnsupportedOperationException();
	}

	public void setOnkeydown(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnkeypress() {
		throw new UnsupportedOperationException();
	}

	public void setOnkeypress(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnkeyup() {
		throw new UnsupportedOperationException();
	}

	public void setOnkeyup(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnload() {
		throw new UnsupportedOperationException();
	}

	public void setOnload(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnloadeddata() {
		throw new UnsupportedOperationException();
	}

	public void setOnloadeddata(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnloadedmetadata() {
		throw new UnsupportedOperationException();
	}

	public void setOnloadedmetadata(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnloadstart() {
		throw new UnsupportedOperationException();
	}

	public void setOnloadstart(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnmousedown() {
		throw new UnsupportedOperationException();
	}

	public void setOnmousedown(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnmouseenter() {
		throw new UnsupportedOperationException();
	}

	public void setOnmouseenter(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnmouseleave() {
		throw new UnsupportedOperationException();
	}

	public void setOnmouseleave(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnmousemove() {
		throw new UnsupportedOperationException();
	}

	public void setOnmousemove(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnmouseout() {
		throw new UnsupportedOperationException();
	}

	public void setOnmouseout(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnmouseover() {
		throw new UnsupportedOperationException();
	}

	public void setOnmouseover(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnmouseup() {
		throw new UnsupportedOperationException();
	}

	public void setOnmouseup(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnmousewheel() {
		throw new UnsupportedOperationException();
	}

	public void setOnmousewheel(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnpause() {
		throw new UnsupportedOperationException();
	}

	public void setOnpause(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnplay() {
		throw new UnsupportedOperationException();
	}

	public void setOnplay(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnplaying() {
		throw new UnsupportedOperationException();
	}

	public void setOnplaying(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnprogress() {
		throw new UnsupportedOperationException();
	}

	public void setOnprogress(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnratechange() {
		throw new UnsupportedOperationException();
	}

	public void setOnratechange(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnreset() {
		throw new UnsupportedOperationException();
	}

	public void setOnreset(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnscroll() {
		throw new UnsupportedOperationException();
	}

	public void setOnscroll(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnseeked() {
		throw new UnsupportedOperationException();
	}

	public void setOnseeked(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnseeking() {
		throw new UnsupportedOperationException();
	}

	public void setOnseeking(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnselect() {
		throw new UnsupportedOperationException();
	}

	public void setOnselect(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnshow() {
		throw new UnsupportedOperationException();
	}

	public void setOnshow(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnsort() {
		throw new UnsupportedOperationException();
	}

	public void setOnsort(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnstalled() {
		throw new UnsupportedOperationException();
	}

	public void setOnstalled(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnsubmit() {
		throw new UnsupportedOperationException();
	}

	public void setOnsubmit(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnsuspend() {
		throw new UnsupportedOperationException();
	}

	public void setOnsuspend(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOntimeupdate() {
		throw new UnsupportedOperationException();
	}

	public void setOntimeupdate(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnvolumechange() {
		throw new UnsupportedOperationException();
	}

	public void setOnvolumechange(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnwaiting() {
		throw new UnsupportedOperationException();
	}

	public void setOnwaiting(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnafterprint() {
		throw new UnsupportedOperationException();
	}

	public void setOnafterprint(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnbeforeprint() {
		throw new UnsupportedOperationException();
	}

	public void setOnbeforeprint(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnbeforeunload() {
		throw new UnsupportedOperationException();
	}

	public void setOnbeforeunload(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnfullscreenchange() {
		throw new UnsupportedOperationException();
	}

	public void setOnfullscreenchange(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnfullscreenerror() {
		throw new UnsupportedOperationException();
	}

	public void setOnfullscreenerror(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnhashchange() {
		throw new UnsupportedOperationException();
	}

	public void setOnhashchange(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnmessage() {
		throw new UnsupportedOperationException();
	}

	public void setOnmessage(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnoffline() {
		throw new UnsupportedOperationException();
	}

	public void setOnoffline(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnonline() {
		throw new UnsupportedOperationException();
	}

	public void setOnonline(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnpagehide() {
		throw new UnsupportedOperationException();
	}

	public void setOnpagehide(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnpageshow() {
		throw new UnsupportedOperationException();
	}

	public void setOnpageshow(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnpopstate() {
		throw new UnsupportedOperationException();
	}

	public void setOnpopstate(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnresize() {
		throw new UnsupportedOperationException();
	}

	public void setOnresize(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnstorage() {
		throw new UnsupportedOperationException();
	}

	public void setOnstorage(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnunload() {
		throw new UnsupportedOperationException();
	}

	public void setOnunload(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public ApplicationCache getApplicationCache() {
		throw new UnsupportedOperationException();
	}

	public BarProp getLocationbar() {
		throw new UnsupportedOperationException();
	}

	public BarProp getMenubar() {
		throw new UnsupportedOperationException();
	}

	public BarProp getPersonalbar() {
		throw new UnsupportedOperationException();
	}

	public BarProp getScrollbars() {
		throw new UnsupportedOperationException();
	}

	public BarProp getStatusbar() {
		throw new UnsupportedOperationException();
	}

	public BarProp getToolbar() {
		throw new UnsupportedOperationException();
	}

	public boolean confirm(String message) {
		throw new UnsupportedOperationException();
	}

	public String getName() {
		throw new UnsupportedOperationException();
	}

	public String getStatus() {
		throw new UnsupportedOperationException();
	}

	public String prompt(String message, String defaultText) {
		throw new UnsupportedOperationException();
	}

	public Element getFrameElement() {
		throw new UnsupportedOperationException();
	}

	public External getExternal() {
		throw new UnsupportedOperationException();
	}

	public History getHistory() {
		throw new UnsupportedOperationException();
	}

	public long getLength() {
		throw new UnsupportedOperationException();
	}

	public Object get(String name) {
		throw new UnsupportedOperationException();
	}

	public Object showModalDialog(String url, Object optionalArgument) {
		throw new UnsupportedOperationException();
	}

	public void alert(String message) {
		console.log(message);
	}

	public void blur() {
		throw new UnsupportedOperationException();
	}

	public void close() {
		throw new UnsupportedOperationException();
	}

	public void focus() {
		throw new UnsupportedOperationException();
	}

	public void print() {
		throw new UnsupportedOperationException();
	}

	public void setName(String name) {
		throw new UnsupportedOperationException();
	}

	public void setOpener(Window opener) {
		throw new UnsupportedOperationException();
	}

	public void setStatus(String status) {
		throw new UnsupportedOperationException();
	}

	public void stop() {
		throw new UnsupportedOperationException();
	}

	public Window getFrames() {
		throw new UnsupportedOperationException();
	}

	public Window get(long index) {
		throw new UnsupportedOperationException();
	}

	public Window getOpener() {
		throw new UnsupportedOperationException();
	}

	public Window getParent() {
		throw new UnsupportedOperationException();
	}

	public Window getSelf() {
		return this;
	}

	public Window getTop() {
		throw new UnsupportedOperationException();
	}

	public Window getWindow() {
		return this;
	}

	public Window open(String url, String target, String features, boolean replace) throws IOException {
		throw new UnsupportedOperationException();
	}

	public RhinoWindow clone() {
		try {
			return (RhinoWindow) super.clone();
		}
		catch (CloneNotSupportedException x) {
			throw new RuntimeException(x);
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private final RhinoWindow window = new RhinoWindow();

		private Builder() {
		}

		public RhinoWindow build() {
			assertRequiredProperties(window);
			final RhinoWindow window = this.window.clone();
			if (window.document != null) {
				window.setDocument(window.document);
			}
			return window;
		}

		public Builder querySelector(QuerySelector querySelector) {
			window.querySelector = querySelector;
			return this;
		}
		
		public Builder document(RhinoDocument document) {
			window.setDocument(document);
			return this;
		}
		
		public Builder timer(Timer timer) {
			window.timer = timer;
			return this;
		}
		
		public Builder console(Console console) {
			window.console = console;
			return this;
		}
		
		public Builder globalEventHandlers(GlobalEventHandlers globalEventHandlers) {
			window.globalEventHandlers = globalEventHandlers;
			return this;
		}
		
		public Builder windowEventHandlers(WindowEventHandlers windowEventHandlers) {
			window.windowEventHandlers = windowEventHandlers;
			return this;
		}

		public Builder location(Location location) {
			window.setLocation(location);
			return this;
		}

		public Builder eventDispatcher(EventDispatcher eventDispatcher) {
			window.eventDispatcher = eventDispatcher;
			return this;
		}

		public Builder parserFactory(Factory<HTMLParser> parserFactory) {
			window.parserFactory = parserFactory;
			return this;
		}
		
		public Builder nodeAdapterFactory(NodeAdapterFactory<Node> nodeAdapterFactory) {
			window.nodeAdapterFactory = nodeAdapterFactory;
			return this;
		}

		public Builder xmlHttpRequestFactory(Factory<XMLHttpRequest> xmlHttpRequestFactory) {
			window.xmlHttpRequestFactory = xmlHttpRequestFactory;
			return this;
		}
	}
}