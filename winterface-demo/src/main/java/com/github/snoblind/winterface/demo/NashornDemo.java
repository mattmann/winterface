package com.github.snoblind.winterface.demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public final class NashornDemo {

	public static void main(String[] args) {
		final Map<String, Object> map = new HashMap<String, Object>();
		final Object[] array = new Object[10];
		
		final ScriptEngineManager manager = new ScriptEngineManager();
		for (ScriptEngineFactory factory: manager.getEngineFactories()) {
			System.out.printf("Name: %s, Language: %s %s, Names: %s, Mime-types: %s%n", factory.getEngineName(), factory.getLanguageName(), factory.getLanguageVersion(), factory.getNames(), factory.getMimeTypes());
		}
		final ScriptEngine engine = manager.getEngineByName("JavaScript");
		engine.getBindings(ScriptContext.ENGINE_SCOPE).put("object", array);
		try {
			final Object result = engine.eval("object.toString");
			System.out.println(result);
			System.out.println(Arrays.toString(array));
		}
		catch (ScriptException e) {
			e.printStackTrace();
		}
	}

}
