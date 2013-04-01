package com.github.mattmann.winterface;

public interface Text extends CharacterData {
	Text splitText(long offset) throws DOMException;
}
