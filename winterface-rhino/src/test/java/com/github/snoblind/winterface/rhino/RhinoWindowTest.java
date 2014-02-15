package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.GlobalEventHandlers;
import com.github.snoblind.winterface.Location;
import com.github.snoblind.winterface.NodeAdapterFactory;
import com.github.snoblind.winterface.WindowEventHandlers;
import com.github.snoblind.winterface.XMLHttpRequest;
import com.github.snoblind.winterface.event.EventDispatcher;
import com.github.snoblind.winterface.spi.HTMLParser;
import com.github.snoblind.winterface.spi.QuerySelector;
import java.util.Timer;
import org.apache.commons.collections4.Factory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mozilla.javascript.Context;
import org.w3c.dom.Node;
import static com.github.snoblind.winterface.util.ClassPathResourceUtils.readString;
import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mozilla.javascript.ScriptableObject.putProperty;

public class RhinoWindowTest {

	private static final String JAVASCRIPT = readString(RhinoWindowTest.class, "/RhinoWindowTest.js");
	
	private RhinoWindow window;

	@Mock private Console console;
	@Mock private EventDispatcher eventDispatcher;
	@Mock private Factory<HTMLParser> parserFactory;
	@Mock private Factory<XMLHttpRequest> xmlHttpRequestFactory;
	@Mock private GlobalEventHandlers globalEventHandlers;
	@Mock private Location location;
	@Mock private NodeAdapterFactory<Node> nodeAdapterFactory;
	@Mock private QuerySelector querySelector;
	@Mock private RhinoDocument document;
	@Mock private Timer timer;
	@Mock private WindowEventHandlers windowEventHandlers;
	
	@Before
	public void setUp() {
		initMocks(this);
		doNothing().when(document).setDefaultView(any(RhinoWindow.class));
		window = RhinoWindow.builder()
				.console(console)
				.document(document)
				.eventDispatcher(eventDispatcher)
				.globalEventHandlers(globalEventHandlers)
				.location(location)
				.nodeAdapterFactory(nodeAdapterFactory)
				.parserFactory(parserFactory)
				.querySelector(querySelector)
				.timer(timer)
				.windowEventHandlers(windowEventHandlers)
				.xmlHttpRequestFactory(xmlHttpRequestFactory)
				.build();
        final Context context = Context.enter();
		context.initStandardObjects(window);
		putProperty(window, "window", window);
	}

	@Test
	public void test() {
		assertSame(console, window.getConsole());
		assertSame(document, window.getDocument());
		assertSame(eventDispatcher, window.getEventDispatcher());
		assertSame(globalEventHandlers, window.getGlobalEventHandlers());
		assertSame(location, window.getLocation());
		assertSame(nodeAdapterFactory, window.getNodeAdapterFactory());
		assertSame(parserFactory, window.getParserFactory());
		assertSame(timer, window.getTimer());
		assertSame(windowEventHandlers, window.getWindowEventHandlers());
		assertSame(window, window.getWindow());
		assertSame(xmlHttpRequestFactory, window.getXmlHttpRequestFactory());
		
		
		System.out.println(window.eval(JAVASCRIPT));
	}

	@After
	public void tearDown() {
		Context.exit();
	}
}
