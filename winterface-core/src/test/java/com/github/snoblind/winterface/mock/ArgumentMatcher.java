package com.github.snoblind.winterface.mock;

import static org.apache.commons.lang.Validate.notNull;

public abstract class ArgumentMatcher<T> extends org.mockito.ArgumentMatcher<T> {

	private final Class<T> argumentType;
	
	public ArgumentMatcher(Class<T> argumentType) {
		notNull(argumentType);
		this.argumentType = argumentType;
	}

	@SuppressWarnings("unchecked")
	public boolean matches(Object argument) {
		if (argument == null) {
			return false;
		}
		if (argumentType.isAssignableFrom(argument.getClass())) {
			return argumentMatches((T) argument);
		}
		return false;
	}

	protected abstract boolean argumentMatches(T argument);
}
