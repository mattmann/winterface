package com.github.snoblind.winterface.rhino;

import java.util.Arrays;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.apache.commons.lang.Validate.isTrue;
import static org.apache.commons.lang.Validate.notNull;

public class CallMethodFunction extends AbstractFunction {

	private static final Logger LOGGER = LoggerFactory.getLogger(CallMethodFunction.class);

	private final MethodFunction f1;

	public CallMethodFunction(MethodFunction methodFunction) {
		notNull(methodFunction);
		this.f1 = methodFunction;
	}

	public Object call(Context context, Scriptable scope, Scriptable thisObject, Object[] args) {
		LOGGER.debug("call({}, {}, {}, {})", context, scope, thisObject, Arrays.toString(args));
		isTrue(thisObject == f1);
		MethodFunction f2 = new MethodFunction(args[0], f1.getMethod());
		return f2.call(context, scope, (Scriptable)args[0], Arrays.copyOfRange(args, 1, args.length));
	}
}