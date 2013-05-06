package com.github.mattmann.winterface;

public interface Text extends CharacterData {
	Text splitText(int offset) throws DOMException;
}
