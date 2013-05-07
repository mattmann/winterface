package com.github.mattmann.winterface;

public interface History {
	long getLength();
	Object getState();
	void back();
	void forward();
	void go(long delta);
	void pushState(Object data, String title, String url);
	void replaceState(Object data, String title, String url);
}
