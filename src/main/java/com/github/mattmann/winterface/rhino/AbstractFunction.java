package com.github.mattmann.winterface.rhino;

import java.util.Arrays;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractFunction extends AbstractScriptable implements Function {

	private static final Logger logger = LoggerFactory.getLogger(AbstractFunction.class);

	public Scriptable construct(Context context, Scriptable scope, Object[] args) {
		logger.debug("construct({}, {}, {})", context, scope, Arrays.toString(args));
		throw new UnsupportedOperationException();
	}
}