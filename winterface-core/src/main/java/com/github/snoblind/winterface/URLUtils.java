package com.github.snoblind.winterface;

public interface URLUtils {
	String getProtocol();
	void setProtocol(String protocol);

	String getHost();
	void setHost(String host);

	String getHostname();
	void setHostname(String hostname);

	String getPort();
	void setPort(String port);

	String getPathname();
	void setPathname(String pathname);

	String getSearch();
	void setSearch(String search);

	String getHash();
	void setHash(String hash);
}
