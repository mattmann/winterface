package com.github.mattmann.winterface;

public interface ProcessingInstruction extends Node {
	String getTarget();
	String getData();
	void setData(String data);
}
