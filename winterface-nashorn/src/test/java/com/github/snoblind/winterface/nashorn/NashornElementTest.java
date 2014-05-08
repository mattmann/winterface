package com.github.snoblind.winterface.nashorn;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static com.github.snoblind.winterface.mock.Answers.UNSUPPORTED;
import static com.github.snoblind.winterface.mock.MockitoAnnotations.initMocks;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.doReturn;

public class NashornElementTest {

	private NashornElement element;

	@Mock private NashornDocument document;
	
	@Before
	public void setUp() {
		initMocks(this, UNSUPPORTED);
		element = new NashornElement("span", document);
	}

	@Test
	public void getDefaultView() {
		doReturn(null).when(document).getDefaultView();
		assertNull(element.getDefaultView());
	}
	
	@Test
	public void getOwnerDocument() {
		assertSame(document, element.getOwnerDocument());
	}
}
