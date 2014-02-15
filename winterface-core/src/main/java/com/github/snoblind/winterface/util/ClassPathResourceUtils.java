package com.github.snoblind.winterface.util;

import java.io.IOException;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import static org.apache.commons.lang.Validate.notNull;

public final class ClassPathResourceUtils {

	private ClassPathResourceUtils() {
	}

	public static final String readString(final Class<?> loadingClass, final String resourcePath) {
		notNull(loadingClass);
		final URL url = loadingClass.getResource(resourcePath);
		notNull(url);
		try {
			return IOUtils.toString(url);
		}
		catch (IOException x) {
			throw new RuntimeException(x);
		}
	}
}
