package com.github.mattmann.winterface.jsoup;

import com.github.mattmann.winterface.Location;
import org.jsoup.Connection;
import static org.apache.commons.lang.Validate.notNull;

public class JSoupLocation implements Location {
	
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
		throw new UnsupportedOperationException();
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
}
