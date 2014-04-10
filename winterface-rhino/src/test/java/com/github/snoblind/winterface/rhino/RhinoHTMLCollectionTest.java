package com.github.snoblind.winterface.rhino;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang.ObjectUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLCollection;
import static com.github.snoblind.winterface.mock.Answers.UNSUPPORTED;
import static com.github.snoblind.winterface.mock.MockitoAnnotations.initMocks;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.intThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class RhinoHTMLCollectionTest {

	private RhinoHTMLCollection rhinoCollection;

	@Mock private Attr attribute;
	@Mock private HTMLCollection collection;
	@Mock private NamedNodeMap attributes;
	@Mock private Node node;
	@Mock private RhinoDocument document;
	@Mock private Scriptable scriptable;
	
	@Before
	public void setUp() {
		initMocks(this, UNSUPPORTED);
		rhinoCollection = new RhinoHTMLCollection(collection, document);
		doReturn(node).when(collection).namedItem("name");
		doReturn(null).when(collection).namedItem(neq("name"));
		doReturn(node).when(collection).item(0);
		doReturn(null).when(collection).item(neq(0));
		doReturn(1).when(collection).getLength();
		doReturn(node).when(document).adapt(node);
		doReturn(attributes).when(node).getAttributes();
		doReturn(null).when(attributes).getNamedItem("id");
		doReturn(attribute).when(attributes).getNamedItem("name");
		doReturn("name").when(attribute).getValue();
	}

	@Test
	public void getIds() {
		final List<Object> ids = Arrays.asList(rhinoCollection.getIds());
		assertEquals(2, ids.size());
		assertTrue(ids.contains(0));
		assertTrue(ids.contains("name"));
	}
	
	@Test
	public void getDefaultValue() {
		assertEquals(rhinoCollection.toString(), rhinoCollection.getDefaultValue(ScriptRuntime.StringClass));
	}
	
	@Test
	public void get_int() {
		assertSame(node, rhinoCollection.get(0, scriptable));
	}

	@Test
	public void get_String() {
		assertSame(node, rhinoCollection.get("name", scriptable));
	}

	@Test
	public void has_int() {
		assertTrue(rhinoCollection.has(0, scriptable));
	}

	@Test
	public void has_String() {
		assertTrue(rhinoCollection.has("name", scriptable));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void put_int() {
		rhinoCollection.put(1, scriptable, node);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void put_String() {
		rhinoCollection.put("abc", scriptable, node);
	}
	
	@Test
	public void getClassName() {
		assertEquals(RhinoHTMLCollection.class.getName(), rhinoCollection.getClassName());
	}

	@Test
	public void namedItem_NotFound() {
		assertNull(rhinoCollection.namedItem("abc"));
		verify(collection).namedItem("abc");
		verifyNoMoreInteractions(collection);
		verifyZeroInteractions(document);
		verifyZeroInteractions(node);
	}
	
	@Test
	public void namedItem() {
		assertSame(node, rhinoCollection.namedItem("name"));
		verify(collection).namedItem("name");
		verify(document).adapt(node);
		verifyNoMoreInteractions(collection);
		verifyNoMoreInteractions(document);
		verifyNoMoreInteractions(node);
	}
	
	@Test
	public void item_NotFound() {
		assertNull(rhinoCollection.item(1));
		verify(collection).item(1);
		verifyNoMoreInteractions(collection);
		verifyZeroInteractions(document);
		verifyZeroInteractions(node);
	}

	@Test
	public void item() {
		assertSame(node, rhinoCollection.item(0));
		verify(collection).item(0);
		verify(document).adapt(node);
		verifyNoMoreInteractions(collection);
		verifyNoMoreInteractions(document);
		verifyNoMoreInteractions(node);
	}
	
	@Test
	public void getLength() {
		assertEquals(1, rhinoCollection.getLength());
		verify(collection).getLength();
		verifyNoMoreInteractions(collection);
		verifyZeroInteractions(document);
		verifyZeroInteractions(node);
	}

	private int neq(final int value1) {
		return intThat(new ArgumentMatcher<Integer>() {
			public boolean matches(final Object value2) {
				return ObjectUtils.notEqual(value1, value2);
			}
		});
	}

	private <T> T neq(final T value1) {
		return argThat(new ArgumentMatcher<T>() {
			public boolean matches(final Object value2) {
				return ObjectUtils.notEqual(value1, value2);
			}
		});
	}
}
