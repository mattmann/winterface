package com.github.mattmann.winterface.rhino;

import java.io.PrintStream;
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

	public void log(Object value) {
		out.println(value);
	}
}