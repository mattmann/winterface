package com.github.snoblind.winterface.abstracts;

import com.github.snoblind.winterface.Event;
import com.github.snoblind.winterface.EventException;
import com.github.snoblind.winterface.EventListener;
import com.github.snoblind.winterface.ExtendedHTMLDocument;
import com.github.snoblind.winterface.ExtendedHTMLElement;
import com.github.snoblind.winterface.Window;
import com.github.snoblind.winterface.event.EventDispatcher;
import com.github.snoblind.winterface.spi.QuerySelector;
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
import org.w3c.dom.html.HTMLElement;

public class AbstractHTMLDocument extends AbstractNode implements ExtendedHTMLDocument {

	protected QuerySelector querySelector;
	protected EventDispatcher eventDispatcher;

	public String getTitle() {
		throw new UnsupportedOperationException();
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

	public HTMLCollection getImages() {
		throw new UnsupportedOperationException();
	}

	public HTMLCollection getApplets() {
		throw new UnsupportedOperationException();
	}

	public HTMLCollection getLinks() {
		throw new UnsupportedOperationException();
	}

	public HTMLCollection getForms() {
		throw new UnsupportedOperationException();
	}

	public HTMLCollection getAnchors() {
		throw new UnsupportedOperationException();
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

	public DocumentType getDoctype() {
		throw new UnsupportedOperationException();
	}

	public DOMImplementation getImplementation() {
		throw new UnsupportedOperationException();
	}

	public Element getDocumentElement() {
		throw new UnsupportedOperationException();
	}

	public Element createElement(String tagName) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public DocumentFragment createDocumentFragment() {
		throw new UnsupportedOperationException();
	}

	public Text createTextNode(String data) {
		throw new UnsupportedOperationException();
	}

	public Comment createComment(String data) {
		throw new UnsupportedOperationException();
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
		throw new UnsupportedOperationException();
	}

	public Node importNode(Node importedNode, boolean deep) throws DOMException {
		throw new UnsupportedOperationException();
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

	public Element getElementById(String elementId) {
		throw new UnsupportedOperationException();
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

	public Node renameNode(Node n, String namespaceURI, String qualifiedName)
			throws DOMException {
		throw new UnsupportedOperationException();
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

	public HTMLElement querySelector(String query) {
		return querySelector.querySelector(this, query);
	}

	public NodeList querySelectorAll(String query) {
		return querySelector.querySelectorAll(this, query);
	}

	public NodeList getElementsByName(String elementName) {
		throw new UnsupportedOperationException();
	}

	public Window getDefaultView() {
		throw new UnsupportedOperationException();
	}

	public ExtendedHTMLElement getBody() {
		throw new UnsupportedOperationException();
	}

}