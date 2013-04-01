package com.github.mattmann.winterface.jsoup;

import org.jsoup.nodes.Element;

import com.github.mattmann.winterface.Attr;
import com.github.mattmann.winterface.DOMException;
import com.github.mattmann.winterface.Event;
import com.github.mattmann.winterface.EventException;
import com.github.mattmann.winterface.EventListener;
import com.github.mattmann.winterface.HTMLElement;
import com.github.mattmann.winterface.NodeList;
import com.github.mattmann.winterface.OnErrorEventHandler;

import static org.apache.commons.lang.Validate.isTrue;
import static org.apache.commons.lang.Validate.notNull;

public class JSoupElement extends JSoupNode<Element> implements HTMLElement {

	protected final JSoupDocument ownerDocument;

	public JSoupElement(Element element, JSoupDocument ownerDocument) {
		super(element);
		notNull(this.ownerDocument = ownerDocument);
	}

	public JSoupDocument getOwnerDocument() {
		return ownerDocument;
	}

	public CharSequence getId() {
		throw new UnsupportedOperationException();
	}

	public void setId(CharSequence id) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getTitle() {
		throw new UnsupportedOperationException();
	}

	public void setTitle(CharSequence title) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getLang() {
		throw new UnsupportedOperationException();
	}

	public void setLang(CharSequence lang) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getDir() {
		throw new UnsupportedOperationException();
	}

	public void setDir(CharSequence dir) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getClassName() {
		throw new UnsupportedOperationException();
	}

	public void setClassName(CharSequence className) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getInnerHTML() {
		return node.html();
	}

	public void setInnerHTML(CharSequence html) {
		node.html(html.toString());
	}

	public CharSequence getOuterHTML() {
		return node.outerHtml();
	}

	public void setOuterHTML(CharSequence outerHTML) {
		throw new UnsupportedOperationException();
	}

	public Attr getAttributeNode(CharSequence name) {
		throw new UnsupportedOperationException();
	}

	public Attr getAttributeNodeNS(CharSequence namespaceURI,
			CharSequence localName) {
		throw new UnsupportedOperationException();
	}

	public Attr removeAttributeNode(Attr oldAttr) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Attr setAttributeNode(Attr newAttr) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Attr setAttributeNodeNS(Attr newAttr) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public boolean hasAttribute(CharSequence name) {
		throw new UnsupportedOperationException();
	}

	public boolean hasAttributeNS(CharSequence namespaceURI, CharSequence localName) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getAttribute(CharSequence name) {
		notNull(name);
		return node.attr(name.toString());
	}

	public CharSequence getAttributeNS(CharSequence namespaceURI, CharSequence localName) {
		throw new UnsupportedOperationException();
	}

	public CharSequence getTagName() {
		return node.tagName();
	}

	public NodeList getElementsByTagName(CharSequence name) {
		throw new UnsupportedOperationException();
	}

	public NodeList getElementsByTagNameNS(CharSequence namespaceURI, CharSequence localName) {
		throw new UnsupportedOperationException();
	}

	public void removeAttribute(CharSequence name) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void removeAttributeNS(CharSequence namespaceURI, CharSequence localName) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void setAttribute(CharSequence name, CharSequence value) throws DOMException {
		notNull(name);
		node.attr(name.toString(), value == null ? null : value.toString());
	}

	public void setAttributeNS(CharSequence namespaceURI, CharSequence qualifiedName, CharSequence value) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public void addEventListener(CharSequence type, EventListener listener, boolean useCapture) {
		ownerDocument.eventDispatcher.addEventListener(this, type, listener, useCapture);
	}

	public void removeEventListener(CharSequence type, EventListener listener, boolean useCapture) {
		ownerDocument.eventDispatcher.removeEventListener(this, type, listener, useCapture);
	}

	public boolean dispatchEvent(Event event) throws EventException {
		isTrue(event instanceof JSoupEvent);
		return dispatchEvent((JSoupEvent)event);
	}
	
	protected boolean dispatchEvent(JSoupEvent event) throws EventException {
		event.setTarget(this);
		return ownerDocument.eventDispatcher.dispatchEvent(event);
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

	public void setOnchange(EventListener handler) {
		throw new UnsupportedOperationException();
	}
	
	private EventListener onclick;

	public EventListener getOnclick() {
		return onclick;
	}

	public void setOnclick(EventListener onclick) {
		if (this.onclick != null) {
			removeEventListener("click", this.onclick, false);
		}
		this.onclick = onclick;
		if (this.onclick != null) {
			addEventListener("click", this.onclick, false);
		}
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
		throw new UnsupportedOperationException();
	}

	public void setOnsubmit(EventListener handler) {
		throw new UnsupportedOperationException();
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

	public void setOnerror(EventListener onerror) {
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

	public String toString() {
		return String.format("<%s/>", getTagName());
	}
}
