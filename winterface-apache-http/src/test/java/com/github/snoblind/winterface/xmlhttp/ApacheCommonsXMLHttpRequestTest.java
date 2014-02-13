package com.github.snoblind.winterface.xmlhttp;

import com.github.snoblind.winterface.Event;
import com.github.snoblind.winterface.EventListener;
import com.github.snoblind.winterface.mock.ArgumentMatcher;
import com.github.snoblind.winterface.spi.HTMLParser;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.w3c.dom.html.HTMLDocument;
import org.xml.sax.SAXException;
import static org.apache.commons.collections4.functors.ConstantFactory.constantFactory;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

public class ApacheCommonsXMLHttpRequestTest {

	private static URL resource;
	private static String responseText;

	@BeforeClass
	public static void setUpClass() throws IOException {
		resource = ApacheCommonsXMLHttpRequestTest.class.getResource("/HTMLDocumentTest.html");
		responseText = IOUtils.toString(resource);
	}

	private ApacheCommonsXMLHttpRequest request;

	@Mock private Event event;
	@Mock private EventListener listener;
	@Mock private HTMLDocument document;
	@Mock private HTMLParser parser;
	@Mock private HttpClient client;
	@Mock private HttpEntity entity;
	@Mock private HttpResponse response;
	@Mock private StatusLine statusLine;
	
	@Before
	public void setUp() throws ClientProtocolException, IOException, ParserConfigurationException, SAXException {
		initMocks(this);
		request = ApacheCommonsXMLHttpRequest.builder()
				.client(client)
				.eventFactory(constantFactory(event))
				.parserFactory(constantFactory(parser))
				.build();
		doNothing().when(listener).handleEvent(event);
		doReturn(200).when(statusLine).getStatusCode();
		doReturn(entity).when(response).getEntity();
		doReturn(response).when(client).execute(any(HttpUriRequest.class));
		doReturn(statusLine).when(response).getStatusLine();
		doReturn(document).when(parser).parse(responseText, resource.toExternalForm());
		doAnswer(new Answer<InputStream>() {
			public InputStream answer(InvocationOnMock invocation) throws Throwable {
				return new ByteArrayInputStream(responseText.getBytes());
			}
		}).when(entity).getContent();
		assertEquals(-1, request.getStatus());
	}

	@Test
	public void send_GET_synchronous_Exception() throws SAXException, IOException {
		doThrow(new RuntimeException()).when(parser).parse(anyString(), anyString());
		request.open("GET", resource.toExternalForm(), true, EMPTY, EMPTY);
		request.send("GET");
		assertNull(request.getResponseXML());
		verifyZeroInteractions(client);
		verifyZeroInteractions(document);
		verifyZeroInteractions(entity);
		verifyZeroInteractions(event);
		verifyZeroInteractions(listener);
		verifyZeroInteractions(parser);
		verifyZeroInteractions(response);
		verifyZeroInteractions(statusLine);
	}

	@Test
	public void send_GET_asynchronous_IOException() throws IllegalStateException, IOException, InterruptedException {
		doThrow(new IOException()).when(entity).getContent();
		request.open("GET", resource.toExternalForm(), true, EMPTY, EMPTY);
		request.send("GET");
		Thread.sleep(100);
		verify(client).execute(argThat(matchRequestURI(resource)));
		verify(entity).getContent();
		verify(response).getEntity();
		verifyZeroInteractions(document);
		verifyZeroInteractions(event);
		verifyZeroInteractions(listener);
		verifyZeroInteractions(parser);
		verifyZeroInteractions(response);
		verifyZeroInteractions(statusLine);
	}
	
	@Test
	public void send_GET_asynchronous() throws IOException, InterruptedException {
		request.setOnreadystatechange(listener);
		assertSame(listener, request.getOnreadystatechange());
		request.open("GET", resource.toExternalForm(), true, EMPTY, EMPTY);
		request.send("GET");
		Thread.sleep(100);
		assertEquals(ReadyState.RESPONSE_READY.ordinal(), request.getReadyState());
		assertEquals(200, request.getStatus());
		assertEquals(responseText, request.getResponseText());
		assertSame(document, request.getResponseXML());
		verify(listener, times(4)).handleEvent(event);
		verify(client).execute(argThat(matchRequestURI(resource)));
		verify(entity).getContent();
		verify(response).getEntity();
		verify(parser).parse(responseText, resource.toExternalForm());
		verify(statusLine).getStatusCode();
		verifyZeroInteractions(document);
		verifyZeroInteractions(event);
		verifyZeroInteractions(listener);
		verifyZeroInteractions(parser);
	}

	@Test
	public void send_GET_synchronous() throws IOException {
		request.open("GET", resource.toExternalForm(), false, EMPTY, EMPTY);
		request.send("GET");
		assertEquals(ReadyState.RESPONSE_READY.ordinal(), request.getReadyState());
		assertEquals(200, request.getStatus());
		assertEquals(responseText, request.getResponseText());
		assertSame(document, request.getResponseXML());
		verify(client).execute(argThat(matchRequestURI(resource)));
		verify(entity).getContent();
		verify(response).getEntity();
		verify(parser).parse(responseText, resource.toExternalForm());
		verify(statusLine).getStatusCode();
		verifyZeroInteractions(document);
		verifyZeroInteractions(event);
		verifyZeroInteractions(listener);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void send_POST_synchronous() throws IOException {
		request.open("POST", resource.toExternalForm(), false, EMPTY, EMPTY);
		request.send("POST");
		verifyZeroInteractions(client);
		verifyZeroInteractions(entity);
		verifyZeroInteractions(response);
		verifyZeroInteractions(statusLine);
		verifyZeroInteractions(event);
		verifyZeroInteractions(listener);
		verifyZeroInteractions(document);
		verifyZeroInteractions(parser);
	}

	private ArgumentMatcher<HttpUriRequest> matchRequestURI(final URL url) {
		return new ArgumentMatcher<HttpUriRequest>(HttpUriRequest.class) {
			protected boolean argumentMatches(HttpUriRequest request) {
				try {
					return request.getURI().toURL().equals(url);
				}
				catch (MalformedURLException x) {
					return false;
				}
			}
		};
	}
}
