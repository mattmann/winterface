package com.github.mattmann.winterface;

public interface CharacterData extends Node {
	CharSequence getData();
	CharSequence substringData(long offset, long count) throws DOMException;
	long getLength();
	void appendData(CharSequence arg) throws DOMException;
	void deleteData(long offset, long count) throws DOMException;
	void insertData(long offset, CharSequence arg) throws DOMException;
	void replaceData(long offset, long count, CharSequence arg) throws DOMException;
	void setData(CharSequence data);
}
