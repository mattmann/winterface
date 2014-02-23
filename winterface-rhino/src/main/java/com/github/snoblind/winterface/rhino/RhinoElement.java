package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.Event;
import com.github.snoblind.winterface.EventException;
import com.github.snoblind.winterface.EventListener;
import com.github.snoblind.winterface.ExtendedHTMLElement;
import com.github.snoblind.winterface.OnErrorEventHandler;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;
import static org.apache.commons.lang.Validate.notNull;

public class RhinoElement extends RhinoNode<Element> implements ExtendedHTMLElement {

	private static final long serialVersionUID = 207282257322677408L;

	private final RhinoDocument ownerDocument;
	
	public RhinoElement(Element element, RhinoDocument ownerDocument) {
		super(element);
		notNull(this.ownerDocument = ownerDocument);
	}

	public RhinoDocument getOwnerDocument() {
		return ownerDocument;
	}

	public String getId() {
		return getAttribute("id");
	}

	public void setId(String value) {
		setAttribute("id", value);
	}

	public String getTitle() {
		throw new UnsupportedOperationException();
	}

	public void setTitle(String title) {
		throw new UnsupportedOperationException();
	}

	public String getLang() {
		throw new UnsupportedOperationException();
	}

	public void setLang(String lang) {
		throw new UnsupportedOperationException();
	}

	public String getDir() {
		throw new UnsupportedOperationException();
	}

	public void setDir(String dir) {
		throw new UnsupportedOperationException();
	}

	public String getClassName() {
		return getAttribute("class");
	}

	public void setClassName(String value) {
		setAttribute("className", value);
	}

	public String getTagName() {
		return node.getTagName();
	}

	public String getInnerHTML() {
		return ownerDocument.getParser().getInnerHTML(this);
	}

	public void setInnerHTML(String innerHTML) {
		ownerDocument.getParser().setInnerHTML(this, innerHTML);
	}

	public String getOuterHTML() {
		return ownerDocument.getParser().getOuterHTML(this);
	}

	public void setOuterHTML(String outerHTML) {
		ownerDocument.getParser().setOuterHTML(this, outerHTML);
	}

	public Element querySelector(String query) {
		return ownerDocument.getQuerySelector().querySelector(this, query);
	}

	public NodeList querySelectorAll(String query) {
		return ownerDocument.getQuerySelector().querySelectorAll(this, query);
	}

	public RhinoCSSStyleDeclaration getStyle() {
		return new RhinoCSSStyleDeclaration(this);
	}
	
	public String getAttribute(String name) {
		return node.getAttribute(name);
	}

	public void setAttribute(String name, String value) throws DOMException {
		node.setAttribute(name, value);
	}

	public void removeAttribute(String name) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Attr getAttributeNode(String name) {
		throw new UnsupportedOperationException();
	}

	public Attr setAttributeNode(Attr newAttr) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Attr removeAttributeNode(Attr oldAttr) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public NodeList getElementsByTagName(String name) {
		throw new UnsupportedOperationException();
	}

	public String getAttributeNS(String namespaceURI, String localName) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void setAttributeNS(String namespaceURI, String qualifiedName, String value) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void removeAttributeNS(String namespaceURI, String localName) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Attr getAttributeNodeNS(String namespaceURI, String localName) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Attr setAttributeNodeNS(Attr newAttr) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public NodeList getElementsByTagNameNS(String namespaceURI, String localName) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public boolean hasAttribute(String name) {
		return node.hasAttribute(name);
	}

	public boolean hasAttributeNS(String namespaceURI, String localName) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public TypeInfo getSchemaTypeInfo() {
		throw new UnsupportedOperationException();
	}

	public void setIdAttribute(String name, boolean isId) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void setIdAttributeNS(String namespaceURI, String localName, boolean isId) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void setIdAttributeNode(Attr idAttr, boolean isId) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void addEventListener(String type, EventListener listener, boolean useCapture) {
		ownerDocument.getEventDispatcher().addEventListener(this, type, listener, useCapture);
	}

	public void removeEventListener(String type, EventListener listener, boolean useCapture) {
		throw new UnsupportedOperationException();
	}

	public boolean dispatchEvent(Event event) throws EventException {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnabort() {
		throw new UnsupportedOperationException();
	}

	public void setOnabort(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnblur() {
		throw new UnsupportedOperationException();
	}

	public void setOnblur(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public OnErrorEventHandler getOnerror() {
		throw new UnsupportedOperationException();
	}

	public void setOnerror(OnErrorEventHandler handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnfocus() {
		throw new UnsupportedOperationException();
	}

	public void setOnfocus(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOncancel() {
		throw new UnsupportedOperationException();
	}

	public void setOncancel(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOncanplay() {
		throw new UnsupportedOperationException();
	}

	public void setOncanplay(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOncanplaythrough() {
		throw new UnsupportedOperationException();
	}

	public void setOncanplaythrough(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnchange() {
		throw new UnsupportedOperationException();
	}

	public void setOnchange(EventListener listener) {
		addEventListener("change", listener, false);
	}

	public EventListener getOnclick() {
		throw new UnsupportedOperationException();
	}

	public void setOnclick(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnclose() {
		throw new UnsupportedOperationException();
	}

	public void setOnclose(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOncontextmenu() {
		throw new UnsupportedOperationException();
	}

	public void setOncontextmenu(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOncuechange() {
		throw new UnsupportedOperationException();
	}

	public void setOncuechange(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOndblclick() {
		throw new UnsupportedOperationException();
	}

	public void setOndblclick(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOndrag() {
		throw new UnsupportedOperationException();
	}

	public void setOndrag(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOndragend() {
		throw new UnsupportedOperationException();
	}

	public void setOndragend(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOndragenter() {
		throw new UnsupportedOperationException();
	}

	public void setOndragenter(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOndragleave() {
		throw new UnsupportedOperationException();
	}

	public void setOndragleave(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOndragover() {
		throw new UnsupportedOperationException();
	}

	public void setOndragover(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOndragstart() {
		throw new UnsupportedOperationException();
	}

	public void setOndragstart(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOndrop() {
		throw new UnsupportedOperationException();
	}

	public void setOndrop(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOndurationchange() {
		throw new UnsupportedOperationException();
	}

	public void setOndurationchange(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnemptied() {
		throw new UnsupportedOperationException();
	}

	public void setOnemptied(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnended() {
		throw new UnsupportedOperationException();
	}

	public void setOnended(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOninput() {
		throw new UnsupportedOperationException();
	}

	public void setOninput(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOninvalid() {
		throw new UnsupportedOperationException();
	}

	public void setOninvalid(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnkeydown() {
		throw new UnsupportedOperationException();
	}

	public void setOnkeydown(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnkeypress() {
		throw new UnsupportedOperationException();
	}

	public void setOnkeypress(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnkeyup() {
		throw new UnsupportedOperationException();
	}

	public void setOnkeyup(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnload() {
		throw new UnsupportedOperationException();
	}

	public void setOnload(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnloadeddata() {
		throw new UnsupportedOperationException();
	}

	public void setOnloadeddata(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnloadedmetadata() {
		throw new UnsupportedOperationException();
	}

	public void setOnloadedmetadata(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnloadstart() {
		throw new UnsupportedOperationException();
	}

	public void setOnloadstart(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnmousedown() {
		throw new UnsupportedOperationException();
	}

	public void setOnmousedown(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnmouseenter() {
		throw new UnsupportedOperationException();
	}

	public void setOnmouseenter(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnmouseleave() {
		throw new UnsupportedOperationException();
	}

	public void setOnmouseleave(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnmousemove() {
		throw new UnsupportedOperationException();
	}

	public void setOnmousemove(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnmouseout() {
		throw new UnsupportedOperationException();
	}

	public void setOnmouseout(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnmouseover() {
		throw new UnsupportedOperationException();
	}

	public void setOnmouseover(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnmouseup() {
		throw new UnsupportedOperationException();
	}

	public void setOnmouseup(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnmousewheel() {
		throw new UnsupportedOperationException();
	}

	public void setOnmousewheel(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnpause() {
		throw new UnsupportedOperationException();
	}

	public void setOnpause(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnplay() {
		throw new UnsupportedOperationException();
	}

	public void setOnplay(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnplaying() {
		throw new UnsupportedOperationException();
	}

	public void setOnplaying(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnprogress() {
		throw new UnsupportedOperationException();
	}

	public void setOnprogress(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnratechange() {
		throw new UnsupportedOperationException();
	}

	public void setOnratechange(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnreset() {
		throw new UnsupportedOperationException();
	}

	public void setOnreset(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnscroll() {
		throw new UnsupportedOperationException();
	}

	public void setOnscroll(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnseeked() {
		throw new UnsupportedOperationException();
	}

	public void setOnseeked(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnseeking() {
		throw new UnsupportedOperationException();
	}

	public void setOnseeking(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnselect() {
		throw new UnsupportedOperationException();
	}

	public void setOnselect(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnshow() {
		throw new UnsupportedOperationException();
	}

	public void setOnshow(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnsort() {
		throw new UnsupportedOperationException();
	}

	public void setOnsort(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnstalled() {
		throw new UnsupportedOperationException();
	}

	public void setOnstalled(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnsubmit() {
		return ownerDocument.getEventDispatcher().getEventListener(this, "submit", false);
	}

	public void setOnsubmit(EventListener listener) {
		ownerDocument.getEventDispatcher().addEventListener(this, "submit", listener, false);
	}

	public EventListener getOnsuspend() {
		throw new UnsupportedOperationException();
	}

	public void setOnsuspend(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOntimeupdate() {
		throw new UnsupportedOperationException();
	}

	public void setOntimeupdate(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnvolumechange() {
		throw new UnsupportedOperationException();
	}

	public void setOnvolumechange(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOnwaiting() {
		throw new UnsupportedOperationException();
	}

	public void setOnwaiting(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOntouchstart() {
		throw new UnsupportedOperationException();
	}

	public void setOntouchstart(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOntouchend() {
		throw new UnsupportedOperationException();
	}

	public void setOntouchend(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOntouchmove() {
		throw new UnsupportedOperationException();
	}

	public void setOntouchmove(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOntouchenter() {
		throw new UnsupportedOperationException();
	}

	public void setOntouchenter(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOntouchleave() {
		throw new UnsupportedOperationException();
	}

	public void setOntouchleave(EventListener handler) {
		throw new UnsupportedOperationException();
	}

	public EventListener getOntouchcancel() {
		throw new UnsupportedOperationException();
	}

	public void setOntouchcancel(EventListener handler) {
		throw new UnsupportedOperationException();
	}
}
