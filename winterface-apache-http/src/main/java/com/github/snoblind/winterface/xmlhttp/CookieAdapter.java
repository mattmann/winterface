package com.github.snoblind.winterface.xmlhttp;

import java.net.HttpCookie;
import java.util.Date;
import org.apache.http.cookie.Cookie;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang.builder.ToStringStyle.MULTI_LINE_STYLE;
import static org.apache.commons.lang.Validate.notNull;

public class CookieAdapter implements Cookie {

	private final HttpCookie cookie;
	
	public CookieAdapter(HttpCookie cookie) {
		notNull(cookie);
		this.cookie = cookie;
	}

	public Date getExpiryDate() {
		if (-1 == cookie.getMaxAge()) {
			return null;
		}
		return new Date(System.currentTimeMillis() + 1000 * cookie.getMaxAge());
	}

	public boolean isExpired(Date date) {
		final Date expiryDate = getExpiryDate();
		return expiryDate != null && expiryDate.before(date);
	}

	public boolean isPersistent() {
		return !cookie.getDiscard();
	}

	public boolean isSecure() {
		return cookie.getSecure();
	}

	public String getName() {
		return cookie.getName();
	}

	public String getValue() {
		return cookie.getValue();
	}

	public String getComment() {
		return cookie.getComment();
	}

	public String getCommentURL() {
		return cookie.getCommentURL();
	}

	public String getDomain() {
		return cookie.getDomain();
	}

	public String getPath() {
		return cookie.getPath();
	}

	public int[] getPorts() {
		if (isBlank(cookie.getPortlist())) {
			return new int[0];
		}
		final String[] strings = cookie.getPortlist().split(",");
		final int[] integers = new int[strings.length];
		for (int i = 0; i < integers.length; i++) {
			integers[i] = Integer.parseInt(strings[i]);
		}
		return integers;
	}

	public int getVersion() {
		return cookie.getVersion();
	}

	public String toString() {
		return reflectionToString(this, MULTI_LINE_STYLE);
	}
}
