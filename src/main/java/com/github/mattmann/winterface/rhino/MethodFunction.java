package com.github.mattmann.winterface.rhino;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.WrappedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.mattmann.winterface.rhino.ReflectionUtils.findMethod;
import static org.apache.commons.lang.Validate.isTrue;
import static org.apache.commons.lang.Validate.notNull;

public class MethodFunction extends AbstractFunction {

	private static final Logger logger = LoggerFactory.getLogger(MethodFunction.class);

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

	private static final Log LOG = LogFactory.getLog(MethodFunction.class);

	protected Log getLog() {
		return LOG;
	}

	public Method getMethod() {
		return method;
	}

	public Object get(String name, Scriptable start) {
		logger.debug("get({}, {})", name, start);
		isTrue(start == this);
		if ("call".equals(name)) {
			return new CallMethodFunction(this);
		}
		return super.get(name, start);
	}
	
	protected Object coerce(Object arg, Class<?> type) {
		notNull(arg);
		notNull(type);
		logger.debug("coerce({}, {}, {})", arg.getClass().getName(), arg, type.getName());
		if (type.isAssignableFrom(arg.getClass())) {
			return arg;
		}
		if (String.class.equals(type) && arg instanceof CharSequence) {
			return arg.toString();
		}
		throw new UnsupportedOperationException();
	}

	protected boolean coerce(Object[] args, Class<?>[] types) {
		notNull(args);
		notNull(types);
		isTrue(args.length == types.length);
		boolean modified = false;
		for (int i = 0; i < args.length; i++) {
			final Object value1 = args[i];
			final Object value2 = coerce(args[i], types[i]);
			if (value2 != value1) {
				modified = true;
				args[i] = value2;
			}
		}
		return modified;
	}

	public Object call(Context context, Scriptable scope, Scriptable thisObject, Object[] args) {
		final Class<?>[] parameterTypes = method.getParameterTypes();
		logger.debug("call({}, {}, {}, {})", context, scope, thisObject, Arrays.toString(args));
//		isTrue(thisObject == object);
		notNull(args);
		isTrue(args.length <= parameterTypes.length);
		if (args.length < parameterTypes.length) {
			List<Object> list = new ArrayList<Object>(parameterTypes.length);
			list.addAll(Arrays.asList(args));
			while (list.size() < parameterTypes.length) {
				list.add(null);
			}
			args = list.toArray(new Object[list.size()]);
		}
		try {
			return method.invoke(object, args);
		}
		catch (InvocationTargetException x) {
			throw new WrappedException(x);
		}
		catch (IllegalAccessException x) {
			throw new WrappedException(x);
		}
		catch (IllegalArgumentException x) {
			if (coerce(args, parameterTypes)) {
				return call(context, scope, thisObject, args);
			}
			if (getLog().isErrorEnabled()) {
				StringBuilder builder = new StringBuilder();
				builder.append("Arguments ");
				for (int i = 0; i < args.length; i++) {
					builder.append(args[i].getClass().getName()).append(" ").append(args[i]);
					if (i + 1 < args.length) {
						builder.append(", ");
					}
				}
				builder.append(" do not match method ").append(method);
				getLog().error(builder.toString(), x);
			}
			throw new WrappedException(x);
		}
	}
}