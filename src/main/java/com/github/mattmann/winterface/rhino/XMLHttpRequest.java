package com.github.mattmann.winterface.rhino;

import com.github.mattmann.winterface.EventListener;
import java.io.IOException;
import org.w3c.dom.Document;

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
