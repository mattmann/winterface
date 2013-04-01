package com.github.mattmann.winterface;

public interface NavigatorContentUtils {
	CharSequence isContentHandlerRegistered(CharSequence mimeType, CharSequence url);
	CharSequence isProtocolHandlerRegistered(CharSequence scheme, CharSequence url);
	void registerContentHandler(CharSequence mimeType, CharSequence url, CharSequence title);
	void registerProtocolHandler(CharSequence scheme, CharSequence url, CharSequence title);
	void unregisterContentHandler(CharSequence mimeType, CharSequence url);
	void unregisterProtocolHandler(CharSequence scheme, CharSequence url);
}
