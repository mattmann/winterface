package com.github.mattmann.winterface;

public interface Location extends URLUtils {
	CharSequence getHref();
	void setHref(CharSequence href);
	void assign(CharSequence url);
	void replace(CharSequence url);
	void reload();
}
