package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.GlobalEventHandlers;
import com.github.snoblind.winterface.WindowEventHandlers;
import com.github.snoblind.winterface.XMLHttpRequest;
import com.github.snoblind.winterface.spi.HTMLParser;
import com.github.snoblind.winterface.spi.QuerySelector;
import java.io.IOException;
import java.util.Timer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.w3c.dom.Document;
import static org.apache.commons.collections4.functors.ConstantFactory.constantFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class RhinoWindowEnvironmentTest {

	private RhinoWindowEnvironment environment;

	@Mock private Console console;
	@Mock private Document responseXML;
	@Mock private GlobalEventHandlers globalEventHandlers;
	@Mock private HTMLParser parser;
	@Mock private QuerySelector querySelector;
	@Mock private Timer timer;
	@Mock private WindowEventHandlers windowEventHandlers;
	@Mock private XMLHttpRequest xmlHttpRequest;

	@Before
	public void setUp() throws IOException {
		initMocks(this);
		environment = RhinoWindowEnvironment.builder()
				.console(console)
				.globalEventHandlers(globalEventHandlers)
				.parserFactory(constantFactory(parser))
				.querySelector(querySelector)
				.timer(timer)
				.windowEventHandlers(windowEventHandlers)
				.xmlHttpRequestFactory(constantFactory(xmlHttpRequest))
				.build();
		doReturn(responseXML).when(xmlHttpRequest).getResponseXML();
		doReturn(4).when(xmlHttpRequest).getReadyState();
	}

	@Test
	public void test() throws IOException {
		final RhinoWindow window = environment.open("about:blank", null, null, false);
		assertNotNull(window);
		final RhinoDocument document = window.getDocument();
		assertSame(responseXML, document.node);
		assertEquals("about:blank", window.getLocation().getHref());
		verify(xmlHttpRequest).open("GET", "about:blank", false, null, null);
		verify(xmlHttpRequest).send(null);
	}
}
