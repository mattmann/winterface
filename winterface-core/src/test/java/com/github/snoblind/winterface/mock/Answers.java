package com.github.snoblind.winterface.mock;

import java.util.Arrays;
import java.lang.reflect.Method;
import org.mockito.stubbing.Answer;
import org.mockito.invocation.InvocationOnMock;

public final class Answers {

	private Answers() {
	}
	
	public static final Answer<Object> ANSWER_UNSUPPORTED = new Answer<Object>() {
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
			System.err.println(method);
			System.err.println(Arrays.toString(arguments));
			throw new UnsupportedOperationException(method.toString());
		}
	};
}
