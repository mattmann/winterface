package com.github.snoblind.winterface.demo;

import com.github.snoblind.winterface.Event;
import com.github.snoblind.winterface.event.DefaultEvent;
import com.github.snoblind.winterface.event.DefaultGlobalEventHandlers;
import com.github.snoblind.winterface.event.DefaultWindowEventHandlers;
import com.github.snoblind.winterface.event.EventDispatcher;
import com.github.snoblind.winterface.EventListener;
import com.github.snoblind.winterface.event.MapEventDispatcher;
import com.github.snoblind.winterface.GlobalEventHandlers;
import com.github.snoblind.winterface.jodd.JoddQuerySelector;
import com.github.snoblind.winterface.jsoup.JSoupHTMLParser;
import com.github.snoblind.winterface.Navigator;
import com.github.snoblind.winterface.rhino.Console;
import com.github.snoblind.winterface.rhino.PrintStreamConsole;
import com.github.snoblind.winterface.rhino.RhinoNavigator;
import com.github.snoblind.winterface.rhino.RhinoWindow;
import com.github.snoblind.winterface.rhino.RhinoWindowEnvironment;
import com.github.snoblind.winterface.rhino.XMLHttpRequestConstructor;
import com.github.snoblind.winterface.spi.HTMLParser;
import com.github.snoblind.winterface.spi.QuerySelector;
import com.github.snoblind.winterface.WindowEventHandlers;
import com.github.snoblind.winterface.xmlhttp.ApacheCommonsXMLHttpRequest;
import com.github.snoblind.winterface.xmlhttp.CookieStoreAdapter;
import com.github.snoblind.winterface.XMLHttpRequest;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.Callable;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import org.apache.commons.collections4.Factory;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.mozilla.javascript.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.mozilla.javascript.ScriptableObject.defineProperty;
import static org.mozilla.javascript.ScriptableObject.DONTENUM;
import static org.mozilla.javascript.ScriptableObject.putProperty;

public final class SwingDemoMain {

	private static Logger LOGGER = LoggerFactory.getLogger(SwingDemoMain.class);

	public static void main(String[] args) {
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
				exitContext();
				shutdown(httpClient);
				cancel(timer);
			}
		});
		final GlobalEventHandlers globalEventHandlers = new DefaultGlobalEventHandlers();
		final WindowEventHandlers windowEventHandlers = new DefaultWindowEventHandlers();
		final Navigator navigator = new RhinoNavigator();
		final Console console = new PrintStreamConsole();
		final QuerySelector querySelector = new JoddQuerySelector();
		final EventDispatcher eventDispatcher = new MapEventDispatcher();
		final Factory<Event> eventFactory = new Factory<Event>() {
			public Event create() {
				return new DefaultEvent();
			}
		};
		final Factory<HTMLParser> parserFactory = new Factory<HTMLParser>() {
			public HTMLParser create() {
				return new JSoupHTMLParser(eventDispatcher);
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
		final RhinoWindowEnvironment environment = RhinoWindowEnvironment.builder()
				.console(console)
				.cookieStore(new CookieStoreAdapter(cookieStore))
				.eventDispatcher(eventDispatcher)
				.globalEventHandlers(globalEventHandlers)
				.navigator(navigator)
				.parserFactory(parserFactory)
				.querySelector(querySelector)
				.timer(timer)
				.windowEventHandlers(windowEventHandlers)
				.xmlHttpRequestFactory(xmlHttpRequestFactory)
				.build();
		final RhinoWindow window;
		try {
			window = environment.open("about:blank", null, null, false);
		}
		catch (IOException x) {
			x.printStackTrace();
			System.exit(1);
			return;
		}
        final JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
		window.addEventListener("load", new EventListener() {
			public void handleEvent(Event event) {
				try {
					editorPane.setPage(new URL(window.getLocation().getHref()));
				}
				catch (IOException x) {
					x.printStackTrace();
				}
//				editorPane.setContentType("text/html");
//				editorPane.setText(parserFactory.create().getOuterHTML(window.getDocument().getDocumentElement()));
			}
		}, false);
		window.addEventListener("unload", new EventListener() {
			public void handleEvent(Event event) {
				System.err.println(event);
			}
		}, false);
		window.setOnload(new EventListener() {
			public void handleEvent(Event event) {
				System.err.println(event);
			}
		});
		window.setOnunload(new EventListener() {
			public void handleEvent(Event event) {
				System.err.println(event);
			}
		});
		final XMLHttpRequestConstructor requestConstructor = new XMLHttpRequestConstructor(xmlHttpRequestFactory);
        final Context context = Context.enter();
		LOGGER.debug("Rhino Context entered.");
		context.initStandardObjects(window);
		LOGGER.debug("Standard objects initialized.");
		putProperty(window, "console", console);
		putProperty(window, "err", System.err);
		putProperty(window, "navigator", navigator);
		putProperty(window, "out", System.out);
		putProperty(window, "window", window);
		defineProperty(window, "XMLHttpRequest", requestConstructor, DONTENUM);
		final LinkedList<Callable<?>> callables = new LinkedList<Callable<?>>();
		final JTextField textField = new JTextField(window.getLocation().getHref());
		textField.setMargin(new Insets(4, 4, 4, 4));
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent event) {
				callables.add(new Callable<Void>() {
					public Void call() throws Exception {
						window.getLocation().setHref(event.getActionCommand());
						return null;
					}
				});
			}
		});
		final JScrollPane scrollPane = new JScrollPane(editorPane);
		final GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		final GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
		final GraphicsConfiguration graphicsConfiguration = graphicsDevice.getDefaultConfiguration();
		final JFrame frame = new JFrame("Winterface Demonstration");
		final Dimension screenSize = frame.getToolkit().getScreenSize();
		final Insets screenInsets = frame.getToolkit().getScreenInsets(graphicsConfiguration);
		final int width = 1600;
		final int height = 1200;
		final int x = (int) Math.round((0.5) * (screenSize.width - screenInsets.left - screenInsets.right - width));
		final int y = (int) Math.round((0.5) * (screenSize.height - screenInsets.top - screenInsets.bottom - height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(textField, BorderLayout.NORTH);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.setBounds(x, y, width, height);
		frame.setVisible(true);
		while (true) {
			while (!callables.isEmpty()) {
				try {
					callables.removeFirst().call();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(100);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static void exitContext() {
		if (Context.getCurrentContext() == null) {
			LOGGER.debug("No current Rhino context. Doing nothing.");
			return;
		}
		try {
			LOGGER.debug("Exiting Rhino context.");
			Context.exit();
			LOGGER.debug("Exited Rhino context.");
		}
		catch (Exception x) {
			x.printStackTrace();
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
