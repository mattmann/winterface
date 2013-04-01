package com.github.mattmann.winterface.jsoup;

import com.github.mattmann.winterface.EventTarget;
import com.github.mattmann.winterface.event.AbstractEvent;

import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang.builder.ToStringStyle.MULTI_LINE_STYLE;

public class JSoupEvent extends AbstractEvent {

	protected void setTarget(EventTarget target) {
		this.target = target;
	}

	public String toString() {
		return reflectionToString(this, MULTI_LINE_STYLE);
	}
}
