package com.github.snoblind.winterface.nashorn;

import com.github.snoblind.winterface.spi.HTMLSerializer;
import com.github.snoblind.winterface.util.NodeListUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import static org.apache.commons.lang.Validate.notNull;

public class NashornHTMLSerializer implements HTMLSerializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(NashornHTMLSerializer.class);

	public String serialize(Node node) {
		notNull(node);
		final Writer writer = new StringWriter();
		serialize(node, new PrintWriter(writer, true));
		LOGGER.debug("Serialized HTML: \"{}\".", writer);
		return writer.toString();
	}

	private void serialize(final Node node, final PrintWriter writer) {
		if (node instanceof Comment) {
			serialize((Comment) node, writer);
		}
		else if (node instanceof Element) {
			serialize((Element) node, writer);
		}
		else if (node instanceof Text) {
			serialize((Text) node, writer);
		}
		else {
			throw new IllegalArgumentException(node.getClass().getName());
		}
	}

	private void serialize(final Comment comment, final PrintWriter writer) {
		writer.printf("<!--%s-->", comment.getData());
	}

	private void serialize(final Text text, final PrintWriter writer) {
		writer.print(text.getData());
	}
	
	private void serialize(final Element element, final PrintWriter writer) {
		final NamedNodeMap attributes = element.getAttributes();
		writer.printf("<%s", element.getTagName());
		for (int i = 0; i < attributes.getLength(); i++) {
			final Attr attribute = (Attr) attributes.item(i);
			writer.printf(" %s=\"%s\"", attribute.getName(), attribute.getValue());
		}
		writer.print(">");
		for (Node childNode: NodeListUtils.iterable(element.getChildNodes())) {
			serialize(childNode, writer);
		}
		writer.printf("</%s>", element.getTagName());
	}
}
