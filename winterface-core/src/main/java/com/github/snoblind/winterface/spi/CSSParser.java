package com.github.snoblind.winterface.spi;

import com.github.snoblind.winterface.ExtendedHTMLDocument;
import org.w3c.dom.Element;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.stylesheets.StyleSheetList;

public interface CSSParser {

	CSSStyleDeclaration parseStyle(Element element);

	StyleSheetList getStyleSheets(ExtendedHTMLDocument document);

	CSSStyleDeclaration getOverrideStyle(Element element, String pseudoElement);
}
