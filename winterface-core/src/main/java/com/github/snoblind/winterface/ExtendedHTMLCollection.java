package com.github.snoblind.winterface;

import java.util.List;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLCollection;

public interface ExtendedHTMLCollection extends HTMLCollection, NodeList, List<Node> {

}
