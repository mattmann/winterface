package com.github.snoblind.winterface.nashorn;

import com.github.snoblind.winterface.Event;
import com.github.snoblind.winterface.event.DefaultEvent;
import com.github.snoblind.winterface.event.DefaultGlobalEventHandlers;
import com.github.snoblind.winterface.event.DefaultWindowEventHandlers;
import com.github.snoblind.winterface.event.EventDispatcher;
import com.github.snoblind.winterface.EventListener;
import com.github.snoblind.winterface.event.MapEventDispatcher;
import com.github.snoblind.winterface.GlobalEventHandlers;
import com.github.snoblind.winterface.Navigator;
import com.github.snoblind.winterface.spi.HTMLParser;
import com.github.snoblind.winterface.spi.JAXPHTMLParser;
import com.github.snoblind.winterface.spi.QuerySelector;
import com.github.snoblind.winterface.Window;
import com.github.snoblind.winterface.WindowEventHandlers;
import com.github.snoblind.winterface.xmlhttp.ApacheCommonsXMLHttpRequest;
import com.github.snoblind.winterface.xmlhttp.CookieStoreAdapter;
import com.github.snoblind.winterface.XMLHttpRequest;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Timer;
import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.collections4.Factory;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
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
//		final Navigator navigator = new RhinoNavigator();
//		final Console console = new PrintStreamConsole();
//		final QuerySelector querySelector = new JoddQuerySelector();
		final EventDispatcher eventDispatcher = new MapEventDispatcher();
		final Factory<Event> eventFactory = new Factory<Event>() {
			public Event create() {
				return new DefaultEvent();
			}
		};
		final Factory<HTMLParser> parserFactory = new Factory<HTMLParser>() {
			public HTMLParser create() {
				return new NashornHTMLParser();
			}
		};
		final Factory<XMLHttpRequest> xmlHttpRequestFactory = new Factory<XMLHttpRequest>() {
			public XMLHttpRequest create() {
				return ApacheCommonsXMLHttpRequest.builder()
						.client(httpClient)
						.eventFactory(eventFactory)
						.parserFactory(parserFactory)
						.build();
			}
		};
		final NashornLocation location = new NashornLocation(xmlHttpRequestFactory);
//		final RhinoWindowEnvironment environment = RhinoWindowEnvironment.builder()
//				.console(console)
//				.cookieStore(new CookieStoreAdapter(cookieStore))
//				.eventDispatcher(eventDispatcher)
//				.globalEventHandlers(globalEventHandlers)
//				.navigator(navigator)
//				.parserFactory(parserFactory)
//				.querySelector(querySelector)
//				.timer(timer)
//				.windowEventHandlers(windowEventHandlers)
//				.xmlHttpRequestFactory(xmlHttpRequestFactory)
//				.build();
//		final RhinoWindow window;
//		try {
//			window = environment.open("about:blank", null, null, false);
//		}
//		catch (IOException x) {
//			x.printStackTrace();
//			System.exit(1);
//			return;
//		}
		final NashornWindow window = new NashornWindow();
		final ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		final ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
		final Bindings bindings = scriptEngine.getBindings(ENGINE_SCOPE);
		bindings.put("window", window);
		final JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
//		window.addEventListener("load", new EventListener() {
//			public void handleEvent(Event event) {
//				try {
//					editorPane.setPage(new URL(window.getLocation().getHref()));
//				}
//				catch (IOException x) {
//					x.printStackTrace();
//				}
////				editorPane.setContentType("text/html");
////				editorPane.setText(parserFactory.create().getOuterHTML(window.getDocument().getDocumentElement()));
//			}
//		}, false);
//		window.addEventListener("unload", new EventListener() {
//			public void handleEvent(Event event) {
//				System.err.println(event);
//			}
//		}, false);
//		window.setOnload(new EventListener() {
//			public void handleEvent(Event event) {
//				System.err.println(event);
//			}
//		});
//		window.setOnunload(new EventListener() {
//			public void handleEvent(Event event) {
//				System.err.println(event);
//			}
//		});
//		final XMLHttpRequestConstructor requestConstructor = new XMLHttpRequestConstructor(xmlHttpRequestFactory);
//        final Context context = Context.enter();
//		LOGGER.debug("Rhino Context entered.");
//		context.initStandardObjects(window);
//		LOGGER.debug("Standard objects initialized.");
//		putProperty(window, "console", console);
//		putProperty(window, "err", System.err);
//		putProperty(window, "navigator", navigator);
//		putProperty(window, "out", System.out);
//		putProperty(window, "window", window);
//		defineProperty(window, "XMLHttpRequest", requestConstructor, DONTENUM);
//		final LinkedList<Callable<?>> callables = new LinkedList<Callable<?>>();
		final JTextField textField = new JTextField(/* window.getLocation().getHref() */);
		textField.setMargin(new Insets(4, 4, 4, 4));
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent event) {
				try {
					location.setHref(event.getActionCommand());
					System.out.println(location.getXmlHttpRequest().getResponseText());
					final Document responseXML = location.getXmlHttpRequest().getResponseXML();
					final Transformer transformer = TransformerFactory.newInstance().newTransformer();
					transformer.transform(new DOMSource(responseXML), new StreamResult(System.out));
					window.setDocument((NashornDocument) responseXML);
					
				}
				catch (Exception x) {
					x.printStackTrace();
				}
			}
		});
		final JScrollPane scrollPane = new JScrollPane(editorPane);
		final JFrame frame = new JFrame("Winterface Demonstration");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(textField, BorderLayout.NORTH);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.setSize(1600, 1200);
		WindowUtils.centerOnScreenAndShow(frame);
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
