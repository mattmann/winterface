package com.github.snoblind.winterface.xmlhttp;

import com.github.snoblind.winterface.Event;
import com.github.snoblind.winterface.EventListener;
import com.github.snoblind.winterface.XMLHttpRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.collections4.Factory;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import static org.apache.commons.lang.Validate.notNull;

public class ApacheCommonsXMLHttpRequest implements XMLHttpRequest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApacheCommonsXMLHttpRequest.class);

	private final HttpClient client;
	private final Factory<? extends Event> eventFactory;
	private final DocumentBuilderFactory documentBuilderFactory;

	public ApacheCommonsXMLHttpRequest(final HttpClient client, final Factory<? extends Event> eventFactory, final DocumentBuilderFactory documentBuilderFactory) {
		notNull(this.client = client);
		notNull(this.eventFactory = eventFactory);
		notNull(this.documentBuilderFactory = documentBuilderFactory);
	}

	public ApacheCommonsXMLHttpRequest(final HttpClient client, final Factory<? extends Event> eventFactory) {
		this(client, eventFactory, DocumentBuilderFactory.newInstance());
	}

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
					LOGGER.error("I/O exception when executing asynchronously.", x);
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
			try {
				responseXML = documentBuilderFactory.newDocumentBuilder().parse(new InputSource(new StringReader(responseText)));
			}
			catch (ParserConfigurationException x) {
				LOGGER.error("XML parsing error", x);
				responseXML = null;
			}
			catch (SAXException x) {
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
		fireReadyStateChanged();
	}

	private void fireReadyStateChanged() {
		if (onreadystatechange != null) {
			onreadystatechange.handleEvent(eventFactory.create());
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
		return responseXML;
	}

	public EventListener getOnreadystatechange() {
		return onreadystatechange;
	}

	public void setOnreadystatechange(EventListener onreadystatechange) {
		this.onreadystatechange = onreadystatechange;
	}
}