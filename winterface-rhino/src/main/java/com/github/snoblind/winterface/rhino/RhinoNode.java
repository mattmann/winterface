package com.github.snoblind.winterface.rhino;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;
import static com.github.snoblind.winterface.util.ReflectionUtils.getPropertyValue;
import static com.github.snoblind.winterface.util.ReflectionUtils.propertyDescriptorsByName;
import static com.github.snoblind.winterface.util.ReflectionUtils.setPropertyValue;
import static java.lang.String.format;
import static java.util.Collections.unmodifiableMap;

public abstract class RhinoNode<N extends Node> extends ScriptableObject implements Node {

	private static final long serialVersionUID = 7612349807292023689L;

	private static final Logger LOGGER = LoggerFactory.getLogger(RhinoNode.class);

	private final Map<String, PropertyDescriptor> propertyDescriptorsByName = propertyDescriptorsByName(this);
	private final Map<String, Function> functionsByName;

	protected N node;

	protected RhinoNode(N node) {
		this.node = node;
		try {
			functionsByName = unmodifiableMap(functionsByName());
		}
		catch (NoSuchMethodException x) {
			throw new RuntimeException(x);
		}
	}

	protected RhinoNode() {
		this(null);
	}

	protected MethodFunction newMethodFunction(String name, Class<?>... parameterTypes) throws NoSuchMethodException {
		return new MethodFunction(this, getClass().getMethod(name, parameterTypes));
	}

	protected Map<String, Function> functionsByName() throws NoSuchMethodException {
		final Map<String, Function> map = new HashMap<String, Function>();
		map.put("appendChild", newMethodFunction("appendChild", Node.class));
		map.put("cloneNode", newMethodFunction("cloneNode", boolean.class));
		map.put("removeChild", newMethodFunction("removeChild", Node.class));
		return map;
	}

	public abstract RhinoDocument getOwnerDocument();

	public String getInnerText() {
		return getOwnerDocument().getParser().getInnerText(this);
	}

	public void setInnerText(String text) {
		getOwnerDocument().getParser().setInnerText(this, text);
	}

	public String getTextContent() throws DOMException {
		return getInnerText();
	}

	public void setTextContent(String textContent) throws DOMException {
		setInnerText(textContent);
	}

	protected Node adapt(Node node) {
		return getOwnerDocument().getNodeAdapterFactory().adapt(node, getOwnerDocument());
	}

	public NodeList getChildNodes() {
		final NodeList childNodes = node.getChildNodes();
		return new NodeList() {

			public Node item(int index) {
				return adapt(childNodes.item(index));
			}

			public int getLength() {
				return childNodes.getLength();
			}
		};
	}

	public String getNodeName() {
		return node.getNodeName();
	}

	public String getNodeValue() throws DOMException {
		return node.getNodeValue();
	}

	public void setNodeValue(String nodeValue) throws DOMException {
		node.setNodeValue(nodeValue);
	}

	public short getNodeType() {
		return node.getNodeType();
	}

	public Node getParentNode() {
		return adapt(node.getParentNode());
	}

	public Node getFirstChild() {
		return adapt(node.getFirstChild());
	}

	public Node getLastChild() {
		return adapt(node.getLastChild());
	}

	public Node getPreviousSibling() {
		return adapt(node.getPreviousSibling());
	}

	public Node getNextSibling() {
		return adapt(node.getNextSibling());
	}

	public boolean hasChildNodes() {
		return node.hasChildNodes();
	}

	public Object setUserData(String key, Object data, UserDataHandler handler) {
		return node.setUserData(key, data, handler);
	}

	public Object getUserData(String key) {
		return node.getUserData(key);
	}

	public Object getFeature(String feature, String version) {
		return node.getFeature(feature, version);
	}

	public boolean isSupported(String feature, String version) {
		return node.isSupported(feature, version);
	}

	public String getBaseURI() {
		return node.getBaseURI();
	}

	public boolean isDefaultNamespace(String namespaceURI) {
		return node.isDefaultNamespace(namespaceURI);
	}

	public String lookupPrefix(String namespaceURI) {
		return node.lookupPrefix(namespaceURI);
	}

	public String lookupNamespaceURI(String prefix) {
		return node.lookupNamespaceURI(prefix);
	}

	
	public String getPrefix() {
		return node.getPrefix();
	}

	public void setPrefix(String prefix) throws DOMException {
		node.setPrefix(prefix);
	}

	public String getLocalName() {
		return node.getLocalName();
	}

	public void normalize() {
		node.normalize();
	}

	public boolean hasAttributes() {
		return node.hasAttributes();
	}

	public String getNamespaceURI() {
		return node.getNamespaceURI();
	}

	public NamedNodeMap getAttributes() {
		return node.getAttributes();
	}

	public Node insertBefore(Node newChild, Node refChild) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public Node removeChild(Node child) throws DOMException {
		if (child instanceof RhinoNode) {
			child = ((RhinoNode<?>) child).node;
		}
		return adapt(node.removeChild(child));
	}

	public Node appendChild(Node child) throws DOMException {
		if (child instanceof RhinoNode) {
			child = ((RhinoNode<?>) child).node;
		}
		return adapt(node.appendChild(child));
	}

	public Node cloneNode(boolean deep) {
		return adapt(node.cloneNode(deep));
	}

	public short compareDocumentPosition(Node other) throws DOMException {
		throw new UnsupportedOperationException();
	}

	public boolean isSameNode(Node other) {
		throw new UnsupportedOperationException();
	}

	public boolean isEqualNode(Node arg) {
		throw new UnsupportedOperationException();
	}

	public Object get(String name, Scriptable start) {
		LOGGER.debug("get({}, {})", name, start);
		if (propertyDescriptorsByName.containsKey(name)) {
			LOGGER.info("Instances of {} have a property named \"{}\".", getClass(), name);
			return getPropertyValue(this, propertyDescriptorsByName.get(name));
		}
		if (functionsByName.containsKey(name)) {
			LOGGER.info("Found function named \"{}\" in Map.", name);
			return functionsByName.get(name);
		}
		final Object result = super.get(name, start);
		if (NOT_FOUND.equals(result)) {
			LOGGER.warn("Node {} has no such member \"{}\".", this, name);
		}
		return result;
	}

	public void put(String name, Scriptable start, Object value) {
		LOGGER.debug("{}.put({}, {}, {})", getClass().getName(), name, start, value);
		if (propertyDescriptorsByName.containsKey(name)) {
			LOGGER.info("Instances of {} have a property named \"{}\".", getClass(), name);
			final PropertyDescriptor descriptor = propertyDescriptorsByName.get(name);
			final Class<?> type = descriptor.getPropertyType();
			setPropertyValue(this, descriptor, convert(value, type));
		}
		else {
			super.put(name, start, value);
		}
	}

	private Object convert(Object value, Class<?> type) {
		if (value == null) {
			return null;
		}
		if (type.isAssignableFrom(value.getClass())) {
			return value;
		}
		if (String.class.equals(type) && value instanceof CharSequence) {
			return value.toString();
		}
		throw new IllegalArgumentException(format("Cannot convert value %s (instance of %s) to type %s.", value, value.getClass(), type));
	}

	public Object getDefaultValue(Class<?> type) {
		if (String.class.equals(type)) {
			return toString();
		}
		return super.getDefaultValue(type);
	}
}
