package com.github.snoblind.winterface.rhino;

import java.util.Arrays;
import java.util.TimerTask;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.apache.commons.lang.Validate.isTrue;
import static org.apache.commons.lang.Validate.notNull;

public class SetTimeoutFunction extends AbstractFunction {

	private static final Logger LOGGER = LoggerFactory.getLogger(SetTimeoutFunction.class);

	private final RhinoWindow window;

	public SetTimeoutFunction(RhinoWindow window) {
		notNull(window);
		this.window = window;
	}

	public Object call(Context context, final Scriptable scope, Scriptable thisObject, Object[] args) {
		LOGGER.debug("call({}, {}, {}, {})", context, scope, thisObject, Arrays.toString(args));
		isTrue(thisObject == window);
		isTrue(0 < args.length && args.length <= 2);
		final Number delay = args.length == 1 ? 0 : (Number)args[1];
		if (args[0] instanceof String) {
			return setTimeout((String)args[0], delay, scope);
		}
		if (args[0] instanceof Function) {
			return setTimeout((Function)args[0], delay, scope);
		}
		throw new IllegalArgumentException(String.valueOf(args[0]));
	}

	private TimerTask setTimeout(final String source, final Number delay, final Scriptable scope) {
		final TimerTask task = new TimerTask() {
			public void run() {
				Context context = Context.enter();
				context.evaluateString(scope, source, null, 0, null);
				Context.exit();
			}
		};
		window.timer.schedule(task, delay.longValue());
		return task;
	}

	private TimerTask setTimeout(final Function f, final Number delay, final Scriptable scope) {
		final TimerTask task = new TimerTask() {
			public void run() {
				Context context = Context.enter();
				f.call(context, scope, window, new Object[0]);
				Context.exit();
			}
		};
		window.timer.schedule(task, delay.longValue());
		return task;
	}
}
