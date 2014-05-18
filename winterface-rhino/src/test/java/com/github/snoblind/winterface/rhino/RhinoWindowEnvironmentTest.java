package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.ExtendedHTMLCollection;
import com.github.snoblind.winterface.GlobalEventHandlers;
import com.github.snoblind.winterface.Navigator;
import com.github.snoblind.winterface.WindowEventHandlers;
import com.github.snoblind.winterface.XMLHttpRequest;
import com.github.snoblind.winterface.event.EventDispatcher;
import com.github.snoblind.winterface.event.ExtendedEvent;
import com.github.snoblind.winterface.mock.Answers;
import com.github.snoblind.winterface.spi.HTMLParser;
import com.github.snoblind.winterface.spi.QuerySelector;
import java.io.IOException;
import java.net.CookieStore;
import java.util.Timer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.w3c.dom.Document;
import static org.apache.commons.collections4.functors.ConstantFactory.constantFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static com.github.snoblind.winterface.mock.MockitoAnnotations.initMocks;

public class RhinoWindowEnvironmentTest {

	private static final String URL = "https://github.com/snoblind/winterface";

	private RhinoWindowEnvironment environment;

	@Mock private Console console;
	@Mock private CookieStore cookieStore;
	@Mock private Document responseXML;
	@Mock private ExtendedEvent event;
	@Mock private EventDispatcher eventDispatcher;
	@Mock private ExtendedHTMLCollection collection;
	@Mock private GlobalEventHandlers globalEventHandlers;
	@Mock private HTMLParser parser;
	@Mock private QuerySelector querySelector;
	@Mock private Timer timer;
	@Mock private WindowEventHandlers windowEventHandlers;
	@Mock private XMLHttpRequest xmlHttpRequest;
	@Mock private Navigator navigator;

	@Before
	public void setUp() throws IOException {
		initMocks(this, Answers.UNSUPPORTED);
		environment = RhinoWindowEnvironment.builder()
				.console(console)
				.cookieStore(cookieStore)
				.eventDispatcher(eventDispatcher)
				.globalEventHandlers(globalEventHandlers)
				.navigator(navigator)
				.parserFactory(constantFactory(parser))
				.querySelector(querySelector)
				.timer(timer)
				.windowEventHandlers(windowEventHandlers)
				.xmlHttpRequestFactory(constantFactory(xmlHttpRequest))
				.build();
		doReturn(responseXML).when(xmlHttpRequest).getResponseXML();
		doReturn(4).when(xmlHttpRequest).getReadyState();
		doNothing().when(xmlHttpRequest).open("GET", URL, false, null, null);
		doNothing().when(xmlHttpRequest).send(null);
		doReturn(event).when(eventDispatcher).createEvent("Event");
		doNothing().when(event).setTarget(any(RhinoWindow.class));
		doNothing().when(event).initEvent("load", true, true);
		doReturn(true).when(eventDispatcher).dispatchEvent(event);
		doReturn(collection).when(querySelector).querySelectorAll(any(RhinoDocument.class), eq("script"));
		doReturn(0).when(collection).getLength();
	}

	@Test
	public void open() throws IOException {
		final RhinoWindow window = environment.open(URL, null, null, false);
		assertNotNull(window);
		final RhinoDocument document = window.getDocument();
		assertSame(responseXML, document.node);
		assertEquals(URL, window.getLocation().getHref());
		verify(xmlHttpRequest).open("GET", URL, false, null, null);
		verify(xmlHttpRequest).send(null);
	}
}
