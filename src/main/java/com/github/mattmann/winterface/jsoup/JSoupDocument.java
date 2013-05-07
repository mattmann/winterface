package com.github.mattmann.winterface.jsoup;

import com.github.mattmann.winterface.Event;
import com.github.mattmann.winterface.EventException;
import com.github.mattmann.winterface.EventListener;
import com.github.mattmann.winterface.HTMLDocument;
import com.github.mattmann.winterface.HTMLElement;
import com.github.mattmann.winterface.HTMLTitleElement;
import com.github.mattmann.winterface.Window;
import com.github.mattmann.winterface.event.EventDispatcher;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.html.HTMLCollection;
import static org.apache.commons.lang.Validate.notNull;

public class JSoupDocument extends JSoupNode<org.jsoup.nodes.Document> implements HTMLDocument {

	public JSoupDocument(Document document, EventDispatcher eventDispatcher) {
		super(document);
		notNull(this.eventDispatcher = eventDispatcher);
	}

	protected final EventDispatcher eventDispatcher;

	public EventDispatcher getEventDispatcher() {
		return eventDispatcher;
	}

	protected Window defaultView;

	public Window getDefaultView() {
		return defaultView;
	}

	public void setDefaultView(Window defaultView) {
		this.defaultView = defaultView;
	}

	public JSoupDocument getOwnerDocument() {
		return this;
	}

	public HTMLElement querySelector(String query) {
		NodeList nodes = querySelectorAll(query);
		if (nodes.getLength() == 0) {
			return null;
		}
		return (HTMLElement)nodes.item(0);
	}

	public NodeList querySelectorAll(String query) {
		final Elements elements = node.select(query.toString());
		return new NodeList() {

			public Node item(int index) {
				return wrap(elements.get(index));
			}

			public int getLength() {
				return elements.size();
			}
		};
	}

	public Attr createAttribute(String name) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public CDATASection createCDATASection(String data) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Comment createComment(String data) {
		throw new UnsupportedOperationException();
	}

	public DocumentFragment createDocumentFragment() {
		throw new UnsupportedOperationException();
	}

	public DocumentType getDoctype() {
		throw new UnsupportedOperationException();
	}

	public DOMImplementation getImplementation() {
		throw new UnsupportedOperationException();
	}

	public Element createElement(String tagName) throws DOMException {
		notNull(tagName);
		return wrap(node.createElement(tagName.toString()));
	}

	public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Element getDocumentElement() {
		return new JSoupHtmlElement(node.child(0), this);
	}

	public EntityReference createEntityReference(String name) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Node importNode(Node importedNode, boolean deep) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public HTMLCollectionAdapter getElementsByTagName(final String tagName) {
		return collect(new ElementFilter() {
			protected boolean accept(org.jsoup.nodes.Element element) {
				return element.tagName().equals(tagName);
			}
		});
	}

	public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
		throw new UnsupportedOperationException();
	}

	public ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Text createTextNode(String data) {
		throw new UnsupportedOperationException();
	}

	public String getTitle() {
		HTMLElement element = querySelector("> html > head > title");
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

	public HTMLElement getBody() {
		return new JSoupBodyElement(node.body(), this);
	}

	public void setBody(org.w3c.dom.html.HTMLElement body) {
		throw new UnsupportedOperationException();
	}

	public HTMLCollection getImages() {
		return getElementsByTagName("img");
	}

	public HTMLCollection getApplets() {
		return getElementsByTagName("applet");
	}

	public HTMLCollection getForms() {
		return getElementsByTagName("form");
	}

	public HTMLCollection getLinks() {
		return collect(new ElementFilter() {
			protected boolean accept(org.jsoup.nodes.Element element) {
				return element.tagName().equals("a") && element.hasAttr("href");
			}
		});
	}

	public HTMLCollection getAnchors() {
		return collect(new ElementFilter() {
			protected boolean accept(org.jsoup.nodes.Element element) {
				return element.tagName().equals("a") && element.hasAttr("name");
			}
		});
	}

	public String getCookie() {
		throw new UnsupportedOperationException();
	}

	public void setCookie(String cookie) {
		throw new UnsupportedOperationException();
	}

	public void open() {
		throw new UnsupportedOperationException();
	}

	public void close() {
		throw new UnsupportedOperationException();
	}

	public void write(String text) {
		throw new UnsupportedOperationException();
	}

	public void writeln(String text) {
		throw new UnsupportedOperationException();
	}

	public Element getElementById(String elementId) {
		throw new UnsupportedOperationException();
	}

	public NodeList getElementsByName(String elementName) {
		throw new UnsupportedOperationException();
	}

	public Event createEvent(String eventInterface) {
		return eventDispatcher.createEvent(eventInterface);
	}

	public void addEventListener(String type, EventListener listener, boolean useCapture) {
		throw new UnsupportedOperationException();
	}

	public void removeEventListener(String type, EventListener listener, boolean useCapture) {
		throw new UnsupportedOperationException();
	}

	public boolean dispatchEvent(Event event) throws EventException {
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return "#document";
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
}
