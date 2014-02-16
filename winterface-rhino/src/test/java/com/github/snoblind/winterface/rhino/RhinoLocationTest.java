package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.XMLHttpRequest;
import com.github.snoblind.winterface.event.EventDispatcher;
import com.github.snoblind.winterface.spi.HTMLParser;
import com.github.snoblind.winterface.spi.QuerySelector;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.w3c.dom.Document;
import static org.apache.commons.collections4.functors.ConstantFactory.constantFactory;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;

public class RhinoLocationTest {

	private RhinoLocation location;

	@Mock private Document document;
	@Mock private EventDispatcher eventDispatcher;
	@Mock private HTMLParser parser;
	@Mock private QuerySelector querySelector;
	@Mock private RhinoWindow window;
	@Mock private XMLHttpRequest request;
	
	@Before
	public void setUp() throws IOException {
		initMocks(this);
		doReturn(4).when(request).getReadyState();
		doReturn(constantFactory(parser)).when(window).getParserFactory();
		doReturn(constantFactory(request)).when(window).getXmlHttpRequestFactory();
		doReturn(document).when(request).getResponseXML();
		doReturn(eventDispatcher).when(window).getEventDispatcher();
		doReturn(querySelector).when(window).getQuerySelector();
		location = new RhinoLocation(window);
	}

	@Test
	public void protocol() throws IOException {
		location.setHref("https://github.com/snoblind/winterface");
		assertEquals("https:", location.getProtocol());
		location.setProtocol("ftp:");
		assertEquals("ftp://github.com/snoblind/winterface", location.getHref());
		verify(request).open("GET", "ftp://github.com/snoblind/winterface", false, null, null);
		verify(request).open("GET", "https://github.com/snoblind/winterface", false, null, null);
		verify(request, times(2)).getReadyState();
		verify(request, times(2)).getResponseXML();
		verify(request, times(2)).send(null);
		verify(window).setLocation(location);
		verify(window, times(2)).getEventDispatcher();
		verify(window, times(2)).getParserFactory();
		verify(window, times(2)).getQuerySelector();
		verify(window, times(2)).setDocument(any(RhinoDocument.class));
	}

	@Test
	public void host() throws IOException {
		location.setHref("https://github.com/snoblind/winterface");
		assertEquals("github.com", location.getHost());
		location.setHostname("snoblind.github.io");
		assertEquals("https://snoblind.github.io/snoblind/winterface", location.getHref());
		verify(request).open("GET", "https://github.com/snoblind/winterface", false, null, null);
		verify(request).open("GET", "https://snoblind.github.io/snoblind/winterface", false, null, null);
		verify(request, times(2)).getReadyState();
		verify(request, times(2)).getResponseXML();
		verify(request, times(2)).send(null);
		verify(window).setLocation(location);
		verify(window, times(2)).getEventDispatcher();
		verify(window, times(2)).getParserFactory();
		verify(window, times(2)).getQuerySelector();
		verify(window, times(2)).setDocument(any(RhinoDocument.class));
	}

	@Test
	public void port() throws IOException {
		location.setHref("https://github.com/snoblind/winterface");
		assertEquals(EMPTY, location.getPort());
		location.setPort("80");
		assertEquals("https://github.com:80/snoblind/winterface", location.getHref());
		verify(request).open("GET", "https://github.com/snoblind/winterface", false, null, null);
		verify(request).open("GET", "https://github.com:80/snoblind/winterface", false, null, null);
		verify(request, times(2)).getReadyState();
		verify(request, times(2)).getResponseXML();
		verify(request, times(2)).send(null);
		verify(window).setLocation(location);
		verify(window, times(2)).getEventDispatcher();
		verify(window, times(2)).getParserFactory();
		verify(window, times(2)).getQuerySelector();
		verify(window, times(2)).setDocument(any(RhinoDocument.class));
	}

	@Test
	public void pathname() throws IOException {
		location.setHref("https://github.com/snoblind/winterface");
		assertEquals("github.com", location.getHost());
		location.setPathname("/snoblind");
		assertEquals("https://github.com/snoblind", location.getHref());
		verify(request).open("GET", "https://github.com/snoblind", false, null, null);
		verify(request).open("GET", "https://github.com/snoblind/winterface", false, null, null);
		verify(request, times(2)).getReadyState();
		verify(request, times(2)).getResponseXML();
		verify(request, times(2)).send(null);
		verify(window).setLocation(location);
		verify(window, times(2)).getEventDispatcher();
		verify(window, times(2)).getParserFactory();
		verify(window, times(2)).getQuerySelector();
		verify(window, times(2)).setDocument(any(RhinoDocument.class));
	}

	@Test
	public void search() throws IOException {
		location.setHref("https://github.com/snoblind/winterface");
		assertEquals(EMPTY, location.getSearch());
		location.setSearch("n1=v1&n2=v2");
		assertEquals("https://github.com/snoblind/winterface?n1=v1&n2=v2", location.getHref());
		verify(request).open("GET", "https://github.com/snoblind/winterface", false, null, null);
		verify(request).open("GET", "https://github.com/snoblind/winterface?n1=v1&n2=v2", false, null, null);
		verify(request, times(2)).getReadyState();
		verify(request, times(2)).getResponseXML();
		verify(request, times(2)).send(null);
		verify(window).setLocation(location);
		verify(window, times(2)).getEventDispatcher();
		verify(window, times(2)).getParserFactory();
		verify(window, times(2)).getQuerySelector();
		verify(window, times(2)).setDocument(any(RhinoDocument.class));
	}
	
	@Test
	public void hash() throws IOException {
		location.setHref("https://github.com/snoblind/winterface");
		assertEquals(EMPTY, location.getHash());
		location.setHash("hash");
		assertEquals("https://github.com/snoblind/winterface#hash", location.getHref());
		verify(request).getReadyState();
		verify(request).getResponseXML();
		verify(request).open("GET", "https://github.com/snoblind/winterface", false, null, null);
		verify(request).send(null);
		verify(window).getEventDispatcher();
		verify(window).getParserFactory();
		verify(window).getQuerySelector();
		verify(window).setDocument(any(RhinoDocument.class));
		verify(window).setLocation(location);
	}

	@After
	public void tearDown() {
		verifyNoMoreInteractions(request);
		verifyZeroInteractions(document);
		verifyZeroInteractions(eventDispatcher);
		verifyZeroInteractions(parser);
		verifyZeroInteractions(querySelector);
	}
}