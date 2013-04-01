package com.github.mattmann.winterface;

import java.io.IOException;

public interface Window extends EventTarget, GlobalEventHandlers, WindowEventHandlers {
	ApplicationCache getApplicationCache();
	BarProp getLocationbar();
	BarProp getMenubar();
	BarProp getPersonalbar();
	BarProp getScrollbars();
	BarProp getStatusbar();
	BarProp getToolbar();
	boolean confirm(CharSequence message);
	Document getDocument();
	CharSequence getName();
	CharSequence getStatus();
	CharSequence prompt(CharSequence message, CharSequence defaultText);
	Element getFrameElement();
	External getExternal();
	History getHistory();
	Location getLocation();
	long getLength();
	Navigator getNavigator();
	Object get(CharSequence name);
	Object showModalDialog(CharSequence url, Object optionalArgument);
	void alert(CharSequence message);
	void blur();
	void close();
	void focus();
	void print();
	void setName(CharSequence name);
	void setOpener(Window opener);
	void setStatus(CharSequence status);
	void stop();
	Window getFrames();
	Window get(long index);
	Window getOpener();
	Window getParent();
	Window getSelf();
	Window getTop();
	Window getWindow();
	Window open(CharSequence url, CharSequence target, CharSequence features, boolean replace) throws IOException;
}