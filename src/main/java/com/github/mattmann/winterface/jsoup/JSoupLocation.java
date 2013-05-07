package com.github.mattmann.winterface.jsoup;

import com.github.mattmann.winterface.Location;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.ObjectUtils;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.Validate.notNull;
import static org.jsoup.Connection.KeyVal;
import static org.jsoup.Connection.Request;
import static org.jsoup.Connection.Response;

public class JSoupLocation implements Location {

	private final Set<JSoupLocationListener> listeners = new HashSet<JSoupLocationListener>();
	private final Connection connection;

	public JSoupLocation(Connection connection) {
		notNull(this.connection = connection);
	}

	public String getProtocol() {
		throw new UnsupportedOperationException();
	}

	public void setProtocol(String protocol) {
		throw new UnsupportedOperationException();
	}

	public String getHost() {
		throw new UnsupportedOperationException();
	}

	public void setHost(String host) {
		throw new UnsupportedOperationException();
	}

	public String getHostname() {
		throw new UnsupportedOperationException();
	}

	public void setHostname(String hostname) {
		throw new UnsupportedOperationException();
	}

	public String getPort() {
		throw new UnsupportedOperationException();
	}

	public void setPort(String port) {
		throw new UnsupportedOperationException();
	}

	public String getPathname() {
		throw new UnsupportedOperationException();
	}

	public void setPathname(String pathname) {
		throw new UnsupportedOperationException();
	}

	public String getSearch() {
		throw new UnsupportedOperationException();
	}

	public void setSearch(String search) {
		throw new UnsupportedOperationException();
	}

	public String getHash() {
		throw new UnsupportedOperationException();
	}

	public void setHash(String hash) {
		throw new UnsupportedOperationException();
	}

	public String getHref() {
		return connection.request().url().toExternalForm();
	}

	private void setURL(URL url) throws IOException {
		String oldValue = getHref();
		connection.request().url(url);
		String newValue = getHref();
		firePropertyChangeEvent("href", oldValue, newValue);
		Response response = connection.execute();
		fireResponseReceived(response);
		oldValue = newValue;
		newValue = getHref();
		firePropertyChangeEvent("href", oldValue, newValue);
		Document document = response.parse();
		fireDocumentParsed(document);
	}

	public void setHref(String href) {
		try {
			setURL(new URL(href.toString()));
		}
		catch (IOException x) {
			throw new RuntimeException(x);
		}
	}

	protected void submit(final String action, final String method, final List<KeyVal> keyVals) throws IOException {
		URL context = connection.request().url();
		URL url = isBlank(action.toString()) ? context : new URL(context, action.toString());
		Request request = connection.request();
		request.method(isBlank(method.toString()) ? Method.GET : Method.valueOf(method.toString().toUpperCase()));
		for (KeyVal keyVal: keyVals) {
			request.data(keyVal);
		}
		setURL(url);
	}

	public void assign(String url) {
		throw new UnsupportedOperationException();
	}

	public void replace(String url) {
		throw new UnsupportedOperationException();
	}

	public void reload() {
		throw new UnsupportedOperationException();
	}

	protected void firePropertyChangeEvent(String propertyName, Object oldValue, Object newValue) {
		if (ObjectUtils.equals(oldValue, newValue) || listeners.isEmpty()) {
		}
		else {
			firePropertyChangeEvent(new PropertyChangeEvent(this, propertyName, oldValue, newValue));
		}
	}

	protected void fireResponseReceived(Response response) {
		if (!listeners.isEmpty()) {
			for (JSoupLocationListener listener: listeners) {
				listener.responseReceived(this, response);
			}
		}
	}

	protected void fireDocumentParsed(Document document) {
		if (!listeners.isEmpty()) {
			for (JSoupLocationListener listener: listeners) {
				listener.documentParsed(this, document);
			}
		}
	}

	protected void firePropertyChangeEvent(PropertyChangeEvent event) {
		for (PropertyChangeListener listener: listeners) {
			listener.propertyChange(event);
		}
	}
	
	public boolean addLocationListener(JSoupLocationListener listener) {
		return listeners.add(listener);
	}

	public boolean removeLocationListener(JSoupLocationListener listener) {
		return listeners.remove(listener);
	}
}
