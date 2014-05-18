package com.github.snoblind.winterface;

import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.events.EventListener;

public interface XMLHttpRequest {
	void open(String method, String url, boolean asynchronous, String username, String password);
	void send(String data) throws IOException;
	int getReadyState();
	int getStatus();
	String getResponseText();
	Document getResponseXML();
	EventListener getOnreadystatechange();
	void setOnreadystatechange(EventListener listener);
}
