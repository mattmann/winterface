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

	public CharSequence getProtocol() {
		throw new UnsupportedOperationException();
	}

	public void setProtocol(CharSequence protocol) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getHost() {
		throw new UnsupportedOperationException();
	}

	public void setHost(CharSequence host) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getHostname() {
		throw new UnsupportedOperationException();
	}

	public void setHostname(CharSequence hostname) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getPort() {
		throw new UnsupportedOperationException();
	}

	public void setPort(CharSequence port) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getPathname() {
		throw new UnsupportedOperationException();
	}

	public void setPathname(CharSequence pathname) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getSearch() {
		throw new UnsupportedOperationException();
	}

	public void setSearch(CharSequence search) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getHash() {
		throw new UnsupportedOperationException();
	}

	public void setHash(CharSequence hash) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getHref() {
		return connection.request().url().toExternalForm();
	}

	private void setURL(URL url) throws IOException {
		CharSequence oldValue = getHref();
		connection.request().url(url);
		CharSequence newValue = getHref();
		firePropertyChangeEvent("href", oldValue, newValue);
		Response response = connection.execute();
		fireResponseReceived(response);
		oldValue = newValue;
		newValue = getHref();
		firePropertyChangeEvent("href", oldValue, newValue);
		Document document = response.parse();
		fireDocumentParsed(document);
	}

	public void setHref(CharSequence href) {
		try {
			setURL(new URL(href.toString()));
		}
		catch (IOException x) {
			throw new RuntimeException(x);
		}
	}

	protected void submit(final CharSequence action, final CharSequence method, final List<KeyVal> keyVals) throws IOException {
		URL context = connection.request().url();
		URL url = isBlank(action.toString()) ? context : new URL(context, action.toString());
		Request request = connection.request();
		request.method(isBlank(method.toString()) ? Method.GET : Method.valueOf(method.toString().toUpperCase()));
		for (KeyVal keyVal: keyVals) {
			request.data(keyVal);
		}
		setURL(url);
	}

	public void assign(CharSequence url) {
		throw new UnsupportedOperationException();
	}

	public void replace(CharSequence url) {
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
