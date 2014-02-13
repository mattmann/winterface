package com.github.snoblind.winterface.rhino;

import com.github.snoblind.winterface.XMLHttpRequest;
import org.apache.commons.collections4.Factory;
import org.mozilla.javascript.BaseFunction;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.apache.commons.lang.Validate.notNull;

public class XMLHttpRequestConstructor extends BaseFunction {

	private static final long serialVersionUID = 1296477902551750478L;

	private static final Logger LOGGER = LoggerFactory.getLogger(XMLHttpRequestConstructor.class);

	private final Factory<? extends XMLHttpRequest> xmlHttpRequestFactory;

	public XMLHttpRequestConstructor(final Factory<? extends XMLHttpRequest> xmlHttpRequestFactory) {
		notNull(this.xmlHttpRequestFactory = xmlHttpRequestFactory);
	}

	public Scriptable construct(final Context context, final Scriptable scope, Object[] args) {
		LOGGER.debug("construct({}, {}, {})", context, scope, args);
		return new XMLHttpRequestAdapter(context, scope, xmlHttpRequestFactory.create());
	}
}
