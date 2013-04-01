package com.github.mattmann.winterface;

public interface History {
	long getLength();
	Object getState();
	void back();
	void forward();
	void go(long delta);
	void pushState(Object data, CharSequence title, CharSequence url);
	void replaceState(Object data, CharSequence title, CharSequence url);
}
