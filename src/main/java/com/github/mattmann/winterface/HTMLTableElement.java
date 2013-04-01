package com.github.mattmann.winterface;

public interface HTMLTableElement extends HTMLElement {

	HTMLTableCaptionElement getCaption();
	void setCaption(HTMLTableCaptionElement caption);

	HTMLTableSectionElement getTHead();
	void setTHead(HTMLTableSectionElement tHead);

	HTMLTableSectionElement getTFoot();
	void setTFoot(HTMLTableSectionElement tFoot);

	HTMLCollection getRows();

	HTMLCollection getTBodies();

	CharSequence getAlign();
	void setAlign(CharSequence align);

	CharSequence getBgColor();
	void setBgColor(CharSequence bgColor);

	CharSequence getBorder();
	void setBorder(CharSequence border);

	CharSequence getCellPadding();
	void setCellPadding(CharSequence cellPadding);

	CharSequence getCellSpacing();
	void setCellSpacing(CharSequence cellSpacing);

	CharSequence getFrame();
	void setFrame(CharSequence frame);

	CharSequence getRules();
	void setRules(CharSequence rules);

	CharSequence getSummary();
	void setSummary(CharSequence summary);

	CharSequence getWidth();
	void setWidth(CharSequence width);

	HTMLElement createTHead();

	void deleteTHead();

	HTMLElement createTFoot();

	void deleteTFoot();

	HTMLElement createCaption();

	void deleteCaption();

	HTMLElement insertRow(long index) throws DOMException;

	void deleteRow(long index) throws DOMException;
}
