package com.github.mattmann.winterface.jsoup;

import com.github.mattmann.winterface.AbstractWindow;
import com.github.mattmann.winterface.ApplicationCache;
import com.github.mattmann.winterface.BarProp;
import com.github.mattmann.winterface.Document;
import com.github.mattmann.winterface.Element;
import com.github.mattmann.winterface.Event;
import com.github.mattmann.winterface.EventException;
import com.github.mattmann.winterface.EventListener;
import com.github.mattmann.winterface.External;
import com.github.mattmann.winterface.GlobalEventHandlers;
import com.github.mattmann.winterface.History;
import com.github.mattmann.winterface.Location;
import com.github.mattmann.winterface.Navigator;
import com.github.mattmann.winterface.Window;
import com.github.mattmann.winterface.WindowEventHandlers;

import static org.apache.commons.lang.Validate.notNull;

public class JSoupWindow extends AbstractWindow {

	protected final JSoupLocation location;

	protected JSoupDocument document;

	public JSoupWindow(GlobalEventHandlers globalEventHandlers, WindowEventHandlers windowEventHandlers, JSoupLocation location, JSoupDocument document) {
		super(globalEventHandlers, windowEventHandlers);
		notNull(this.location = location);
		notNull(this.document = document);
		document.defaultView = this;
	}

	public Document getDocument() {
		return document;
	}

	public Window open(CharSequence url, CharSequence target, CharSequence features, boolean replace) {
		throw new UnsupportedOperationException();
	}

	public Location getLocation() {
		return location;
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

	public boolean confirm(CharSequence message) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getName() {
		throw new UnsupportedOperationException();
	}

	public CharSequence getStatus() {
		throw new UnsupportedOperationException();
	}

	public CharSequence prompt(CharSequence message, CharSequence defaultText) {
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

	public Navigator getNavigator() {
		throw new UnsupportedOperationException();
	}

	public Object get(CharSequence name) {
		throw new UnsupportedOperationException();
	}

	public Object showModalDialog(CharSequence url, Object optionalArgument) {
		throw new UnsupportedOperationException();
	}

	public void alert(CharSequence message) {
		throw new UnsupportedOperationException();
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

	public void setName(CharSequence name) {
		throw new UnsupportedOperationException();
	}

	public void setOpener(Window opener) {
		throw new UnsupportedOperationException();
	}

	public void setStatus(CharSequence status) {
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
		throw new UnsupportedOperationException();
	}

	public Window getTop() {
		throw new UnsupportedOperationException();
	}

	public Window getWindow() {
		throw new UnsupportedOperationException();
	}

	public void addEventListener(CharSequence type, EventListener listener,
			boolean useCapture) {
		throw new UnsupportedOperationException();
	}

	public void removeEventListener(CharSequence type, EventListener listener,
			boolean useCapture) {
		throw new UnsupportedOperationException();
	}

	public boolean dispatchEvent(Event event) throws EventException {
		throw new UnsupportedOperationException();
	}
}
