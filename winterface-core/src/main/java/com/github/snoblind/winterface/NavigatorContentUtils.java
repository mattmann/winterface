package com.github.snoblind.winterface;

public interface NavigatorContentUtils {
	String isContentHandlerRegistered(String mimeType, String url);
	String isProtocolHandlerRegistered(String scheme, String url);
	void registerContentHandler(String mimeType, String url, String title);
	void registerProtocolHandler(String scheme, String url, String title);
	void unregisterContentHandler(String mimeType, String url);
	void unregisterProtocolHandler(String scheme, String url);
}
