package com.github.mattmann.winterface;

public interface Entity extends Node {
	CharSequence getPublicId();
	CharSequence getSystemId();
	CharSequence getNotationName();
}
