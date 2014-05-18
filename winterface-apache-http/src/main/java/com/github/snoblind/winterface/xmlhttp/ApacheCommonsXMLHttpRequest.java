package com.github.snoblind.winterface.xmlhttp;

import com.github.snoblind.winterface.XMLHttpRequest;
import com.github.snoblind.winterface.event.EventDispatcher;
import com.github.snoblind.winterface.event.ExtendedEvent;
import com.github.snoblind.winterface.spi.HTMLParser;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;
import org.apache.commons.collections4.Factory;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import static org.apache.commons.lang.Validate.notNull;

public class ApacheCommonsXMLHttpRequest implements EventTarget, XMLHttpRequest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApacheCommonsXMLHttpRequest.class);

	private HttpClient client;
	private Factory<? extends HTMLParser> parserFactory;
	private EventDispatcher eventDispatcher;
	private EventListener onreadystatechange;

	@SuppressWarnings("unused") private String username;
	@SuppressWarnings("unused") private String password;

	private String method;
	private String url;
	private boolean asynchronous;
	private String responseText;
	private Document responseXML;
	private int readyState = 0;
	private HttpUriRequest request;
	private HttpResponse response;

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
		if ("GET".equalsIgnoreCase(method)) {
			if (asynchronous) {
				asyncGet();
			}
			else {
				get();
			}
		}
		else if ("POST".equalsIgnoreCase(method)) {
			if (asynchronous) {
				asyncPost();
			}
			else {
				post();
			}
		}
		else {
			throw new UnsupportedOperationException(method);
		}
	}

	private void asyncGet() {
		callAsync(new Callable<Void>() {
			public Void call() throws Exception {
				get();
				return null;
			}
		});
	}

	private void asyncPost() {
		callAsync(new Callable<Void>() {
			public Void call() throws Exception {
				post();
				return null;
			}
		});
	}

	private static void callAsync(final Callable<Void> callable) {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					callable.call();
				}
				catch (Exception x) {
					LOGGER.error("Exception when executing asynchronously.", x);
				}
			}
		});
		thread.start();
	}
	
	private void post() throws IOException {
		request = new HttpPost(url);
		execute();
	}
	
	private void get() throws IOException {
		request = new HttpGet(url);
		execute();
	}

	private void execute() throws IOException {
		notNull(request);
		setReadyState(1);
		response = client.execute(request);
		setReadyState(2);
		HttpEntity entity = response.getEntity();
		setReadyState(3);
		InputStream istream = null;
		try {
			responseText = IOUtils.toString(istream = entity.getContent());
			try {
				responseXML = parserFactory.create().parse(responseText, url);
			}
			catch (Exception x) {
				LOGGER.error("XML parsing error", x);
				responseXML = null;
			}
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
		final ExtendedEvent event = eventDispatcher.createEvent("Event");
		event.initEvent("readystatechange", false, false);
		event.setTarget(this);
		eventDispatcher.dispatchEvent(event);
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
		return responseXML;
	}

	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {

		private ApacheCommonsXMLHttpRequest request = new ApacheCommonsXMLHttpRequest();

		public ApacheCommonsXMLHttpRequest build() {
			ApacheCommonsXMLHttpRequest request = this.request;
			this.request = null;
			return request;
		}
		
		public Builder client(final HttpClient client) {
			request().client = client;
			return this;
		}

		public Builder eventDispatcher(final EventDispatcher eventDispatcher) {
			request().eventDispatcher = eventDispatcher;
			return this;
		}

		public Builder parserFactory(final Factory<? extends HTMLParser> parserFactory) {
			request().parserFactory = parserFactory;
			return this;
		}

		private ApacheCommonsXMLHttpRequest request() {
			if (request == null) {
				request = new ApacheCommonsXMLHttpRequest();
			}
			return request;
		}
	}

	public void addEventListener(String type, EventListener listener, boolean useCapture) {
		eventDispatcher.addEventListener(this, type, listener, useCapture);
	}

	public void removeEventListener(String type, EventListener listener, boolean useCapture) {
		eventDispatcher.removeEventListener(this, type, listener, useCapture);
	}

	public boolean dispatchEvent(Event event) throws EventException {
		return eventDispatcher.dispatchEvent(event);
	}

	public EventListener getOnreadystatechange() {
		return onreadystatechange;
	}

	public void setOnreadystatechange(EventListener onreadystatechange) {
		this.onreadystatechange = onreadystatechange;
		eventDispatcher.addEventListener(this, "readystatechange", onreadystatechange, false);
	}
}