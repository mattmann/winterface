package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.Event;
import com.github.snoblind.winterface.xmlhttp.ApacheCommonsXMLHttpRequest;
import org.apache.commons.collections4.Factory;
import org.apache.http.client.HttpClient;
import org.mozilla.javascript.BaseFunction;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.apache.commons.lang.Validate.notNull;

public class XMLHttpRequestConstructor extends BaseFunction {

	private static final long serialVersionUID = 1296477902551750478L;

	private static final Logger LOGGER = LoggerFactory.getLogger(XMLHttpRequestConstructor.class);

	private final HttpClient client;
	private final Factory<? extends Event> eventFactory;
	

	public XMLHttpRequestConstructor(final HttpClient client, final Factory<? extends Event> eventFactory) {
		notNull(this.client = client);
		notNull(this.eventFactory = eventFactory);
	}

	public Scriptable construct(final Context context, final Scriptable scope, Object[] args) {
		LOGGER.debug("construct({}, {}, {})", context, scope, args);
		return new XMLHttpRequestAdapter(context, scope, new ApacheCommonsXMLHttpRequest(client, eventFactory));
	}
}
