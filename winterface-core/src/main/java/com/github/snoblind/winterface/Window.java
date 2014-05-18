package com.github.snoblind.winterface;

import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.events.EventTarget;

/*
 * https://developer.mozilla.org/en-US/docs/Web/API/Window
 */
public interface Window extends EventTarget, GlobalEventHandlers, WindowEventHandlers {
	ApplicationCache getApplicationCache();
	BarProp getLocationbar();
	BarProp getMenubar();
	BarProp getPersonalbar();
	BarProp getScrollbars();
	BarProp getStatusbar();
	BarProp getToolbar();
	boolean isClosed();
	boolean confirm(String message);
	Document getDocument();
	String getDefaultStatus();
	String getName();
	String getStatus();
	String prompt(String message, String defaultText);
	Element getFrameElement();
	External getExternal();
	History getHistory();
	int getInnerWidth();
	int getInnerHeight();
	int getOuterWidth();
	int getOuterHeight();
	Window[] getFrames();
	Location getLocation();
	long getLength();
	Navigator getNavigator();
	Object eval(String script);
	Object get(String name);
	Object showModalDialog(String url, Object optionalArgument);
	void alert(String message);
	void blur();
	void close();
	void focus();
	void moveBy(int dx, int dy);
	void moveTo(int x, int y);
	void print();
	void resizeBy(int dw, int dh);
	void resizeTo(int w, int h);
	void scrollBy(int dx, int dy);
	void scrollTo(int x, int y);
	void setName(String name);
	void setOpener(Window opener);
	void setStatus(String status);
	void stop();
	Window get(long index);
	Window getOpener();
	Window getParent();
	Window getSelf();
	Window getTop();
	Window getWindow();
	Window open(String url, String target, String features, boolean replace) throws IOException;
}