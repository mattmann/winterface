package com.github.mattmann.winterface;

public interface CharacterData extends Node {
	String getData();
	String substringData(int offset, int count) throws DOMException;
	long getLength();
	void appendData(String arg) throws DOMException;
	void deleteData(int offset, int count) throws DOMException;
	void insertData(int offset, String arg) throws DOMException;
	void replaceData(int offset, int count, String arg) throws DOMException;
	void setData(String data);
}
