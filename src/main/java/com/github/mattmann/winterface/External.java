package com.github.mattmann.winterface;

public interface External {
	void AddSearchProvider(String engineURL);
	long IsSearchProviderInstalled(String engineURL);
}
