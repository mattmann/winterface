package com.github.snoblind.winterface.nashorn;

import com.github.snoblind.winterface.Window;
import com.github.snoblind.winterface.WindowEnvironment;
import java.io.IOException;

public class NashornWindowEnvironment implements WindowEnvironment {

	public Window open(String url, String target, String features, boolean replace) throws IOException {
		throw new UnsupportedOperationException();
	}
}
