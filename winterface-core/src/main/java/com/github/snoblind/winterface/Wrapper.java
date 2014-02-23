package com.github.snoblind.winterface;

public interface Wrapper {
	
	boolean isWrapperFor(Class<?> type);

	<T> T unwrap(Class<T> type);
}
