package com.github.mattmann.winterface;

public interface ProcessingInstruction extends Node {
	CharSequence getTarget();
	CharSequence getData();
	void setData(CharSequence data);
}
