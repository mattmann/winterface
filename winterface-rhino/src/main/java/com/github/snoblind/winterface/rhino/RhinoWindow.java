package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.ApplicationCache;
import com.github.snoblind.winterface.BarProp;
import com.github.snoblind.winterface.External;
import com.github.snoblind.winterface.GlobalEventHandlers;
import com.github.snoblind.winterface.History;
import com.github.snoblind.winterface.Navigator;
import com.github.snoblind.winterface.OnErrorEventHandler;
import com.github.snoblind.winterface.Window;
import com.github.snoblind.winterface.WindowEventHandlers;
import com.github.snoblind.winterface.XMLHttpRequest;
import com.github.snoblind.winterface.event.EventDispatcher;
import com.github.snoblind.winterface.event.ExtendedEvent;
import com.github.snoblind.winterface.spi.HTMLParser;
import com.github.snoblind.winterface.spi.QuerySelector;
import com.github.snoblind.winterface.util.NodeListUtils;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.CookieStore;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Timer;
import java.util.TreeMap;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections4.Factory;
import org.apache.commons.lang.StringUtils;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;
import static com.github.snoblind.winterface.required.RequiredProperties.assertRequiredProperties;
import static com.github.snoblind.winterface.util.ReflectionUtils.getPropertyValue;
import static com.github.snoblind.winterface.util.ReflectionUtils.propertyDescriptorsByName;
import static java.util.Collections.unmodifiableMap;
import static org.apache.commons.codec.binary.StringUtils.getBytesUtf8;
import static org.apache.commons.codec.binary.StringUtils.newStringUtf8;
import static org.apache.commons.lang.Validate.notNull;

public class RhinoWindow extends ScriptableObject implements Cloneable, Window {

	private static final long serialVersionUID = 6419776873162088518L;
	private static final Logger LOGGER = LoggerFactory.getLogger(RhinoWindow.class);
	private static final Map<String, PropertyDescriptor> PROPERTY_DESCRIPTORS_BY_NAME = propertyDescriptorsByName(RhinoWindow.class);

	protected Timer timer;

	private final Map<String, Function> functionsByName;

	private Console console;
	private CookieStore cookieStore;
	private RhinoLocation location;
	private Navigator navigator;
	private EventDispatcher eventDispatcher;
	private Factory<HTMLParser> parserFactory;
	private Factory<XMLHttpRequest> xmlHttpRequestFactory;
	private GlobalEventHandlers globalEventHandlers;
	private QuerySelector querySelector;
	private RhinoDocument document;
	private WindowEventHandlers windowEventHandlers;
	private boolean closed;

	public RhinoWindow() {
		try {
			functionsByName = functionsByName();
		}
		catch (NoSuchMethodException x) {
			throw new RuntimeException(x);
		}
	}

	private MethodFunction newMethodFunction(String name, Class<?>... parameterTypes) throws NoSuchMethodException {
		return new MethodFunction(this, getClass().getMethod(name, parameterTypes));
	}
	
	private Map<String, Function> functionsByName() throws NoSuchMethodException {
		final Map<String, Function> map = new TreeMap<String, Function>();
		map.put("addEventListener", newMethodFunction("addEventListener", String.class, EventListener.class, boolean.class));
		map.put("alert", newMethodFunction("alert", String.class));
		map.put("atob", newMethodFunction("decodeBase64", String.class));
		map.put("blur", newMethodFunction("blur"));
		map.put("btoa", newMethodFunction("encodeBase64", String.class));
//		map.put("clearInterval", newMethodFunction("clearInterval"));
//		map.put("clearTimeout", newMethodFunction("clearTimeout"));
		map.put("close", newMethodFunction("close"));
//		map.put("confirm", newMethodFunction("confirm"));
//		map.put("createPopup", newMethodFunction("createPopup"));
		map.put("focus", newMethodFunction("focus"));
		map.put("moveBy", newMethodFunction("moveBy", int.class, int.class));
		map.put("moveTo", newMethodFunction("moveTo", int.class, int.class));
		map.put("open", newMethodFunction("open", String.class, String.class, String.class, boolean.class));
		map.put("print", newMethodFunction("print"));
//		map.put("prompt", newMethodFunction("prompt"));
		map.put("removeEventListener", newMethodFunction("removeEventListener", String.class, EventListener.class, boolean.class));
		map.put("resizeBy", newMethodFunction("resizeBy", int.class, int.class));
		map.put("resizeTo", newMethodFunction("resizeTo", int.class, int.class));
		map.put("scrollBy", newMethodFunction("scrollBy", int.class, int.class));
		map.put("scrollTo", newMethodFunction("scrollTo", int.class, int.class));
//		map.put("setInterval", newMethodFunction("setInterval"));
		map.put("setTimeout", new SetTimeoutFunction(this));
		map.put("stop", newMethodFunction("stop"));
		return unmodifiableMap(map);
	}

	public String encodeURIComponent(String input) {
		try {
			return URLEncoder.encode(input, "UTF-8");
		}
		catch (UnsupportedEncodingException x) {
			throw new RuntimeException(x);
		}
	}

	public String decodeURIComponent(String input) {
		try {
			return URLDecoder.decode(input, "UTF-8");
		}
		catch (UnsupportedEncodingException x) {
			throw new RuntimeException(x);
		}
	}
	
	public void moveBy(int dx, int dy) {
		throw new UnsupportedOperationException();
	}

	public void moveTo(int x, int y) {
		throw new UnsupportedOperationException();
	}

	public void resizeBy(int dw, int dh) {
		throw new UnsupportedOperationException();
	}

	public void resizeTo(int w, int h) {
		throw new UnsupportedOperationException();
	}

	public void scrollBy(int dx, int dy) {
		throw new UnsupportedOperationException();
	}

	public void scrollTo(int x, int y) {
		throw new UnsupportedOperationException();
	}

	@Required
	public CookieStore getCookieStore() {
		return cookieStore;
	}

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
	
	@Required
	public Navigator getNavigator() {
		return navigator;
	}

	public RhinoLocation getLocation() {
		return location;
	}

	protected void setLocation(RhinoLocation location) {
		this.location = location;
	}
	
	public RhinoDocument getDocument() {
		return document;
	}

	public boolean dispatchEvent(Event event) throws EventException {
		return eventDispatcher.dispatchEvent(event);
	}

	public boolean dispatchEvent(final String eventType, final boolean canBubble, final boolean cancelableArg) throws EventException {
		final ExtendedEvent event = eventDispatcher.createEvent("Event");
		event.setTarget(this);
		event.initEvent(eventType, canBubble, cancelableArg);
		return dispatchEvent(event);
	}

	protected void setDocument(final RhinoDocument document) {
		notNull(document);
		if (this.document != null) {
			this.document.setDefaultView(null);
			dispatchEvent("unload", true, true);
		}
		this.document = document;
		this.document.setDefaultView(this);
		dispatchEvent("load", true, true);
	}

	protected void evalDocument() {
		final NodeList nodes = document.querySelectorAll("script");
		for (Node node: NodeListUtils.iterable(nodes)) {
			evalScriptElement((RhinoElement) node);
		}
	}

	private void evalScriptElement(RhinoElement scriptElement) {
		if (scriptElement.hasAttribute("src")) {
			final String url = scriptElement.getAttribute("src");
			final XMLHttpRequest request = xmlHttpRequestFactory.create();
			request.open("GET", url, false, null, null);
			try {
				request.send(null);
			}
			catch (IOException x) {
				throw new RuntimeException(x);
			}
			eval(request.getResponseText());
		}
		else {
			String text = scriptElement.getInnerText().trim();
			if (text.startsWith("<!--") && text.endsWith("-->")) {
				text = text.substring(4, text.length() - 3).trim();
			}
			LOGGER.debug(text);
			eval(text);
		}
	}
	
	public boolean has(String name, Scriptable start) {
		LOGGER.debug("has({}, {})", name, start);
		return super.has(name, start);
	}

	public Object get(String name, Scriptable start) {
		LOGGER.debug("get({}, {})", name, start);
		if (PROPERTY_DESCRIPTORS_BY_NAME.containsKey(name)) {
			LOGGER.info("Instances of {} have a property named \"{}\".", getClass(), name);
			return getPropertyValue(this, PROPERTY_DESCRIPTORS_BY_NAME.get(name));
		}
		if (functionsByName.containsKey(name)) {
			LOGGER.info("Found function named \"{}\" in Map.", name);
			return functionsByName.get(name);
		}
		final Object result = super.get(name, start);
		if (NOT_FOUND.equals(result)) {
			LOGGER.warn("Window has no such member \"{}\".", name);
		}
		return result;
	}
	
	public String encodeBase64(String string) {
		return Base64.encodeBase64String(getBytesUtf8(string));
	}

	public String decodeBase64(String string) {
		return newStringUtf8(Base64.decodeBase64(string));
	}
	
	public void put(String name, Scriptable start, Object value) {
		LOGGER.debug("put({}, {}, {})", name, start, value);
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

	public Object eval(final String source) {
		LOGGER.info("eval({})", source);
		Context context = Context.getCurrentContext();
		notNull(context);
		return context.evaluateString(this, source, null, 0, null);
	}

	public void close() {
		// fire events
		closed = true;
	}

	public boolean isClosed() {
		return closed;
	}

	public boolean getClosed() {
		return isClosed();
	}
	
	public String getDefaultStatus() {
		return StringUtils.EMPTY;
	}

	public int getInnerWidth() {
		return 0;
	}

	public int getInnerHeight() {
		return 0;
	}

	public int getOuterWidth() {
		return 0;
	}

	public int getOuterHeight() {
		return 0;
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
		return globalEventHandlers.getOnload();
	}

	public void setOnload(EventListener handler) {
		globalEventHandlers.setOnload(handler);
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
		return windowEventHandlers.getOnunload();
	}

	public void setOnunload(EventListener handler) {
		windowEventHandlers.setOnunload(handler);
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
		return 0;
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

	public Window[] getFrames() {
		return new Window[0];
	}

	public Window get(long index) {
		throw new UnsupportedOperationException();
	}

	public Window getOpener() {
		return null;
	}

	public Window getParent() {
		return null;
	}

	public Window getSelf() {
		return this;
	}

	public Window getTop() {
		return this;
	}

	public Window getWindow() {
		return this;
	}

	public Window open(String url, String target, String features, boolean replace) throws IOException {
		throw new UnsupportedOperationException();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private RhinoWindow window;

		private Builder() {
		}

		public RhinoWindow build() {
			assertRequiredProperties(window);
			final RhinoWindow window = this.window;
			this.window = null;
			return window;
		}
		
		private RhinoWindow getWindow() {
			if (window == null) {
				window = new RhinoWindow();
			}
			return window;
		}

		public Builder navigator(Navigator navigator) {
			getWindow().navigator = navigator;
			return this;
		}
		
		public Builder querySelector(QuerySelector querySelector) {
			getWindow().querySelector = querySelector;
			return this;
		}
		
		public Builder document(RhinoDocument document) {
			getWindow().setDocument(document);
			return this;
		}
		
		public Builder timer(Timer timer) {
			getWindow().timer = timer;
			return this;
		}
		
		public Builder console(Console console) {
			getWindow().console = console;
			return this;
		}
		
		public Builder globalEventHandlers(GlobalEventHandlers globalEventHandlers) {
			getWindow().globalEventHandlers = globalEventHandlers;
			return this;
		}
		
		public Builder windowEventHandlers(WindowEventHandlers windowEventHandlers) {
			getWindow().windowEventHandlers = windowEventHandlers;
			return this;
		}

		public Builder location(RhinoLocation location) {
			getWindow().setLocation(location);
			return this;
		}

		public Builder eventDispatcher(EventDispatcher eventDispatcher) {
			getWindow().eventDispatcher = eventDispatcher;
			return this;
		}

		public Builder parserFactory(Factory<HTMLParser> parserFactory) {
			getWindow().parserFactory = parserFactory;
			return this;
		}

		public Builder xmlHttpRequestFactory(Factory<XMLHttpRequest> xmlHttpRequestFactory) {
			getWindow().xmlHttpRequestFactory = xmlHttpRequestFactory;
			return this;
		}

		public Builder cookieStore(CookieStore cookieStore) {
			getWindow().cookieStore = cookieStore;
			return this;
		}
	}

	public CSSStyleDeclaration getComputedStyle(Element elt, String pseudoElt) {
		throw new UnsupportedOperationException();
	}
}
