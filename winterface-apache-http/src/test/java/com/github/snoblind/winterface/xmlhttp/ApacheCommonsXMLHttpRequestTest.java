package com.github.snoblind.winterface.xmlhttp;

import com.github.snoblind.winterface.Event;
import com.github.snoblind.winterface.EventListener;
import com.github.snoblind.winterface.mock.ArgumentMatcher;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.collections4.functors.ConstantFactory;
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
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.doReturn;
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

	@Mock private HttpClient client;
	@Mock private HttpEntity entity;
	@Mock private HttpResponse response;
	@Mock private StatusLine statusLine;
	@Mock private Event event;
	@Mock private EventListener listener;
	@Mock private Document document;
	@Mock private DocumentBuilder documentBuilder;
	@Mock private DocumentBuilderFactory documentBuilderFactory;
	
	@Before
	public void setUp() throws ClientProtocolException, IOException, ParserConfigurationException, SAXException {
		initMocks(this);
		request = new ApacheCommonsXMLHttpRequest(client, ConstantFactory.constantFactory(event), documentBuilderFactory);
		doReturn(200).when(statusLine).getStatusCode();
		doReturn(documentBuilder).when(documentBuilderFactory).newDocumentBuilder();
		doReturn(document).when(documentBuilder).parse(any(InputSource.class));
		doReturn(entity).when(response).getEntity();
		doReturn(response).when(client).execute(any(HttpUriRequest.class));
		doReturn(statusLine).when(response).getStatusLine();
		doAnswer(new Answer<InputStream>() {
			public InputStream answer(InvocationOnMock invocation) throws Throwable {
				return new ByteArrayInputStream(responseText.getBytes());
			}
		}).when(entity).getContent();
		assertEquals(-1, request.getStatus());
	}

	@Test
	public void send_GET_synchronous_ParserConfigurationException() throws ParserConfigurationException, IOException {
		doThrow(new ParserConfigurationException()).when(documentBuilderFactory).newDocumentBuilder();
		request.open("GET", resource.toExternalForm(), true, EMPTY, EMPTY);
		request.send("GET");
		assertNull(request.getResponseXML());
		verifyZeroInteractions(client);
		verifyZeroInteractions(entity);
		verifyZeroInteractions(response);
		verifyZeroInteractions(statusLine);
		verifyZeroInteractions(event);
		verifyZeroInteractions(listener);
		verifyZeroInteractions(document);
		verifyZeroInteractions(documentBuilder);
		verifyZeroInteractions(documentBuilderFactory);
	}

	@Test
	public void send_GET_synchronous_SAXException() throws SAXException, IOException {
		doThrow(new SAXException()).when(documentBuilder).parse(any(InputSource.class));
		request.open("GET", resource.toExternalForm(), true, EMPTY, EMPTY);
		request.send("GET");
		assertNull(request.getResponseXML());
		verifyZeroInteractions(client);
		verifyZeroInteractions(entity);
		verifyZeroInteractions(response);
		verifyZeroInteractions(statusLine);
		verifyZeroInteractions(event);
		verifyZeroInteractions(listener);
		verifyZeroInteractions(document);
		verifyZeroInteractions(documentBuilder);
		verifyZeroInteractions(documentBuilderFactory);
	}

	@Test
	public void send_GET_asynchronous_IOException() throws IllegalStateException, IOException, InterruptedException {
		doThrow(new IOException()).when(entity).getContent();
		request.open("GET", resource.toExternalForm(), true, EMPTY, EMPTY);
		request.send("GET");
		Thread.sleep(100);
		verify(client).execute(argThat(new ArgumentMatcher<HttpUriRequest>(HttpUriRequest.class) {
			protected boolean argumentMatches(HttpUriRequest request) {
				try {
					return request.getURI().toURL().equals(resource);
				}
				catch (MalformedURLException x) {
					return false;
				}
			}
		}));
//		verifyZeroInteractions(entity);
//		verifyZeroInteractions(response);
		verifyZeroInteractions(statusLine);
		verifyZeroInteractions(event);
		verifyZeroInteractions(listener);
		verifyZeroInteractions(document);
		verifyZeroInteractions(documentBuilder);
		verifyZeroInteractions(documentBuilderFactory);
	}
	
	@Test
	public void send_GET_asynchronous() throws IOException, InterruptedException {
		request.setOnreadystatechange(listener);
		assertSame(listener, request.getOnreadystatechange());
		request.open("GET", resource.toExternalForm(), true, EMPTY, EMPTY);
		request.send("GET");
		Thread.sleep(100);
		verify(listener, times(4)).handleEvent(event);
		verify(client).execute(argThat(new ArgumentMatcher<HttpUriRequest>(HttpUriRequest.class) {
			protected boolean argumentMatches(HttpUriRequest request) {
				try {
					return request.getURI().toURL().equals(resource);
				}
				catch (MalformedURLException x) {
					return false;
				}
			}
		}));
//		verifyZeroInteractions(entity);
//		verifyZeroInteractions(response);
//		verifyZeroInteractions(statusLine);
		verifyZeroInteractions(event);
		verifyZeroInteractions(document);
//		verifyZeroInteractions(documentBuilder);
//		verifyZeroInteractions(documentBuilderFactory);

		assertEquals(ReadyState.RESPONSE_READY.ordinal(), request.getReadyState());
		assertEquals(200, request.getStatus());
		assertEquals(responseText, request.getResponseText());
		assertSame(document, request.getResponseXML());
	}

	@Test
	public void send_GET_synchronous() throws IOException {
		request.open("GET", resource.toExternalForm(), false, EMPTY, EMPTY);
		request.send("GET");
//		verifyZeroInteractions(client);
//		verifyZeroInteractions(entity);
//		verifyZeroInteractions(response);
//		verifyZeroInteractions(statusLine);
		verifyZeroInteractions(event);
		verifyZeroInteractions(listener);
		verifyZeroInteractions(document);
//		verifyZeroInteractions(documentBuilder);
//		verifyZeroInteractions(documentBuilderFactory);
		assertEquals(ReadyState.RESPONSE_READY.ordinal(), request.getReadyState());
		assertEquals(200, request.getStatus());
		assertEquals(responseText, request.getResponseText());
		assertSame(document, request.getResponseXML());
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
		verifyZeroInteractions(documentBuilder);
		verifyZeroInteractions(documentBuilderFactory);
	}
}
