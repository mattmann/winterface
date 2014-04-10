package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.Event;
import com.github.snoblind.winterface.EventException;
import com.github.snoblind.winterface.EventListener;
import com.github.snoblind.winterface.ExtendedHTMLCollection;
import com.github.snoblind.winterface.ExtendedHTMLDocument;
import com.github.snoblind.winterface.ExtendedHTMLElement;
import com.github.snoblind.winterface.event.EventDispatcher;
import com.github.snoblind.winterface.spi.CachingNodeAdapterFactory;
import com.github.snoblind.winterface.spi.HTMLParser;
import com.github.snoblind.winterface.spi.NodeAdapterFactory;
import com.github.snoblind.winterface.spi.QuerySelector;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.mozilla.javascript.Scriptable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLTitleElement;
import static com.github.snoblind.winterface.required.RequiredProperties.assertRequiredProperties;

public class RhinoDocument extends RhinoNode<Document> implements Cloneable, ExtendedHTMLDocument {

	private static final Logger LOGGER = LoggerFactory.getLogger(RhinoDocument.class);

	private final NodeAdapterFactory<Node, RhinoDocument> nodeAdapterFactory = new CachingNodeAdapterFactory<Node, RhinoDocument>(new RhinoNodeAdapterFactory());

	private EventDispatcher eventDispatcher;
	private HTMLParser parser;
	private QuerySelector querySelector;
	private RhinoWindow defaultView;

	protected RhinoDocument(Document node) {
		super(node, ExtendedHTMLDocument.class);
	}

	public String getCookie() {
		final URI uri = getURI();
		final List<HttpCookie> cookies = defaultView.getCookieStore().get(uri);
		final StringBuilder builder = new StringBuilder();
		for (HttpCookie cookie: cookies) {
			if (builder.length() > 0) {
				builder.append("; ");
			}
			builder.append(cookie.getName()).append("=").append(cookie.getValue());
		}
		final String returnValue = builder.toString();
		LOGGER.debug("getCookie() => \"{}\"", returnValue);
		return returnValue;
	}

	public void setCookie(String cookieString) {
		LOGGER.warn("setCookie({})", cookieString);
		final URI uri = getURI();
		final List<HttpCookie> cookies = HttpCookie.parse(cookieString);
		for (HttpCookie cookie: cookies) {
			defaultView.getCookieStore().add(uri, cookie);
		}
	}

	private URI getURI() {
		try {
			return new URI(defaultView.getLocation().getHref());
		}
		catch (URISyntaxException x) {
			throw new RuntimeException(x);
		}
	}

	public Object get(String name, Scriptable start) {
		return super.get(name, start);
	}

	public RhinoDocument getOwnerDocument() {
		return this;
	}

	@Required
	public QuerySelector getQuerySelector() {
		return querySelector;
	}

	@Required
	public EventDispatcher getEventDispatcher() {
		return eventDispatcher;
	}
	
	@Required
	public NodeAdapterFactory<Node, RhinoDocument> getNodeAdapterFactory() {
		return nodeAdapterFactory;
	}

	@Required
	public HTMLParser getParser() {
		return parser;
	}

	protected void setEventDispatcher(EventDispatcher eventDispatcher) {
		this.eventDispatcher = eventDispatcher;
	}

	public RhinoWindow getDefaultView() {
		return defaultView;
	}

	protected void setDefaultView(RhinoWindow defaultView) {
		this.defaultView = defaultView;
	}

	public DocumentType getDoctype() {
		return (DocumentType) adapt(node.getDoctype());
	}

	public Element getDocumentElement() {
		return (Element) adapt(node.getDocumentElement());
	}

	public DOMImplementation getImplementation() {
		throw new UnsupportedOperationException();
	}

	public Element createElement(String tagName) throws DOMException {
		return (Element) adapt(node.createElement(tagName));
	}

	public DocumentFragment createDocumentFragment() {
		return (DocumentFragment) adapt(node.createDocumentFragment());
	}

	public Text createTextNode(String data) {
		throw new UnsupportedOperationException();
	}

	public Comment createComment(String data) {
		return (Comment) adapt(node.createComment(data));
	}

	public CDATASection createCDATASection(String data) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Attr createAttribute(String name) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public EntityReference createEntityReference(String name) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public NodeList getElementsByTagName(String tagname) {
		return querySelectorAll(tagname);
	}

	public Node importNode(Node importedNode, boolean deep) throws DOMException {
		return adapt(node.importNode(importedNode, deep));
	}

	public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
		throw new UnsupportedOperationException();
	}

	public Element getElementById(final String id) {
		return querySelector(String.format("#%s", id));
	}

	public String getInputEncoding() {
		throw new UnsupportedOperationException();
	}

	public String getXmlEncoding() {
		throw new UnsupportedOperationException();
	}

	public boolean getXmlStandalone() {
		throw new UnsupportedOperationException();
	}

	public void setXmlStandalone(boolean xmlStandalone) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public String getXmlVersion() {
		throw new UnsupportedOperationException();
	}

	public void setXmlVersion(String xmlVersion) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public boolean getStrictErrorChecking() {
		throw new UnsupportedOperationException();
	}

	public void setStrictErrorChecking(boolean strictErrorChecking) {
		throw new UnsupportedOperationException();
	}

	public String getDocumentURI() {
		throw new UnsupportedOperationException();
	}

	public void setDocumentURI(String documentURI) {
		throw new UnsupportedOperationException();
	}

	public Node adoptNode(Node source) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public DOMConfiguration getDomConfig() {
		throw new UnsupportedOperationException();
	}

	public void normalizeDocument() {
		throw new UnsupportedOperationException();
	}

	public Node renameNode(Node n, String namespaceURI, String qualifiedName) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private Document document;
		private EventDispatcher eventDispatcher;
		private HTMLParser parser;
		private QuerySelector querySelector;

		public RhinoDocument build() {
			final RhinoDocument rhinoDocument = new RhinoDocument(document);
			rhinoDocument.eventDispatcher = eventDispatcher;
			rhinoDocument.parser = parser;
			rhinoDocument.querySelector = querySelector;
			assertRequiredProperties(rhinoDocument);
			return rhinoDocument;
		}
		
		public Builder document(final Document document) {
			this.document = document;
			return this;
		}

		public Builder eventDispatcher(final EventDispatcher eventDispatcher) {
			this.eventDispatcher = eventDispatcher;
			return this;
		}

		public Builder parser(final HTMLParser parser) {
			this.parser = parser;
			return this;
		}

		public Builder querySelector(QuerySelector querySelector) {
			this.querySelector = querySelector;
			return this;
		}
	}

	public void addEventListener(String type, EventListener listener, boolean useCapture) {
		eventDispatcher.addEventListener(this, type, listener, useCapture);
	}

	public void removeEventListener(String type, EventListener listener, boolean useCapture) {
		eventDispatcher.removeEventListener(this, type, listener, useCapture);
	}

	public boolean dispatchEvent(Event event) throws EventException {
		return eventDispatcher.dispatchEvent(event);
	}

	public Element querySelector(String selectors) {
		return querySelector.querySelector(this, selectors);
	}

	public ExtendedHTMLCollection querySelectorAll(String selectors) {
		return new RhinoHTMLCollection(querySelector.querySelectorAll(this, selectors), this);
	}

	public ExtendedHTMLCollection getElementsByName(final String name) {
		return querySelectorAll(name);
	}

	public ExtendedHTMLElement getHead() {
		return (ExtendedHTMLElement) querySelector("html > head");
	}
	
	public ExtendedHTMLElement getBody() {
		return (ExtendedHTMLElement) querySelector("html > body");
	}

	public String getTitle() {
		final Element element = querySelector("html > head > title");
		if (element instanceof HTMLTitleElement) {
			return ((HTMLTitleElement)element).getText();
		}
		return null;
	}

	public void setTitle(String title) {
		throw new UnsupportedOperationException();
	}

	public String getReferrer() {
		throw new UnsupportedOperationException();
	}

	public String getDomain() {
		throw new UnsupportedOperationException();
	}

	public String getURL() {
		throw new UnsupportedOperationException();
	}

	public void setBody(HTMLElement body) {
		throw new UnsupportedOperationException();
	}

	public ExtendedHTMLCollection getImages() {
		return querySelectorAll("img");
	}

	public ExtendedHTMLCollection getApplets() {
		return querySelectorAll("applet");
	}

	public ExtendedHTMLCollection getLinks() {
		return querySelectorAll("a[href]");
	}

	public ExtendedHTMLCollection getForms() {
		return querySelectorAll("form");
	}

	public ExtendedHTMLCollection getAnchors() {
		return querySelectorAll("a[name]");
	}

	public void open() {
		throw new UnsupportedOperationException();
	}

	public void close() {
		throw new UnsupportedOperationException();
	}

	public void write(String text) {
		throw new UnsupportedOperationException(String.format("write(\"%s\")", text));
	}

	public void writeln(String text) {
		throw new UnsupportedOperationException();
	}

	public String getClassName() {
		throw new UnsupportedOperationException();
	}
}
