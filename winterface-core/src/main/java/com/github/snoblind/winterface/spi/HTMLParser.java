package com.github.snoblind.winterface.spi;

import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface HTMLParser {

	Document parse(String html, String baseUri);
	
	String getInnerText(Node node);
	void setInnerText(Node node, String innerText);
	
	String getInnerHTML(Element element);
	void setInnerHTML(Element element, String innerHTML);

	String getOuterHTML(Element element);
	Node setOuterHTML(Element element, String outerHTML);
}
