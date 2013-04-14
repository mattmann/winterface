package com.github.mattmann.winterface.rhino;

import java.io.IOException;

import org.apache.commons.lang.Validate;
import org.apache.http.client.HttpClient;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.Scriptable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActiveXObject extends IdScriptableObject {

	private static final long serialVersionUID = 3685364679909911760L;
	private static final Logger LOGGER = LoggerFactory.getLogger(ActiveXObject.class);
	private static final String TAG = "ActiveXObject";

	private final HttpClient client;

	public ActiveXObject(HttpClient client, String name) {
		Validate.notNull(this.client = client);
		Validate.isTrue("Microsoft.XMLHTTP".equals(name));
	}

	public String getClassName() {
		return "ActiveXObject";
	}

	protected int findPrototypeId(String name) {
		LOGGER.debug("findPrototypeId(\"{}\")", name);
		if ("constructor".equals(name)) {
			return 1;
		}
		if ("onreadystatechange".equals(name)) {
			return 2;
		}
		if ("open".equals(name)) {
			return 3;
		}
		if ("send".equals(name)) {
			return 4;
		}
		if ("responseText".equals(name)) {
			return 5;
		}
		if ("readyState".equals(name)) {
			return 6;
		}
		if ("status".equals(name)) {
			return 7;
		}
		throw new IllegalStateException(name);
	}

	protected void initPrototypeId(int id) {
		LOGGER.debug("initPrototypeId({})", id);
		switch (id) {
		case 1:
			initPrototypeMethod(TAG, id, "constructor", 1);
			break;
		case 2:
			initPrototypeValue(id, "onreadystatechange", null, 0);
			break;
		case 3:
			initPrototypeMethod(TAG, id, "open", 5);
			break;
		case 4:
			initPrototypeMethod(TAG, id, "send", 1);
			break;
		case 5:
			initPrototypeValue(id, "responseText", null, 0);
			break;
		case 6:
			initPrototypeValue(id, "readyState", null, 0);
			break;
		case 7:
			initPrototypeValue(id, "status", null, 0);
			break;
		default:
			throw new IllegalArgumentException(String.valueOf(id));
		}
	}

	public Object get(String name, Scriptable start) {
		LOGGER.debug("get({}, {})", name, start);
		if (start instanceof XMLHttpRequest) {
			final XMLHttpRequest request = (XMLHttpRequest)start;
			if ("responseText".equals(name)) {
				return request.responseText;
			}
			if ("readyState".equals(name)) {
				return request.readyState;
			}
			if ("status".equals(name)) {
				return request.getStatus();
			}
		}
		return super.get(name, start);
	}

	public void put(String name, Scriptable start, Object value) {
		LOGGER.debug("put({}, {}, {})", name, start, value);
		if (start instanceof XMLHttpRequest) {
			final XMLHttpRequest request = (XMLHttpRequest)start;
			if ("onreadystatechange".equals(name)) {
				request.onreadystatechange = (Function)value;
				return;
			}
		}
		super.put(name, start, value);
	}

	public Object execIdCall(IdFunctionObject f, Context context, Scriptable scope, Scriptable thisObj, Object[] args) {
		LOGGER.debug("execIdCall({}, {}, {}, {}, {})", f, context, scope, thisObj, args);
		if (args.length == 1 && "Microsoft.XMLHTTP".equals(args[0])) {
			return new XMLHttpRequest(context, scope, client);
		}
		if (f.methodId() == 3) {
			final XMLHttpRequest request = (XMLHttpRequest)thisObj;
			final String method = (String)args[0];
			final String url = (String)args[1];
			final boolean asynchronous = args.length > 2 ? (Boolean)args[2] : true;
			final String username = (String)(args.length > 3 ? args[3] : null);
			final String password = (String)(args.length > 4 ? args[4] : null);
			request.open(method, url, asynchronous, username, password);
			return null;
		}
		if (f.methodId() == 4) {
			final XMLHttpRequest request = (XMLHttpRequest)thisObj;
			final String data = args.length > 0 ? (String)args[0] : null;
			try {
				request.send(data);
			}
			catch (IOException x) {
				throw new RuntimeException(x);
			}
			return null;
		}
		throw new UnsupportedOperationException(String.valueOf(f.methodId()));
	}
}
