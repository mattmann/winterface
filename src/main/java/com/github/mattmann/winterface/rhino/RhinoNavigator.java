package com.github.mattmann.winterface.rhino;

import com.github.mattmann.winterface.Navigator;

public class RhinoNavigator implements Navigator {

	public CharSequence getAppName() {
		throw new UnsupportedOperationException();
	}

	public CharSequence getAppVersion() {
		throw new UnsupportedOperationException();
	}

	public CharSequence getPlatform() {
		throw new UnsupportedOperationException();
	}

	public CharSequence getUserAgent() {
		throw new UnsupportedOperationException();
	}

	public CharSequence getLanguage() {
		throw new UnsupportedOperationException();
	}

	public boolean isOnLine() {
		throw new UnsupportedOperationException();
	}

	public CharSequence isContentHandlerRegistered(CharSequence mimeType, CharSequence url) {
		throw new UnsupportedOperationException();
	}

	public CharSequence isProtocolHandlerRegistered(CharSequence scheme, CharSequence url) {
		throw new UnsupportedOperationException();
	}

	public void registerContentHandler(CharSequence mimeType, CharSequence url, CharSequence title) {
		throw new UnsupportedOperationException();
	}

	public void registerProtocolHandler(CharSequence scheme, CharSequence url, CharSequence title) {
		throw new UnsupportedOperationException();
	}

	public void unregisterContentHandler(CharSequence mimeType, CharSequence url) {
		throw new UnsupportedOperationException();
	}

	public void unregisterProtocolHandler(CharSequence scheme, CharSequence url) {
		throw new UnsupportedOperationException();
	}

	public void yieldForStorageUpdates() {
		throw new UnsupportedOperationException();
	}
}