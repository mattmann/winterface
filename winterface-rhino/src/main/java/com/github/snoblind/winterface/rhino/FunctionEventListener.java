package com.github.snoblind.winterface.rhino;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import static org.apache.commons.lang.Validate.notNull;

public class FunctionEventListener implements EventListener {

	private final Context context;
	private final Scriptable scope;
	private final Scriptable thisObject;	
	private final Function function;
	
	public FunctionEventListener(final Context context, final Scriptable scope, final Scriptable thisObject, final Function function) {
		notNull(this.context = context);
		notNull(this.scope = scope);
		notNull(this.thisObject = thisObject);
		notNull(this.function = function);
	}

	public void handleEvent(Event event) {
		function.call(context, scope, thisObject, new Object[] { event });
	}
}