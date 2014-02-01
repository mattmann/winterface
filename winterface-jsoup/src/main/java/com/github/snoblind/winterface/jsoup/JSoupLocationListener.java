package com.github.snoblind.winterface.jsoup;

import java.beans.PropertyChangeListener;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;

public interface JSoupLocationListener extends PropertyChangeListener {
	void responseReceived(JSoupLocation location, Response response);
	void documentParsed(JSoupLocation location, Document document);
}
