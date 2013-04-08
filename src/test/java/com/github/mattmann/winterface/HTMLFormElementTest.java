package com.github.mattmann.winterface;

import org.mockito.Mockito;

import java.io.PrintWriter;

import java.io.Writer;

import java.io.StringWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.mattmann.winterface.Event;
import com.github.mattmann.winterface.EventListener;
import com.github.mattmann.winterface.HTMLCollection;
import com.github.mattmann.winterface.HTMLFormElement;

import static org.junit.Assert.assertEquals;

public abstract class HTMLFormElementTest {

	protected abstract HTMLFormElement createHTMLFormElement() throws Exception;
	
	private HTMLFormElement form;
	
	@Before
	public void setUp() throws Exception {
		form = createHTMLFormElement();
	}
	
	@After
	public void tearDown() {
		form = null;
	}
	
	@Test
	public void test1() {
		
		HTMLCollection elements = form.getElements();
		assertEquals(elements.getLength(), 0);
		assertEquals(elements.getLength(), form.getLength());
		
		form.setAcceptCharset("UTF-8");
		assertEquals("UTF-8", form.getAcceptCharset());
		
		form.setAction("index.html");
		assertEquals("index.html", form.getAction());
		
		form.setEnctype("multipart/form-data");
		assertEquals("multipart/form-data", form.getEnctype());

		form.setMethod("POST");
		assertEquals("POST", form.getMethod());

		form.setName("name");
		assertEquals("name", form.getName());

		form.setTarget("_top");
		assertEquals("_top", form.getTarget());
	}
	
	@Test
	public void test2() {
		
		Writer buffer = new StringWriter();
		PrintWriter writer = new PrintWriter(buffer, true);
		writer.println("<button type=\"reset\">reset</button>");
		writer.println("<button type=\"submit\">submit</button>");
		writer.println("<input type=\"checkbox\">");
		writer.println("<input type=\"radio\">");
		writer.println("<input type=\"text\">");
		writer.println("<select/>");
		writer.println("<textarea rows=\"4\" cols=\"32\"/>");
		form.setInnerHTML(buffer.toString());

		form.getOnsubmit();
		form.setOnsubmit(null);
		form.getOnreset();
		form.setOnreset(null);
		
/*		HTMLCollection elements = form.getElements();
		for (int i = 0; i < elements.getLength(); i++) {
			HTMLElement element = (HTMLElement)elements.item(i);
			System.out.println(element.getOuterHTML());
		}
*/
		assertEquals(7, form.getLength());
	}
	
	@Test
	public void test3() {
		EventListener listener = Mockito.mock(EventListener.class);
		form.setOnsubmit(listener);
		assertEquals(listener, form.getOnsubmit());
		form.submit();
		Mockito.verify(listener).handleEvent(Mockito.any(Event.class));
	}

	@Test
	public void test4() {
		EventListener listener = Mockito.mock(EventListener.class);
		form.setOnreset(listener);
		assertEquals(listener, form.getOnreset());
		form.reset();
		Mockito.verify(listener).handleEvent(Mockito.any(Event.class));
	}
}
