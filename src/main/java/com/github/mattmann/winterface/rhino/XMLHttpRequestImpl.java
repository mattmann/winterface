package com.github.mattmann.winterface.rhino;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.mattmann.winterface.Document;
import com.github.mattmann.winterface.EventListener;
import com.github.mattmann.winterface.event.AbstractEvent;
import static org.apache.commons.lang.Validate.notNull;

public class XMLHttpRequestImpl implements XMLHttpRequest {

	private static final Logger LOGGER = LoggerFactory.getLogger(XMLHttpRequestImpl.class);

	private final HttpClient client;

	public XMLHttpRequestImpl(HttpClient client) {
		notNull(this.client = client);
	}

	private String method;
	private String url;
	private boolean asynchronous;
	private String username;
	private String password;
	private String responseText;
	private int readyState = 0;
	private HttpUriRequest request;
	private HttpResponse response;
	private EventListener onreadystatechange;

	public void open(String method, String url, boolean asynchronous, String username, String password) {
		LOGGER.debug("open({}, {}, {}, {}, {})", method, url, asynchronous, username, password);
		this.method = method;
		this.url = url;
		this.asynchronous = asynchronous;
		this.username = username;
		this.password = password;
	}

	public void send(String data) throws IOException {
		LOGGER.debug("send({})", data);
		if ("GET".equals(method)) {
			if (asynchronous) {
				asyncGet();
			}
			else {
				get();
			}
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	private void asyncGet() {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					get();
				}
				catch (IOException x) {
					throw new RuntimeException(x);
				}
			}
		});
		thread.start();
	}

	private void get() throws IOException {
		request = new HttpGet(url);
		setReadyState(1);
		response = client.execute(request);
		setReadyState(2);
		HttpEntity entity = response.getEntity();
		setReadyState(3);
		InputStream istream = null;
		try {
			responseText = IOUtils.toString(istream = entity.getContent());
			setReadyState(4);
		}
		finally {
			IOUtils.closeQuietly(istream);
		}
	}

	public int getReadyState() {
		return readyState;
	}

	private void setReadyState(int readyState) {
		this.readyState = readyState;
		fireReadyStateChanged();
	}

	private void fireReadyStateChanged() {
		if (onreadystatechange != null) {
			onreadystatechange.handleEvent(new AbstractEvent() {});
		}
	}

	public int getStatus() {
		if (response == null) {
			return -1;
		}
		return response.getStatusLine().getStatusCode();
	}

	public String getResponseText() {
		return responseText;
	}

	public Document getResponseXML() {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnreadystatechange() {
		return onreadystatechange;
	}

	public void setOnreadystatechange(EventListener onreadystatechange) {
		this.onreadystatechange = onreadystatechange;
	}
}