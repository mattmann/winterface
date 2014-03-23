package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.ExtendedHTMLCollection;
import com.github.snoblind.winterface.mock.Answers;
import com.github.snoblind.winterface.spi.QuerySelector;
import java.net.URLEncoder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLFormElement;
import org.w3c.dom.html.HTMLInputElement;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static com.github.snoblind.winterface.mock.MockitoAnnotations.initMocks;

public class RhinoFormElementTest {

	private RhinoFormElement form;

	@Mock private ExtendedHTMLCollection elements;
	@Mock private HTMLFormElement domElement;
	@Mock private QuerySelector querySelector;
	@Mock private RhinoDocument document;
	@Mock private RhinoLocation location;
	@Mock private RhinoWindow window;
	
	@Before
	public void setUp() {
		initMocks(this, Answers.ANSWER_UNSUPPORTED);
		form = new RhinoFormElement(domElement, document);
		doReturn(location).when(window).getLocation();
		doReturn(window).when(document).getDefaultView();
		doReturn(querySelector).when(document).getQuerySelector();
		doReturn(elements).when(querySelector).querySelectorAll(form, "button, datalist, input, keygen, select, output, textarea");
		doReturn("POST").when(domElement).getAttribute("method");
		doReturn("https://host.domain.com/path").when(domElement).getAttribute("action");
		doAnswer(new Answer<String>() {
			public String answer(InvocationOnMock invocation) throws Throwable {
				return URLEncoder.encode((String) invocation.getArguments()[0], "UTF-8");
			}
		}).when(window).encodeURIComponent(anyString());
		doAnswer(new Answer<Node>() {
			public Node answer(InvocationOnMock invocation) throws Throwable {
				return (Node) invocation.getArguments()[0];
			}
		}).when(document).adapt(any(Node.class));
		doNothing().when(location).submit(anyString(), anyString(), anyString());
	}

	@Test
	public void submit() {
		doReturn(2).when(elements).getLength();
		doReturn(mockInputElement("n1", "v1")).when(elements).item(0);
		doReturn(mockInputElement("n2", "v2")).when(elements).item(1);
		form.submit();
		verify(location).submit("POST", "https://host.domain.com/path", "n1=v1&n2=v2");
	}
	
	@Test
	public void getElements() {
		final HTMLInputElement inputElement = mockInputElement("name", "value");
		doReturn(1).when(elements).getLength();
		doReturn(inputElement).when(elements).item(0);
		final ExtendedHTMLCollection elements = form.getElements();
		assertEquals(1, elements.getLength());
		assertSame(inputElement, elements.item(0));
		verify(querySelector).querySelectorAll(form, "button, datalist, input, keygen, select, output, textarea");
		
	}

	private HTMLInputElement mockInputElement(String name, String value) {
		final HTMLInputElement inputElement = mock(HTMLInputElement.class, Answers.ANSWER_UNSUPPORTED);
		doReturn(name).when(inputElement).getName();
		doReturn(value).when(inputElement).getValue();		
		return inputElement;
	}
}
