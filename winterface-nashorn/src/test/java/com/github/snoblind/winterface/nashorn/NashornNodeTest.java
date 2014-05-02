package com.github.snoblind.winterface.nashorn;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import static com.github.snoblind.winterface.mock.Answers.UNSUPPORTED;
import static com.github.snoblind.winterface.mock.MockitoAnnotations.initMocks;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.w3c.dom.DOMException.INVALID_STATE_ERR;
import static org.w3c.dom.Node.COMMENT_NODE;
import static org.w3c.dom.Node.DOCUMENT_NODE;
import static org.w3c.dom.Node.ELEMENT_NODE;
import static org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE;
import static org.w3c.dom.Node.TEXT_NODE;

public class NashornNodeTest {

	@Spy private NashornNode node;

	@Mock private NodeList childNodes;
	
	@Before
	public void setUp() {
		initMocks(this, UNSUPPORTED);
	}

	@Test
	public void getTextContent_InvalidNodeType() {
		final short nodeType = 0;
		doReturn(nodeType).when(node).getNodeType();
		try {
			node.getTextContent();
			fail();
		}
		catch (DOMException x) {
			assertEquals(INVALID_STATE_ERR, x.code);
			assertEquals(String.format("Unexpected node type: %d.", nodeType), x.getMessage());
		}
	}

	@Test
	public void getTextContent_COMMENT() {
		final String content = "This is a comment.";
		doReturn(COMMENT_NODE).when(node).getNodeType();
		doReturn(content).when(node).getNodeValue();
		assertEquals(content, node.getTextContent());
	}

	@Test
	public void getTextContent_DOCUMENT() {
		doReturn(DOCUMENT_NODE).when(node).getNodeType();
		assertNull(node.getTextContent());
	}

	@Test
	public void getTextContent_ELEMENT_ZeroChildNodes() {
		doReturn(0).when(childNodes).getLength();
		doReturn(childNodes).when(node).getChildNodes();
		doReturn(ELEMENT_NODE).when(node).getNodeType();
		assertEquals(EMPTY, node.getTextContent());
	}

	@Test
	public void getTextContent_ELEMENT() {
		final String content = "This is text.";
		final Node c1 = mock(Node.class, UNSUPPORTED);
		final Node c2 = mock(Node.class, UNSUPPORTED);
		final Node c3 = mock(Node.class, UNSUPPORTED);
		doReturn(COMMENT_NODE).when(c1).getNodeType();
		doReturn(PROCESSING_INSTRUCTION_NODE).when(c2).getNodeType();
		doReturn(TEXT_NODE).when(c3).getNodeType();
		doReturn(content).when(c3).getTextContent();
		doReturn(3).when(childNodes).getLength();
		doReturn(c1).when(childNodes).item(0);
		doReturn(c2).when(childNodes).item(1);
		doReturn(c3).when(childNodes).item(2);
		doReturn(childNodes).when(node).getChildNodes();
		doReturn(ELEMENT_NODE).when(node).getNodeType();
		assertEquals(content, node.getTextContent());
	}
}