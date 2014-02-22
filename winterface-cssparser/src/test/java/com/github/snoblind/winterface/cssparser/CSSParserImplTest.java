package com.github.snoblind.winterface.cssparser;

import com.steadystate.css.dom.CSSStyleDeclarationImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.w3c.dom.Element;
import org.w3c.dom.css.CSSValue;
import org.w3c.dom.css.CSSValueList;
import static com.github.snoblind.winterface.mock.MockitoAnnotations.initMocks;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.w3c.dom.css.CSSValue.CSS_PRIMITIVE_VALUE;

public class CSSParserImplTest {

	private static final String STYLE = "background-color: white; color: black; font-style: normal !important; font-weight: bold; border: 1px solid gray;";

	private CSSParserImpl parser;

	@Mock private Element element;

	@Before
	public void setUp() {
		initMocks(this);
		parser = new CSSParserImpl();
		doReturn(STYLE).when(element).getAttribute("style");
	}

	@Test
	public void parseStyle() {
		final CSSStyleDeclarationImpl style = (CSSStyleDeclarationImpl) parser.parseStyle(element);
		assertEquals(5, style.getLength());
		
		final CSSValue value1 = style.getPropertyCSSValue("background-color");
		assertEquals(CSS_PRIMITIVE_VALUE, value1.getCssValueType());
		assertEquals("white", value1.getCssText());
		
		final CSSValue value2 = style.getPropertyCSSValue("color");
		assertEquals(CSS_PRIMITIVE_VALUE, value2.getCssValueType());
		assertEquals("black", value2.getCssText());

		final CSSValue value3 = style.getPropertyCSSValue("font-style");
		assertEquals(CSS_PRIMITIVE_VALUE, value3.getCssValueType());
		assertEquals("normal", value3.getCssText());
		assertEquals("important", style.getPropertyPriority("font-style"));

		final CSSValue value4 = style.getPropertyCSSValue("font-weight");
		assertEquals(CSS_PRIMITIVE_VALUE, value4.getCssValueType());
		assertEquals("bold", value4.getCssText());

		final CSSValueList value5 = (CSSValueList) style.getPropertyCSSValue("border");
		assertEquals(3, value5.getLength());
		assertEquals("1px", value5.item(0).getCssText());
		assertEquals("solid", value5.item(1).getCssText());
		assertEquals("gray", value5.item(2).getCssText());
	}
}
