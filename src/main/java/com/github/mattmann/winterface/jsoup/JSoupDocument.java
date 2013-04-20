package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.github.mattmann.winterface.Attr;
import com.github.mattmann.winterface.CDATASection;
import com.github.mattmann.winterface.Comment;
import com.github.mattmann.winterface.DOMException;
import com.github.mattmann.winterface.DOMImplementation;
import com.github.mattmann.winterface.DocumentFragment;
import com.github.mattmann.winterface.DocumentType;
import com.github.mattmann.winterface.Element;
import com.github.mattmann.winterface.EntityReference;
import com.github.mattmann.winterface.Event;
import com.github.mattmann.winterface.EventException;
import com.github.mattmann.winterface.EventListener;
import com.github.mattmann.winterface.HTMLCollection;
import com.github.mattmann.winterface.HTMLDocument;
import com.github.mattmann.winterface.HTMLElement;
import com.github.mattmann.winterface.HTMLTitleElement;
import com.github.mattmann.winterface.Node;
import com.github.mattmann.winterface.NodeList;
import com.github.mattmann.winterface.ProcessingInstruction;
import com.github.mattmann.winterface.Text;
import com.github.mattmann.winterface.Window;
import com.github.mattmann.winterface.event.EventDispatcher;

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

	public HTMLElement querySelector(CharSequence query) {
		NodeList nodes = querySelectorAll(query);
		if (nodes.getLength() == 0) {
			return null;
		}
		return (HTMLElement)nodes.item(0);
	}

	public NodeList querySelectorAll(CharSequence query) {
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

	public Attr createAttribute(CharSequence name) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Attr createAttributeNS(CharSequence namespaceURI, CharSequence qualifiedName) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public CDATASection createCDATASection(CharSequence data) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Comment createComment(CharSequence data) {
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

	public Element createElement(CharSequence tagName) throws DOMException {
		notNull(tagName);
		return wrap(node.createElement(tagName.toString()));
	}

	public Element createElementNS(CharSequence namespaceURI, CharSequence qualifiedName) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Element getDocumentElement() {
		return new JSoupHtmlElement(node.child(0), this);
	}

	public EntityReference createEntityReference(CharSequence name) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Node importNode(Node importedNode, boolean deep) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public HTMLCollectionAdapter getElementsByTagName(final CharSequence tagName) {
		return collect(new ElementFilter() {
			protected boolean accept(org.jsoup.nodes.Element element) {
				return element.tagName().equals(tagName);
			}
		});
	}

	public NodeList getElementsByTagNameNS(CharSequence namespaceURI, CharSequence localName) {
		throw new UnsupportedOperationException();
	}

	public ProcessingInstruction createProcessingInstruction(CharSequence target, CharSequence data) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Text createTextNode(CharSequence data) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getTitle() {
		HTMLElement element = querySelector("> html > head > title");
		if (element instanceof HTMLTitleElement) {
			return ((HTMLTitleElement)element).getText();
		}
		return null;
	}

	public void setTitle(CharSequence title) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getReferrer() {
		throw new UnsupportedOperationException();
	}

	public CharSequence getDomain() {
		throw new UnsupportedOperationException();
	}

	public CharSequence getURL() {
		throw new UnsupportedOperationException();
	}

	public HTMLElement getBody() {
		return new JSoupBodyElement(node.body(), this);
	}

	public void setBody(HTMLElement body) {
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

	public CharSequence getCookie() {
		throw new UnsupportedOperationException();
	}

	public void setCookie(CharSequence cookie) {
		throw new UnsupportedOperationException();
	}

	public void open() {
		throw new UnsupportedOperationException();
	}

	public void close() {
		throw new UnsupportedOperationException();
	}

	public void write(CharSequence text) {
		throw new UnsupportedOperationException();
	}

	public void writeln(CharSequence text) {
		throw new UnsupportedOperationException();
	}

	public Element getElementById(CharSequence elementId) {
		throw new UnsupportedOperationException();
	}

	public NodeList getElementsByName(CharSequence elementName) {
		throw new UnsupportedOperationException();
	}

	public Event createEvent(CharSequence eventInterface) {
		return eventDispatcher.createEvent(eventInterface);
	}

	public void addEventListener(CharSequence type, EventListener listener, boolean useCapture) {
		throw new UnsupportedOperationException();
	}

	public void removeEventListener(CharSequence type, EventListener listener, boolean useCapture) {
		throw new UnsupportedOperationException();
	}

	public boolean dispatchEvent(Event event) throws EventException {
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return "#document";
	}
}
