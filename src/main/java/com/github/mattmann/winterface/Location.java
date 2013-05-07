package com.github.mattmann.winterface;

public interface Location extends URLUtils {
	String getHref();
	void setHref(String href);
	void assign(String url);
	void replace(String url);
	void reload();
}
