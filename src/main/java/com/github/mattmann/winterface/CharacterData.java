package com.github.mattmann.winterface;

public interface CharacterData extends Node {
	CharSequence getData();
	CharSequence substringData(int offset, int count) throws DOMException;
	long getLength();
	void appendData(CharSequence arg) throws DOMException;
	void deleteData(int offset, int count) throws DOMException;
	void insertData(int offset, CharSequence arg) throws DOMException;
	void replaceData(int offset, int count, CharSequence arg) throws DOMException;
	void setData(CharSequence data);
}
