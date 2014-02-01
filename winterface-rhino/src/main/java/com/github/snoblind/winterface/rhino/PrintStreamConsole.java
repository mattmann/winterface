package com.github.snoblind.winterface.rhino;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.Map;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.NativeObject;
import static org.apache.commons.lang.Validate.notNull;

public class PrintStreamConsole implements Console {
	
	private final PrintStream out;

	public PrintStreamConsole(PrintStream out) {
		notNull(out);
		this.out = out;
	}

	public PrintStreamConsole() {
		this(System.out);
	}

	@SuppressWarnings("unchecked")
	public void log(Object value) {
		if (value instanceof String) {
			out.println(value);
		}
		else if (value instanceof NativeArray || value instanceof NativeJavaObject) {
			out.println(Context.jsToJava(value, String.class));
		}
		else if (value instanceof NativeObject) {
			out.println(toString((Map<String, Object>) value));
		}
		else {
			out.println(value.getClass().getName());
			out.println(value);
		}
	}

	private String toString(Map<String, Object> map) {
		StringBuilder builder = new StringBuilder("{");
		Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Object> entry = iterator.next();
			builder.append(entry.getKey()).append(": ").append(entry.getValue());
			if (iterator.hasNext()) {
				builder.append(", ");
			}
		}
		return builder.append("}").toString();
	}
}