package com.github.mattmann.winterface;

import java.io.IOException;

public interface WindowEnvironment {
	Window open(CharSequence url, CharSequence target, CharSequence features, boolean replace) throws IOException;
}
