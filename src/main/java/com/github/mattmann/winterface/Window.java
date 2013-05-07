package com.github.mattmann.winterface;

import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface Window extends EventTarget, GlobalEventHandlers, WindowEventHandlers {
	ApplicationCache getApplicationCache();
	BarProp getLocationbar();
	BarProp getMenubar();
	BarProp getPersonalbar();
	BarProp getScrollbars();
	BarProp getStatusbar();
	BarProp getToolbar();
	boolean confirm(String message);
	Document getDocument();
	String getName();
	String getStatus();
	String prompt(String message, String defaultText);
	Element getFrameElement();
	External getExternal();
	History getHistory();
	Location getLocation();
	long getLength();
	Navigator getNavigator();
	Object get(String name);
	Object showModalDialog(String url, Object optionalArgument);
	void alert(String message);
	void blur();
	void close();
	void focus();
	void print();
	void setName(String name);
	void setOpener(Window opener);
	void setStatus(String status);
	void stop();
	Window getFrames();
	Window get(long index);
	Window getOpener();
	Window getParent();
	Window getSelf();
	Window getTop();
	Window getWindow();
	Window open(String url, String target, String features, boolean replace) throws IOException;
}