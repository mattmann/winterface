package com.github.mattmann.winterface.jsoup;

import com.github.mattmann.winterface.Location;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import org.jsoup.Connection;
import static org.apache.commons.lang.Validate.notNull;

public class JSoupLocation implements Location {

	protected final Set<PropertyChangeListener> listeners = new HashSet<PropertyChangeListener>();
	protected final Connection connection;

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

	public void setHref(CharSequence href) {
		try {
			final Object oldValue = getHref();
			final Object newValue = href;
			connection.request().url(new URL(href.toString()));
			firePropertyChangeEvent("href", oldValue, newValue);
		}
		catch (IOException x) {
			throw new RuntimeException(x);
		}
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
		if (!listeners.isEmpty()) {
			firePropertyChangeEvent(new PropertyChangeEvent(this, propertyName, oldValue, newValue));
		}
	}

	protected void firePropertyChangeEvent(PropertyChangeEvent event) {
		for (PropertyChangeListener listener: listeners) {
			listener.propertyChange(event);
		}
	}
	
	public boolean addPropertyChangeListener(PropertyChangeListener listener) {
		return listeners.add(listener);
	}

	public boolean removePropertyChangeListener(PropertyChangeListener listener) {
		return listeners.remove(listener);
	}
}
