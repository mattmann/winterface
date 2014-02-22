package com.github.snoblind.winterface.cssparser;

import com.github.snoblind.winterface.spi.CSSParser;
import com.steadystate.css.parser.CSSOMParser;
import java.io.IOException;
import java.io.StringReader;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.Element;
import org.w3c.dom.css.CSSStyleDeclaration;

public class CSSParserImpl implements CSSParser {

	private final CSSOMParser parser = new CSSOMParser();
	
	public CSSStyleDeclaration parseStyle(Element element) {
		final String style = element.getAttribute("style");
		final InputSource inputSource = new InputSource(new StringReader(style));
		try {
			return parser.parseStyleDeclaration(inputSource);
		}
		catch (IOException x) {
			throw new RuntimeException(x);
		}
	}
}
