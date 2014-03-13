package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.Event;
import com.github.snoblind.winterface.GlobalEventHandlers;
import com.github.snoblind.winterface.Navigator;
import com.github.snoblind.winterface.WindowEventHandlers;
import com.github.snoblind.winterface.XMLHttpRequest;
import com.github.snoblind.winterface.event.EventDispatcher;
import com.github.snoblind.winterface.event.EventImpl;
import com.github.snoblind.winterface.event.MapEventDispatcher;
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
import org.mockito.Mock;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Undefined;
import static com.github.snoblind.winterface.util.ClassPathResourceUtils.readString;
import static org.apache.commons.collections4.functors.ConstantFactory.constantFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mozilla.javascript.ScriptableObject.putProperty;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

public class RhinoWindowTest {

	private static final String JQUERY = readString(RhinoWindowTest.class, "/jquery-2.1.0.js");
	private static final String JAVASCRIPT = readString(RhinoWindowTest.class, "/RhinoWindowTest.js");
	
	private RhinoWindow window;
	private RhinoDocument document;
	private Console console;
	private HTMLParser parser;
	private EventDispatcher eventDispatcher;

	@Mock private Factory<XMLHttpRequest> xmlHttpRequestFactory;
	@Mock private GlobalEventHandlers globalEventHandlers;
	@Mock private RhinoLocation location;
	@Mock private QuerySelector querySelector;
	@Mock private Timer timer;
	@Mock private WindowEventHandlers windowEventHandlers;
	@Mock private Navigator navigator;

	@Before
	public void setUp() throws IOException, ParserConfigurationException, TransformerException {
		initMocks(this);
		final URL url = getClass().getResource("/RhinoWindowTest.html");
		eventDispatcher = new MapEventDispatcher();
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
				.navigator(navigator)
				.parserFactory(constantFactory(parser))
				.querySelector(querySelector)
				.timer(timer)
				.windowEventHandlers(windowEventHandlers)
				.xmlHttpRequestFactory(xmlHttpRequestFactory)
				.build();
        final Context context = Context.enter();
		context.initStandardObjects(window);
		putProperty(window, "window", window);
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
		assertEquals("abcdefghijklmnopqrstuvwxyz", window.get("alphabet", null));
		final Event event = new EventImpl();
		event.setTarget(document);
		event.initEvent("DOMContentLoaded", false, false);
		document.dispatchEvent(event);
		assertEquals("ZYXWVUTSRQPONMLKJIHGFEDCBA", window.get("alphabet", null));
	}

	@After
	public void tearDown() {
		Context.exit();
	}
}
