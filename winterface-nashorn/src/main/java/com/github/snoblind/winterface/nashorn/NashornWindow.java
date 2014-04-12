package com.github.snoblind.winterface.nashorn;

import com.github.snoblind.winterface.Window;
import com.github.snoblind.winterface.abstracts.AbstractWindow;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.w3c.dom.Element;

public class NashornWindow extends AbstractWindow {

	private final Map<String, Object> map = new HashMap<String, Object>();

	private NashornDocument document;
	
	public NashornDocument getDocument() {
		return document;
	}

	public void setDocument(NashornDocument document) {
		this.document = document;
	}

	public Object get(String key) {
		return map.get(key);
	}

	public Object put(String key, Object value) {
		System.out.printf("put(%s, %s)%n", key, value);
		return map.put(key, value);
	}

	public Set<Entry<String, Object>> entrySet() {
		return map.entrySet();
	}
	
	public boolean isClosed() {
		throw new UnsupportedOperationException();
	}

	public boolean confirm(String message) {
		throw new UnsupportedOperationException();
	}

	public String getDefaultStatus() {
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

	public int getInnerWidth() {
		throw new UnsupportedOperationException();
	}

	public int getInnerHeight() {
		throw new UnsupportedOperationException();
	}

	public int getOuterWidth() {
		throw new UnsupportedOperationException();
	}

	public int getOuterHeight() {
		throw new UnsupportedOperationException();
	}

	public Window[] getFrames() {
		throw new UnsupportedOperationException();
	}

	public long getLength() {
		throw new UnsupportedOperationException();
	}

	public Object eval(String script) {
		throw new UnsupportedOperationException();
	}

	public Object showModalDialog(String url, Object optionalArgument) {
		throw new UnsupportedOperationException();
	}

	public void alert(String message) {
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

	public void moveBy(int dx, int dy) {
		throw new UnsupportedOperationException();
	}

	public void moveTo(int x, int y) {
		throw new UnsupportedOperationException();
	}

	public void print() {
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

	public void setName(String name) {
		throw new UnsupportedOperationException();
	}

	public void setStatus(String status) {
		throw new UnsupportedOperationException();
	}

	public void stop() {
		throw new UnsupportedOperationException();
	}

	public Window get(long index) {
		throw new UnsupportedOperationException();
	}
}
