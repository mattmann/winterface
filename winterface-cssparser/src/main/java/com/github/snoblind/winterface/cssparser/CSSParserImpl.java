package com.github.snoblind.winterface.cssparser;

import com.github.snoblind.winterface.ExtendedHTMLCollection;
import com.github.snoblind.winterface.ExtendedHTMLDocument;
import com.github.snoblind.winterface.UnmodifiableStyleSheetList;
import com.github.snoblind.winterface.spi.CSSParser;
import com.steadystate.css.parser.CSSOMParser;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.stylesheets.StyleSheet;
import org.w3c.dom.stylesheets.StyleSheetList;
import static org.apache.commons.lang.Validate.notNull;

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

	private StyleSheet parseStyleSheet(Element element) throws IOException {
		notNull(element);
		if (element.getTagName().equalsIgnoreCase("link")) {
			return parser.parseStyleSheet(new InputSource(element.getAttribute("href")), element, element.getAttribute("href"));
		}
		if (element.getTagName().equalsIgnoreCase("style")) {
			return parser.parseStyleSheet(new InputSource(new StringReader(element.getTextContent())), element, null);
		}
		throw new IllegalArgumentException(element.toString());
	}

	public StyleSheetList getStyleSheets(ExtendedHTMLDocument document) {
		final ExtendedHTMLCollection elements = document.querySelectorAll("link[rel=\"stylesheet\"], style");
		final List<StyleSheet> list = new ArrayList<StyleSheet>(elements.getLength());
		for (Node element: elements) {
			try {
				list.add(parseStyleSheet((Element) element));
			}
			catch (IOException x) {
				throw new RuntimeException(x);
			}
		}
		return new UnmodifiableStyleSheetList(list);
	}

	public CSSStyleDeclaration getOverrideStyle(Element element, String pseudoElement) {
		throw new UnsupportedOperationException();
	}
}
