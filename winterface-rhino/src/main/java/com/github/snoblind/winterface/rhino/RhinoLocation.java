package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.Location;
import com.github.snoblind.winterface.XMLHttpRequest;
import com.github.snoblind.winterface.spi.HTMLParser;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.w3c.dom.Document;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.apache.commons.lang.StringUtils.defaultString;
import static org.apache.commons.lang.Validate.isTrue;
import static org.apache.commons.lang.Validate.notNull;

public class RhinoLocation implements Location {

	private final RhinoWindow window;
	private String href;
	
	public RhinoLocation(final RhinoWindow window) {
		notNull(this.window = window);
		window.setLocation(this);
	}
	
	public String getProtocol() {
		try {
			return String.format("%s:", new URI(href).getScheme());
		}
		catch (URISyntaxException x) {
			return EMPTY;
		}
	}

	public void setProtocol(String protocol) {
		try {
			int index = protocol.indexOf(':');
			if (index > 0) {
				protocol = protocol.substring(0, index);
			}
			assign(new URIBuilder().uri(new URI(href)).scheme(protocol).build().toASCIIString());
		}
		catch (URISyntaxException x) {
		}
	}

	public String getHost() {
		try {
			return new URI(href).getHost();
		}
		catch (URISyntaxException x) {
			return EMPTY;
		}
	}

	public void setHost(String host) {
		try {
			assign(new URIBuilder().uri(new URI(href)).host(host).build().toASCIIString());
		}
		catch (URISyntaxException x) {
		}
	}

	public String getHostname() {
		return getHost();
	}

	public void setHostname(String hostname) {
		setHost(hostname);
	}

	public String getPort() {
		try {
			final int port = new URI(href).getPort();
			if (port < 0) {
				return EMPTY;
			}
			return Integer.toString(port);
		}
		catch (URISyntaxException x) {
			return EMPTY;
		}
	}

	public void setPort(String port) {
		try {
			assign(new URIBuilder().uri(new URI(href)).port(Integer.parseInt(port)).build().toASCIIString());
		}
		catch (URISyntaxException x) {
		}
	}

	public String getPathname() {
		try {
			return new URI(href).getPath();
		}
		catch (URISyntaxException x) {
			return EMPTY;
		}
	}

	public void setPathname(String pathname) {
		try {
			assign(new URIBuilder().uri(new URI(href)).path(pathname).build().toASCIIString());
		}
		catch (URISyntaxException x) {
		}
	}

	public String getSearch() {
		try {
			return defaultString(new URI(href).getQuery());
		}
		catch (URISyntaxException x) {
			return EMPTY;
		}
	}

	public void setSearch(String search) {
		try {
			assign(new URIBuilder().uri(new URI(href)).query(search).build().toASCIIString());
		}
		catch (URISyntaxException x) {
		}
	}

	public String getHash() {
		try {
			return defaultString(new URI(href).getFragment());
		}
		catch (URISyntaxException x) {
			return EMPTY;
		}
	}

	public void setHash(String hash) {
		try {
			href = new URIBuilder().uri(new URI(href)).fragment(hash).build().toASCIIString();
		}
		catch (URISyntaxException x) {
		}
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		assign(href);
	}

	protected void submit(String method, String action, String data) {
		final XMLHttpRequest request = window.getXmlHttpRequestFactory().create();
		request.open(method, action, false, null, null);
		try {
			request.send(data);
		}
		catch (IOException x) {
			throw new RuntimeException(x);
		}
		isTrue(4 == request.getReadyState());
		href = action;
		window.setDocument(RhinoDocument.builder()
				.document(request.getResponseXML())
				.eventDispatcher(window.getEventDispatcher())
				.parser(window.getParserFactory().create())
				.querySelector(window.getQuerySelector())
				.build());
		window.evalDocument();
	}

	private void assign(final URL url) throws IOException {
		final XMLHttpRequest request = window.getXmlHttpRequestFactory().create();
		request.open("GET", url.toString(), false, null, null);
		request.send(null);
		isTrue(4 == request.getReadyState());
		href = url.toString();
		window.setDocument(RhinoDocument.builder()
				.document(request.getResponseXML())
				.eventDispatcher(window.getEventDispatcher())
				.parser(window.getParserFactory().create())
				.querySelector(window.getQuerySelector())
				.build());
		window.evalDocument();
	}

	private URL toURL(final String url) throws MalformedURLException {
		try {
			return new URL(url);
		}
		catch (MalformedURLException x) {
			if (x.getMessage().startsWith("no protocol: ")) {
				return new URL(String.format("http://%s", url));
			}
			throw x;
		}
	}
	
	public void assign(final String url) {
		if ("about:blank".equals(url)) {
			final HTMLParser parser = window.getParserFactory().create();
			final Document document = parser.parse("<html/>", url);
			window.setDocument(RhinoDocument.builder()
					.document(document)
					.eventDispatcher(window.getEventDispatcher())
					.parser(parser)
					.querySelector(window.getQuerySelector())
					.build());
			href = url;
		}
		else {
			try {
				assign(toURL(url));
			}
			catch (IOException x) {
				throw new RuntimeException(x);
			}
		}
	}

	public void replace(String url) {
		assign(url);
	}

	public void reload() {
		assign(href);
	}
}