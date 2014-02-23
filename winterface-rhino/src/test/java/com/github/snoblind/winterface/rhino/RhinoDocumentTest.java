package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.ExtendedHTMLDocument;
import com.github.snoblind.winterface.ExtendedHTMLElement;
import com.github.snoblind.winterface.event.EventDispatcher;
import com.github.snoblind.winterface.spi.HTMLParser;
import com.github.snoblind.winterface.spi.QuerySelector;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.w3c.dom.html.HTMLTitleElement;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.doReturn;
import static com.github.snoblind.winterface.mock.MockitoAnnotations.initMocks;

public class RhinoDocumentTest {

	private static final Answer<Object> ANSWER = new Answer<Object>() {
		public Object answer(InvocationOnMock invocation) throws Throwable {
			final Method method = invocation.getMethod();
			final Object[] arguments = invocation.getArguments();
			if (arguments.length == 0) {
				if (method.getName().equals("finalize")) {
					return null;
				}
				if (method.getName().equals("toString")) {
					return invocation.getMock().getClass().getName();
				}
			}
			System.err.println(invocation.getMethod());
			System.err.println(Arrays.toString(invocation.getArguments()));
			throw new UnsupportedOperationException(invocation.getMethod().toString());
		}
	};
	
	private RhinoDocument rhinoDocument;

	@Mock private EventDispatcher eventDispatcher;
	@Mock private ExtendedHTMLDocument document;
	@Mock private HTMLParser parser;
	@Mock private QuerySelector querySelector;
	@Mock private ExtendedHTMLElement bodyElement;
	@Mock private HTMLTitleElement titleElement;

	@Before
	public void setUp() {
		initMocks(this, ANSWER);
		rhinoDocument = RhinoDocument.builder()
				.document(document)
				.eventDispatcher(eventDispatcher)
				.parser(parser)
				.querySelector(querySelector)
				.build();
		doReturn(bodyElement).when(querySelector).querySelector(rhinoDocument, "html > body");
		doReturn(titleElement).when(querySelector).querySelector(rhinoDocument, "html > head > title");
		doReturn("Title").when(titleElement).getText();
	}

	@Test
	public void getBody() {
		assertSame(bodyElement, rhinoDocument.getBody());
	}

	@Test
	public void getTitle() {
		assertEquals("Title", rhinoDocument.getTitle());
	}
}
