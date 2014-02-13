package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.Location;
import com.github.snoblind.winterface.XMLHttpRequest;
import java.io.IOException;
import static org.apache.commons.lang.Validate.isTrue;
import static org.apache.commons.lang.Validate.notNull;

public class RhinoLocation implements Location {

	private final RhinoWindow window;
	private String href;
	
	public RhinoLocation(final RhinoWindow window) {
		notNull(this.window = window);
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
		return href;
	}

	public void setHref(String href) {
		assign(href);
	}

	public void assign(final String url) {
		try {
			final XMLHttpRequest request = window.getXmlHttpRequestFactory().create();
			request.open("GET", url, false, null, null);
			request.send(null);
			isTrue(4 == request.getReadyState());
			href = url;
			window.setDocument(RhinoDocument.builder()
					.document(request.getResponseXML())
					.eventDispatcher(window.getEventDispatcher())
					.nodeAdapterFactory(window.getNodeAdapterFactory())
					.parser(window.getParserFactory().create())
					.build());
		}
		catch (IOException x) {
			throw new RuntimeException(x);
		}
	}

	public void replace(String url) {
		throw new UnsupportedOperationException();
	}

	public void reload() {
		throw new UnsupportedOperationException();
	}
}