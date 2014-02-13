package com.github.snoblind.winterface.spi;

import com.github.snoblind.winterface.ExtendedHTMLElement;
import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLDocument;

public interface HTMLParser {

	HTMLDocument parse(String html, String baseUri);
	
	String getInnerText(Node node);
	void setInnerText(Node node, String innerText);
	
	String getInnerHTML(ExtendedHTMLElement element);
	void setInnerHTML(ExtendedHTMLElement element, String innerHTML);

	String getOuterHTML(ExtendedHTMLElement element);
	Node setOuterHTML(ExtendedHTMLElement element, String outerHTML);
}
