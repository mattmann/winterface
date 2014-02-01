package com.github.snoblind.winterface.rhino;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import static org.apache.commons.lang.Validate.isTrue;
import static org.apache.commons.lang.Validate.notNull;

public class ForEachFunction extends AbstractFunction {

	private final Iterable<?> iterable;
	
	public ForEachFunction(Iterable<?> iterable) {
		notNull(iterable);
		this.iterable = iterable;
	}

	public Object call(Context context, Scriptable scope, Scriptable thisObject, Object[] args) {
		isTrue(args.length == 1);
		Function callback = (Function) args[0];
		int index = 0;
		for (Object value: iterable) {
			callback.call(context, scope, thisObject, new Object[] { value, index++ });
		}
		return null;
	}
}
