package com.github.mattmann.winterface;

public interface External {
	void AddSearchProvider(CharSequence engineURL);
	long IsSearchProviderInstalled(CharSequence engineURL);
}
