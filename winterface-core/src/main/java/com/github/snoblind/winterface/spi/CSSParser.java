package com.github.snoblind.winterface.spi;

import org.w3c.dom.Element;
import org.w3c.dom.css.CSSStyleDeclaration;

public interface CSSParser {

	CSSStyleDeclaration parseStyle(Element element);
}
