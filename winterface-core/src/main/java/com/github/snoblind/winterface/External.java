package com.github.snoblind.winterface;

public interface External {
	void AddSearchProvider(String engineURL);
	long IsSearchProviderInstalled(String engineURL);
}
