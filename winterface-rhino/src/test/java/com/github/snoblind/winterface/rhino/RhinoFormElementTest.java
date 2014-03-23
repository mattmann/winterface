package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.ExtendedHTMLCollection;
import com.github.snoblind.winterface.mock.Answers;
import com.github.snoblind.winterface.spi.QuerySelector;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.w3c.dom.html.HTMLFormElement;
import org.w3c.dom.html.HTMLInputElement;
import static org.mockito.Mockito.doReturn;
import static com.github.snoblind.winterface.mock.MockitoAnnotations.initMocks;

public class RhinoFormElementTest {

	private RhinoFormElement form;

	@Mock private HTMLFormElement domElement;
	@Mock private RhinoDocument document;
	@Mock private QuerySelector querySelector;
	@Mock private HTMLInputElement inputElement;
	@Mock private ExtendedHTMLCollection elements;
	
	@Before
	public void setUp() {
		initMocks(this, Answers.ANSWER_UNSUPPORTED);
		form = new RhinoFormElement(domElement, document);
		doReturn(querySelector).when(document).getQuerySelector();
		doReturn(elements).when(querySelector).querySelectorAll(form, "button, datalist, input, keygen, select, output, textarea");
	}

	@Test
	public void test() {
//		elements.add(inputElement);
		final Object result = form.get("elements", form);
		System.out.println(result);
	}
}
