package com.github.snoblind.winterface.xmlhttp;

import java.net.HttpCookie;
import java.net.URI;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static java.lang.System.currentTimeMillis;
import static org.apache.commons.lang.Validate.notNull;
import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang.builder.ToStringStyle.MULTI_LINE_STYLE;

public class CookieStoreAdapter implements java.net.CookieStore {

	private static final Logger LOGGER = LoggerFactory.getLogger(CookieStoreAdapter.class);

	private final CookieStore cookieStore;

	public CookieStoreAdapter(CookieStore cookieStore) {
		notNull(cookieStore);
		this.cookieStore = cookieStore;
	}

	public void add(URI uri, HttpCookie cookie) {
		LOGGER.debug("add({}, {})", uri, reflectionToString(cookie, MULTI_LINE_STYLE));
		cookieStore.addCookie(new CookieAdapter(cookie));
	}

	public List<HttpCookie> get(URI uri) {
		final Date date = new Date();
		final List<HttpCookie> list = new LinkedList<HttpCookie>();
		for (Cookie cookie: cookieStore.getCookies()) {
			if (matches(cookie, date, uri)) {
				LOGGER.debug("Cookie {} matches {}.", cookie, uri);
				list.add(copy(cookie));
			}
			else {
				LOGGER.debug("Cookie {} does not match {}.", cookie, uri);
			}
		}
		return list;
	}

	private HttpCookie copy(final Cookie c1) {
		final HttpCookie c2 = new HttpCookie(c1.getName(), c1.getValue());
		c2.setComment(c1.getComment());
		c2.setCommentURL(c1.getCommentURL());
		c2.setDiscard(!c1.isPersistent());
		c2.setDomain(c1.getDomain());
		c2.setMaxAge(maxAge(c1));
		c2.setPath(c1.getPath());
		c2.setPortlist(join(c1.getPorts(), ","));
		c2.setSecure(c1.isSecure());
		c2.setVersion(c1.getVersion());
		return c2;
	}

	private long maxAge(final Cookie cookie) {
		final Date expiryDate = cookie.getExpiryDate();
		if (expiryDate == null) {
			return -1;
		}
		return Math.round(0.001 * (expiryDate.getTime() - currentTimeMillis()));
	}
	
	private String join(int[] values, String separator) {
		final StringBuilder builder = new StringBuilder();
		for (int value: values) {
			if (builder.length() > 0) {
				builder.append(separator);
			}
			builder.append(value);
		}
		return builder.toString();
	}

	private boolean matches(Cookie cookie, Date date, URI uri) {
		if (cookie.isExpired(date)) {
			return false;
		}
		if (!uri.getHost().endsWith(cookie.getDomain())) {
			return false;
		}
		if (!uri.getPath().startsWith(cookie.getPath())) {
			return false;
		}
// TODO
//		cookie.getPorts();
//		cookie.isSecure();
		return true;
	}

	public List<HttpCookie> getCookies() {
		throw new UnsupportedOperationException();
	}

	public List<URI> getURIs() {
		throw new UnsupportedOperationException();
	}

	public boolean remove(URI uri, HttpCookie cookie) {
		throw new UnsupportedOperationException();
	}

	public boolean removeAll() {
		throw new UnsupportedOperationException();
	}
}
