package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.Event;
import com.github.snoblind.winterface.EventListener;
import com.github.snoblind.winterface.EventTarget;
import com.github.snoblind.winterface.GlobalEventHandlers;
import com.github.snoblind.winterface.Location;
import com.github.snoblind.winterface.WindowEventHandlers;
import com.github.snoblind.winterface.XMLHttpRequest;
import com.github.snoblind.winterface.event.EventDispatcher;
import com.github.snoblind.winterface.spi.HTMLParser;
import com.github.snoblind.winterface.spi.JAXPHTMLParser;
import com.github.snoblind.winterface.spi.QuerySelector;
import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.commons.collections4.Factory;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Undefined;
import static com.github.snoblind.winterface.util.ClassPathResourceUtils.readString;
import static org.apache.commons.collections4.functors.ConstantFactory.constantFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
//import static com.github.snoblind.winterface.mock.MockitoAnnotations.initMocks;
import static org.mozilla.javascript.ScriptableObject.putProperty;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class RhinoWindowTest {

	private static final Answer<Object> ANSWER = new Answer<Object>() {
		public Object answer(InvocationOnMock invocation) throws Throwable {
			final java.lang.reflect.Method method = invocation.getMethod();
			final Object[] arguments = invocation.getArguments();
			if (arguments.length == 0) {
				if (method.getName().equals("finalize")) {
					return null;
				}
				if (method.getName().equals("toString")) {
					return invocation.getMock().getClass().getName();
				}
			}
			System.err.println(method);
			System.err.println(java.util.Arrays.toString(arguments));
			throw new UnsupportedOperationException(method.toString());
		}
	};

	private static final String JQUERY = readString(RhinoWindowTest.class, "/jquery-2.1.0.js");
	private static final String JAVASCRIPT = readString(RhinoWindowTest.class, "/RhinoWindowTest.js");
	
	private RhinoWindow window;
	private RhinoDocument document;
	private Console console;
	private HTMLParser parser;

	@Captor private ArgumentCaptor<EventListener> eventListenerCaptor;
	
	@Mock private EventDispatcher eventDispatcher;
	@Mock private Factory<XMLHttpRequest> xmlHttpRequestFactory;
	@Mock private GlobalEventHandlers globalEventHandlers;
	@Mock private Location location;
	@Mock private QuerySelector querySelector;
	@Mock private Timer timer;
	@Mock private WindowEventHandlers windowEventHandlers;
	@Mock private Event event;
	
	@Before
	public void setUp() throws IOException, ParserConfigurationException, TransformerException {
		initMocks(this);
		final URL url = getClass().getResource("/RhinoWindowTest.html");
		console = new PrintStreamConsole(System.out);
		parser = new JAXPHTMLParser();
		document = RhinoDocument.builder()
				.eventDispatcher(eventDispatcher)
				.document(parser.parse(IOUtils.toString(url), url.toExternalForm()))
				.querySelector(querySelector)
				.parser(parser)
				.build();
		window = RhinoWindow.builder()
				.console(console)
				.document(document)
				.eventDispatcher(eventDispatcher)
				.globalEventHandlers(globalEventHandlers)
				.location(location)
				.parserFactory(constantFactory(parser))
				.querySelector(querySelector)
				.timer(timer)
				.windowEventHandlers(windowEventHandlers)
				.xmlHttpRequestFactory(xmlHttpRequestFactory)
				.build();
        final Context context = Context.enter();
		context.initStandardObjects(window);
		putProperty(window, "window", window);
		doNothing().when(eventDispatcher).addEventListener(any(EventTarget.class), anyString(), any(EventListener.class), eq(false));
		doReturn(url.toExternalForm()).when(location).getHref();
	}

	@Test
	public void test() {
		assertSame(console, window.getConsole());
		assertSame(document, window.getDocument());
		assertSame(eventDispatcher, window.getEventDispatcher());
		assertSame(globalEventHandlers, window.getGlobalEventHandlers());
		assertSame(location, window.getLocation());
		assertSame(parser, window.getParserFactory().create());
		assertSame(timer, window.getTimer());
		assertSame(windowEventHandlers, window.getWindowEventHandlers());
		assertSame(window, window.getWindow());
		assertSame(xmlHttpRequestFactory, window.getXmlHttpRequestFactory());
		assertEquals(Undefined.instance, window.eval(JQUERY));
		assertEquals(0.0, window.eval(JAVASCRIPT));
		verify(eventDispatcher).addEventListener(any(RhinoDocument.class), eq("DOMContentLoaded"), eventListenerCaptor.capture(), eq(false));
		assertEquals("abcdefghijklmnopqrstuvwxyz", window.get("alphabet", null));
		eventListenerCaptor.getValue().handleEvent(event);
		assertEquals("ZYXWVUTSRQPONMLKJIHGFEDCBA", window.get("alphabet", null));
	}

	@After
	public void tearDown() {
		Context.exit();
	}
}
