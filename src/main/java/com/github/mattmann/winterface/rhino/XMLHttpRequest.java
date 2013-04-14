package com.github.mattmann.winterface.rhino;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.apache.commons.lang.Validate.notNull;

public class XMLHttpRequest extends ScriptableObject {

	private static final long serialVersionUID = 5611577738338105865L;

	private static final Logger LOGGER = LoggerFactory.getLogger(XMLHttpRequest.class);

	private final Context context;
	private final Scriptable scope;
	private final HttpClient client;

	public XMLHttpRequest(Context context, Scriptable scope, HttpClient client) {
		notNull(this.context = context);
		notNull(this.scope = scope);
		notNull(this.client = client);
	}

	public String getClassName() {
		return "XMLHttpRequest";
	}

	private String method;
	private String url;
	private boolean asynchronous;
	private String username;
	private String password;
	protected Function onreadystatechange;
	protected String responseText;
	protected int readyState = 0;
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

	private void setReadyState(int readyState) {
		this.readyState = readyState;
		fireReadyStateChanged();
	}

	/*
0: request not initialized 
1: server connection established
2: request received 
3: processing request 
4: request finished and response is ready
 */
	private void fireReadyStateChanged() {
		if (onreadystatechange != null) {
			onreadystatechange.call(context, scope, this, null);
		}
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

	public Integer getStatus() {
		if (response == null) {
			return null;
		}
		return response.getStatusLine().getStatusCode();
	}
}
