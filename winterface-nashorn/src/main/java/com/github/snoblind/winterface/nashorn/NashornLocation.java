package com.github.snoblind.winterface.nashorn;

import com.github.snoblind.winterface.XMLHttpRequest;
import com.github.snoblind.winterface.abstracts.AbstractLocation;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.collections4.Factory;
import org.w3c.dom.events.EventListener;
import static org.apache.commons.lang.Validate.isTrue;
import static org.apache.commons.lang.Validate.notNull;

public class NashornLocation extends AbstractLocation {

	private final Factory<XMLHttpRequest> xmlHttpRequestFactory;

	private XMLHttpRequest xmlHttpRequest;
	private EventListener onreadystatechange;
	
	public NashornLocation(Factory<XMLHttpRequest> xmlHttpRequestFactory) {
		notNull(xmlHttpRequestFactory);
		this.xmlHttpRequestFactory = xmlHttpRequestFactory;
	}

	public XMLHttpRequest getXmlHttpRequest() {
		return xmlHttpRequest;
	}

	public EventListener getOnreadystatechange() {
		return onreadystatechange;
	}

	public void setOnreadystatechange(EventListener onreadystatechange) {
		this.onreadystatechange = onreadystatechange;
	}

	private void assign(final URL url) throws IOException {
		xmlHttpRequest = xmlHttpRequestFactory.create();
		xmlHttpRequest.open("GET", url.toString(), false, null, null);
		xmlHttpRequest.setOnreadystatechange(onreadystatechange);
		href = url.toString();
		xmlHttpRequest.send(null);
		isTrue(4 == xmlHttpRequest.getReadyState());
	}

	private URL toURL(final String url) throws MalformedURLException {
		try {
			return new URL(url);
		}
		catch (MalformedURLException x) {
			if (x.getMessage().startsWith("no protocol: ")) {
				return new URL(String.format("http://%s", url));
			}
			throw x;
		}
	}

	public void assign(final String url) {
		try {
			assign(toURL(url));
		}
		catch (IOException x) {
			throw new RuntimeException(x);
		}
	}
}