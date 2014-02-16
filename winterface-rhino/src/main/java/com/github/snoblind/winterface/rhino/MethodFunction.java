package com.github.snoblind.winterface.rhino;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.WrappedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.github.snoblind.winterface.util.ReflectionUtils.findMethod;
import static org.apache.commons.lang.Validate.isTrue;
import static org.apache.commons.lang.Validate.notNull;

public class MethodFunction extends AbstractFunction {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodFunction.class);

	private final Object object;

	private final Method method;

	public MethodFunction(Object object, Method method) {
		notNull(object);
		notNull(method);
		this.object = object;
		this.method = method;
	}

	public MethodFunction(Object object, String methodName) {
		notNull(object);
		notNull(methodName);
		this.object = object;
		try {
			this.method = findMethod(object.getClass(), methodName);
		}
		catch (NoSuchMethodException x) {
			throw new IllegalArgumentException(x);
		}
	}

	public Method getMethod() {
		return method;
	}

	public Object get(String name, Scriptable start) {
		LOGGER.debug("get({}, {})", name, start);
		isTrue(start == this);
		if ("call".equals(name)) {
			return new CallMethodFunction(this);
		}
		return super.get(name, start);
	}

	public Object call(Context context, Scriptable scope, Scriptable thisObject, Object[] arguments) {
		final Class<?>[] parameterTypes = method.getParameterTypes();
		LOGGER.debug("call({}, {}, {}, {})", context, scope, thisObject, Arrays.toString(arguments));
//		isTrue(thisObject == object);
		notNull(arguments);
		isTrue(arguments.length <= parameterTypes.length);
		if (arguments.length < parameterTypes.length) {
			List<Object> list = new ArrayList<Object>(parameterTypes.length);
			list.addAll(Arrays.asList(arguments));
			while (list.size() < parameterTypes.length) {
				list.add(null);
			}
			arguments = list.toArray(new Object[list.size()]);
		}
		try {
			return method.invoke(object, arguments);
		}
		catch (InvocationTargetException x) {
			throw new WrappedException(x);
		}
		catch (IllegalAccessException x) {
			throw new WrappedException(x);
		}
		catch (IllegalArgumentException x) {
			if (RhinoTypeConverter.getInstance().convert(context, scope, thisObject, arguments, parameterTypes)) {
				return call(context, scope, thisObject, arguments);
			}
			if (LOGGER.isErrorEnabled()) {
				StringBuilder builder = new StringBuilder();
				builder.append("Arguments ");
				for (int i = 0; i < arguments.length; i++) {
					builder.append(arguments[i].getClass().getName()).append(" ").append(arguments[i]);
					if (i + 1 < arguments.length) {
						builder.append(", ");
					}
				}
				builder.append(" do not match method ").append(method);
				LOGGER.error(builder.toString(), x);
			}
			throw new WrappedException(x);
		}
	}
}