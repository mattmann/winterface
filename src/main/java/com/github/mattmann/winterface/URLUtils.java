package com.github.mattmann.winterface;

public interface URLUtils {
	CharSequence getProtocol();
	void setProtocol(CharSequence protocol);

	CharSequence getHost();
	void setHost(CharSequence host);

	CharSequence getHostname();
	void setHostname(CharSequence hostname);

	CharSequence getPort();
	void setPort(CharSequence port);

	CharSequence getPathname();
	void setPathname(CharSequence pathname);

	CharSequence getSearch();
	void setSearch(CharSequence search);

	CharSequence getHash();
	void setHash(CharSequence hash);
}
