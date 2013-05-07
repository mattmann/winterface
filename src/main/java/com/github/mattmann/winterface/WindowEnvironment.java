package com.github.mattmann.winterface;

import java.io.IOException;

public interface WindowEnvironment {
	Window open(String url, String target, String features, boolean replace) throws IOException;
}
