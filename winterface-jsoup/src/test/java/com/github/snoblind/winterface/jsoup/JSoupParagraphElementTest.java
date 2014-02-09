package com.github.snoblind.winterface.jsoup;

import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import com.github.snoblind.winterface.jsoup.JSoupDocument;
import com.github.snoblind.winterface.jsoup.JSoupParagraphElement;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class JSoupParagraphElementTest {

	private static final Answer<Object> ANSWER = new Answer<Object>() {
		public Object answer(InvocationOnMock invocation) throws Throwable {
			throw new UnsupportedOperationException(invocation.getMethod().toString());
		}
	};

	private JSoupParagraphElement paragraph;

	@Mock private Element element;
	@Mock private JSoupDocument ownerDocument;

	@Before
	public void setUp() {
		initMocks(this);
		element = mock(Element.class, ANSWER);
		ownerDocument = mock(JSoupDocument.class, ANSWER);
		paragraph = new JSoupParagraphElement(element, ownerDocument);
		doReturn(element).when(element).attr("align", "center");
		doReturn("center").when(element).attr("align");
	}
	
	@Test
	public void setAlign() {
		paragraph.setAlign("center");
		paragraph.getAlign();
		verify(element).attr("align", "center");
		verify(element).attr("align");
	}
}
