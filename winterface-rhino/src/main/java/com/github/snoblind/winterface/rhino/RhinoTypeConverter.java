package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.EventListener;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.apache.commons.lang.Validate.isTrue;
import static org.apache.commons.lang.Validate.notNull;

public class RhinoTypeConverter {

	private static final Logger LOGGER = LoggerFactory.getLogger(RhinoTypeConverter.class);

	public static RhinoTypeConverter getInstance() {
		return INSTANCE;
	}
	
	private static final RhinoTypeConverter INSTANCE = new RhinoTypeConverter();
	
	private RhinoTypeConverter() {
	}
	
	public Object convert(final Context context, final Scriptable scope, final Scriptable thisObject, final Object argument, final Class<?> type) {
		notNull(argument);
		notNull(type);
		LOGGER.debug("convert({}({}), {})", argument.getClass().getName(), argument, type.getName());
		if (type.isAssignableFrom(argument.getClass())) {
			return argument;
		}
		if (String.class.equals(type) && argument instanceof CharSequence) {
			return argument.toString();
		}
		if (Integer.TYPE.equals(type) && argument instanceof Number) {
			return ((Number)argument).intValue();
		}
		if (argument instanceof Function && EventListener.class.equals(type)) {
			return new FunctionEventListener(context, scope, thisObject, (Function) argument);
		}
		if (argument instanceof Boolean && boolean.class.equals(type)) {
			return ((Boolean) argument).booleanValue();
		}
		throw new UnsupportedOperationException(String.format("convert(%s(%s), %s)", argument.getClass().getName(), argument, type.getName()));
	}

	public boolean convert(final Context context, final Scriptable scope, final Scriptable thisObject, final Object[] arguments, final Class<?>[] types) {
		notNull(arguments);
		notNull(types);
		isTrue(arguments.length == types.length);
		boolean modified = false;
		for (int i = 0; i < arguments.length; i++) {
			final Object value1 = arguments[i];
			final Object value2 = convert(context, scope, thisObject, arguments[i], types[i]);
			if (value2 != value1) {
				modified = true;
				arguments[i] = value2;
			}
		}
		return modified;
	}
}
