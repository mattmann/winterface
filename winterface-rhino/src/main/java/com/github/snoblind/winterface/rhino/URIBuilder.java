package com.github.snoblind.winterface.rhino;

import java.net.URI;
import java.net.URISyntaxException;

public class URIBuilder {

	private String scheme;
	private String userInfo;
	private String host;
	private int port;
	private String path;
	private String query;
	private String fragment;

	public URI build() throws URISyntaxException {
		return new URI(scheme, userInfo, host, port, path, query, fragment);
	}

	public URIBuilder uri(URI uri) {
		return scheme(uri.getScheme())
				.userInfo(uri.getUserInfo())
				.host(uri.getHost())
				.port(uri.getPort())
				.path(uri.getPath())
				.query(uri.getQuery())
				.fragment(uri.getFragment());
	}

	public URIBuilder scheme(String scheme) {
		this.scheme = scheme;
		return this;
	}

	public URIBuilder userInfo(String userInfo) {
		this.userInfo = userInfo;
		return this;
	}

	public URIBuilder host(String host) {
		this.host = host;
		return this;
	}

	public URIBuilder port(int port) {
		this.port = port;
		return this;
	}

	public URIBuilder path(String path) {
		this.path = path;
		return this;
	}

	public URIBuilder query(String query) {
		this.query = query;
		return this;
	}

	public URIBuilder fragment(String fragment) {
		this.fragment = fragment;
		return this;
	}
}
