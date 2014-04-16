package com.github.snoblind.winterface.nashorn;

import com.github.snoblind.winterface.Event;
import com.github.snoblind.winterface.event.EventDispatcher;
import com.github.snoblind.winterface.EventListener;
import com.github.snoblind.winterface.event.MapEventDispatcher;
import com.github.snoblind.winterface.spi.HTMLParser;
import com.github.snoblind.winterface.xmlhttp.ApacheCommonsXMLHttpRequest;
import com.github.snoblind.winterface.XMLHttpRequest;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Insets;
import java.util.Map;
import java.util.Timer;
import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.plaf.FontUIResource;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.functors.ConstantFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import static javax.script.ScriptContext.ENGINE_SCOPE;

public final class NashornDemo {

	private static Logger LOGGER = LoggerFactory.getLogger(NashornDemo.class);

	public static void main(String[] args) throws ParserConfigurationException, TransformerException {
		System.setProperty("awt.useSystemAAFontSettings","on");
		System.setProperty("swing.aatext", "true");
		System.setProperty("swing.defaultlaf", "javax.swing.plaf.nimbus.NimbusLookAndFeel");
		final Font font = new FontUIResource("Georgia", Font.BOLD, 16);
		final UIDefaults defaults = UIManager.getDefaults();
		for (Map.Entry<Object, Object> entry: defaults.entrySet()) {
			final CharSequence key = (CharSequence) entry.getKey();
			if (key.toString().endsWith(".font")) {
				entry.setValue(font);
			}
		}
		final Timer timer = new Timer();
		final CookieStore cookieStore = new BasicCookieStore();
		final HttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				shutdown(httpClient);
				cancel(timer);
			}
		});
		final EventDispatcher eventDispatcher = new MapEventDispatcher();
		final HTMLParser parser = new NashornHTMLParser();
		final Factory<HTMLParser> parserFactory = ConstantFactory.constantFactory(parser);
		final Factory<XMLHttpRequest> xmlHttpRequestFactory = new Factory<XMLHttpRequest>() {
			public XMLHttpRequest create() {
				return ApacheCommonsXMLHttpRequest.builder()
						.client(httpClient)
						.eventDispatcher(eventDispatcher)
						.parserFactory(parserFactory)
						.build();
			}
		};
		final NashornLocation location = new NashornLocation(xmlHttpRequestFactory);
		final NashornWindow window = new NashornWindow();
		window.setLocation(location);
		location.setOnreadystatechange(new EventListener() {
			public void handleEvent(Event event1) {
				final int readyState = ((XMLHttpRequest) event1.getTarget()).getReadyState();
				if (4 == readyState) {
					final NashornDocument document = (NashornDocument) location.getXmlHttpRequest().getResponseXML();
					removeWhitespace(document, parser);
					window.setDocument(document);
					final Event event2 = eventDispatcher.createEvent("Event");
					event2.initEvent("load", false, false);
					event2.setTarget(window);
					window.dispatchEvent(event2);
				}
			}
		});
		window.setEventDispatcher(eventDispatcher);
		final ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		final ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
		final Bindings bindings = scriptEngine.getBindings(ENGINE_SCOPE);
		bindings.put("window", window);
		final JScrollPane scrollPane = new JScrollPane();
		final JTree tree = new JTree();
		window.setOnload(new EventListener() {
			public void handleEvent(Event event) {
				tree.setModel(new DocumentModel(window.getDocument()));
				scrollPane.setViewportView(tree);
			}
		});
		window.setOnunload(new EventListener() {
			public void handleEvent(Event event) {
				System.err.println(event);
			}
		});
		final JTextField textField = new JTextField(/* window.getLocation().getHref() */);
		textField.setMargin(new Insets(4, 4, 4, 4));
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent event) {
				try {
					location.setHref(event.getActionCommand());
				}
				catch (Exception x) {
					x.printStackTrace();
				}
			}
		});
		final JFrame frame = new JFrame("Winterface Demonstration");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(textField, BorderLayout.NORTH);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.setSize(1600, 1200);
		WindowUtils.centerOnScreenAndShow(frame);
	}

	private static void removeWhitespace(final NashornDocument document, final HTMLParser parser) {
		removeWhitespace(document.getDocumentElement(), parser);
	}

	private static void removeWhitespace(final Node parentNode, final HTMLParser parser) {
		final NodeList childNodes = parentNode.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			final Node childNode = childNodes.item(i);
			if (childNode instanceof CharacterData) {
				final String data = ((CharacterData) childNode).getData();
				if (StringUtils.isWhitespace(data)) {
					parentNode.removeChild(childNode);
					i--;
				}
			}
			else {
				removeWhitespace(childNode, parser);
			}
		}
	}

	@SuppressWarnings("deprecation")
	private static void shutdown(HttpClient httpClient) {
		try {
			LOGGER.debug("Shutting down HTTP connection manager.");
			httpClient.getConnectionManager().shutdown();
			LOGGER.debug("Shutdown of HTTP connection manager completed without incident.");
		}
		catch (Exception x) {
			x.printStackTrace();
		}
	}

	private static void cancel(Timer timer) {
		try {
			LOGGER.debug("Cancelling timer.");
			timer.cancel();
			LOGGER.debug("Cancelled timer.");
		}
		catch (Exception x) {
			x.printStackTrace();
		}
	}
}
