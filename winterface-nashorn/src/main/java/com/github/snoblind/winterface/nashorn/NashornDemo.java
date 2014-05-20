package com.github.snoblind.winterface.nashorn;

import com.github.snoblind.winterface.cssparser.CSSParserImpl;
import com.github.snoblind.winterface.event.EventDispatcher;
import com.github.snoblind.winterface.event.ExtendedEvent;
import com.github.snoblind.winterface.event.MapEventDispatcher;
import com.github.snoblind.winterface.spi.CSSParser;
import com.github.snoblind.winterface.spi.HTMLParser;
import com.github.snoblind.winterface.spi.HTMLSerializer;
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
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.tree.TreeModel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.commons.collections4.Factory;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import static javax.script.ScriptContext.ENGINE_SCOPE;
import static org.apache.commons.collections4.functors.ConstantFactory.constantFactory;

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
		final HTMLSerializer serializer = new NashornHTMLSerializer();
		final HTMLParser parser = new NashornHTMLParser(serializer);
		final Factory<XMLHttpRequest> xmlHttpRequestFactory = new Factory<XMLHttpRequest>() {
			public XMLHttpRequest create() {
				return ApacheCommonsXMLHttpRequest.builder()
						.client(httpClient)
						.eventDispatcher(eventDispatcher)
						.parserFactory(constantFactory(parser))
						.build();
			}
		};
		final NashornLocation location = new NashornLocation(xmlHttpRequestFactory);
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
		final CSSParser cssParser = new CSSParserImpl();
		final NashornQuerySelector querySelector = new NashornQuerySelector();
		final NashornWindow window = new NashornWindow();
		window.setHTMLParser(parser);
		window.setLocation(location);
		location.setOnreadystatechange(new EventListener() {
			public void handleEvent(Event event1) {
				final int readyState = ((XMLHttpRequest) event1.getTarget()).getReadyState();
				switch (readyState) {
				case 1:
				case 2:
					break;
				case 3:
					textField.setText(location.getHref());
					break;
				case 4:
					final NashornDocument document = (NashornDocument) location.getXmlHttpRequest().getResponseXML();
					document.setDefaultView(window);
					document.setCssParser(cssParser);
					document.setEventDispatcher(eventDispatcher);
					document.setQuerySelector(querySelector);
					window.setDocument(document);
					final ExtendedEvent event2 = eventDispatcher.createEvent("Event");
					event2.initEvent("load", false, false);
					event2.setTarget(window);
					window.dispatchEvent(event2);
					break;
				default:
					throw new IllegalArgumentException(Integer.toString(readyState));
				}
			}
		});
		window.setEventDispatcher(eventDispatcher);
		final ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		final ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
		final Bindings bindings = scriptEngine.getBindings(ENGINE_SCOPE);
		bindings.put("location", location);
		bindings.put("this", window);
		bindings.put("window", window);
		window.setScriptEngine(scriptEngine);
		final JScrollPane scrollPane = new JScrollPane();
		final JTree tree = new JTree();
		final TreeModelListener treeModelListener = new TreeModelListener() {

			public void treeNodesChanged(TreeModelEvent e) {
				System.out.println(e);
			}

			public void treeNodesInserted(TreeModelEvent e) {
				System.out.println(e);
			}

			public void treeNodesRemoved(TreeModelEvent e) {
				System.out.println(e);
			}

			public void treeStructureChanged(TreeModelEvent e) {
				System.out.println(e);
			}
		};
		window.setOnunload(new EventListener() {
			public void handleEvent(Event event) {
				System.err.println(event);
			}
		});
		final NashornConsole console = new NashornConsole(scriptEngine);
		final JFrame frame = new JFrame("Winterface Demonstration");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(textField, BorderLayout.NORTH);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.getContentPane().add(console, BorderLayout.SOUTH);
		frame.setSize(1600, 1200);
		window.setOnload(new EventListener() {
			public void handleEvent(Event event) {
				final NashornDocument document = window.getDocument();
				frame.setTitle(document.getTitle());
				bindings.put("document", document);
				final TreeModel model = new DocumentModel(document);
				model.addTreeModelListener(treeModelListener);
				tree.setModel(model);
				for (int i = 0; i < tree.getRowCount(); i++) {
					tree.expandPath(tree.getPathForRow(i));
				}
				scrollPane.setViewportView(tree);
			}
		});
		WindowUtils.centerOnScreenAndShow(frame);
		location.setHref("nytimes.com");
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
