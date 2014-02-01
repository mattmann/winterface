package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.Navigator;

public class RhinoNavigator implements Navigator {

	public String getAppName() {
		throw new UnsupportedOperationException();
	}

	public String getAppVersion() {
		throw new UnsupportedOperationException();
	}

	public String getPlatform() {
		throw new UnsupportedOperationException();
	}

	public String getUserAgent() {
		throw new UnsupportedOperationException();
	}

	public String getLanguage() {
		throw new UnsupportedOperationException();
	}

	public boolean isOnLine() {
		throw new UnsupportedOperationException();
	}

	public String isContentHandlerRegistered(String mimeType, String url) {
		throw new UnsupportedOperationException();
	}

	public String isProtocolHandlerRegistered(String scheme, String url) {
		throw new UnsupportedOperationException();
	}

	public void registerContentHandler(String mimeType, String url, String title) {
		throw new UnsupportedOperationException();
	}

	public void registerProtocolHandler(String scheme, String url, String title) {
		throw new UnsupportedOperationException();
	}

	public void unregisterContentHandler(String mimeType, String url) {
		throw new UnsupportedOperationException();
	}

	public void unregisterProtocolHandler(String scheme, String url) {
		throw new UnsupportedOperationException();
	}

	public void yieldForStorageUpdates() {
		throw new UnsupportedOperationException();
	}
}