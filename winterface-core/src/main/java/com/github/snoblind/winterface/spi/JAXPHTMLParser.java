package com.github.snoblind.winterface.spi;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import static com.github.snoblind.winterface.util.NodeListUtils.iterable;
import static java.lang.String.format;
import static javax.xml.transform.OutputKeys.INDENT;
import static javax.xml.transform.OutputKeys.OMIT_XML_DECLARATION;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.apache.commons.lang.Validate.notNull;

public class JAXPHTMLParser implements HTMLParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(JAXPHTMLParser.class);

	private final DocumentBuilder documentBuilder;
	private final Transformer transformer;
	
	public JAXPHTMLParser(final DocumentBuilder documentBuilder, final Transformer transformer) {
		notNull(this.documentBuilder = documentBuilder);
		notNull(this.transformer = transformer);
	}

	public JAXPHTMLParser() throws ParserConfigurationException, TransformerException {
		documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(INDENT, "no");
		transformer.setOutputProperty(OMIT_XML_DECLARATION, "yes");
	}

	private String toString(Node node) {
		final Writer writer = new StringWriter();
		try {
			transformer.transform(new DOMSource(node), new StreamResult(writer));
			return writer.toString();
		}
		catch (TransformerException x) {
			throw new RuntimeException(x);
		}
	}
	
	public Document parse(String html, String baseUri) {
		try {
			return documentBuilder.parse(new InputSource(new StringReader(html)));
		}
		catch (SAXException x) {
			throw new RuntimeException(x);
		}
		catch (IOException x) {
			throw new RuntimeException(x);
		}
	}

	public String getInnerText(Node node) {
		LOGGER.debug("{}.getInnerText({})", getClass().getName(), node.getClass().getName());
		throw new UnsupportedOperationException();
	}

	public void setInnerText(Node node, String innerText) {
		LOGGER.debug("{}.setInnerText({}, {})", getClass().getName(), node.getClass().getName(), innerText);
		throw new UnsupportedOperationException();
	}

	public String getInnerHTML(Element element) {
		LOGGER.debug("{}.getInnerHTML({})", getClass().getName(), toString(element));
		final NodeList nodes = element.getChildNodes();
		if (0 == nodes.getLength()) {
			return EMPTY;
		}
		final DocumentFragment fragment = element.getOwnerDocument().createDocumentFragment();
		for (Node node: iterable(nodes)) {
			fragment.appendChild(node.cloneNode(true));
		}
		return toString(fragment);
	}

	public void setInnerHTML(Element element, String innerHTML) {
		LOGGER.debug("{}.setInnerHTML({})", getClass().getName(), element.getClass().getName(), innerHTML);
		final Document document = parse(format("<%s>%s</%s>", element.getTagName(), innerHTML, element.getTagName()), null);
		while (element.getLastChild() != null) {
			element.removeChild(element.getLastChild());
		}
		for (Node node: iterable(document.getDocumentElement().getChildNodes())) {
			element.appendChild(element.getOwnerDocument().importNode(node, true));
		}
	}

	public String getOuterHTML(Element element) {
		LOGGER.debug("{}.getOuterHTML({})", getClass().getName(), element.getClass().getName());
		throw new UnsupportedOperationException();
	}

	public Node setOuterHTML(Element element, String outerHTML) {
		LOGGER.debug("{}.setOuterHTML({})", getClass().getName(), element.getClass().getName(), outerHTML);
		throw new UnsupportedOperationException();
	}
}
