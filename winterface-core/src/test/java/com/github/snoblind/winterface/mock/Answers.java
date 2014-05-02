package com.github.snoblind.winterface.mock;

import java.util.Arrays;
import java.lang.reflect.Method;
import org.mockito.stubbing.Answer;
import org.mockito.invocation.InvocationOnMock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Answers {

	private static final Logger LOGGER = LoggerFactory.getLogger(Answers.class);
	
	private Answers() {
	}
	
	public static final Answer<Object> UNSUPPORTED = new Answer<Object>() {
		public Object answer(InvocationOnMock invocation) throws Throwable {
			final Method method = invocation.getMethod();
			final Object[] arguments = invocation.getArguments();
			if (arguments.length == 0) {
				if (method.getName().equals("finalize")) {
					return null;
				}
				if (method.getName().equals("toString")) {
					return invocation.getMock().getClass().getName();
				}
			}
			final String message = String.format("%s %s", method, Arrays.toString(arguments));
			LOGGER.error(message);
			throw new UnsupportedOperationException(message);
		}
	};
}
