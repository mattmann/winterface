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

	String getAlign();
	void setAlign(String align);

	String getBgColor();
	void setBgColor(String bgColor);

	String getBorder();
	void setBorder(String border);

	String getCellPadding();
	void setCellPadding(String cellPadding);

	String getCellSpacing();
	void setCellSpacing(String cellSpacing);

	String getFrame();
	void setFrame(String frame);

	String getRules();
	void setRules(String rules);

	String getSummary();
	void setSummary(String summary);

	String getWidth();
	void setWidth(String width);

	HTMLElement createTHead();

	void deleteTHead();

	HTMLElement createTFoot();

	void deleteTFoot();

	HTMLElement createCaption();

	void deleteCaption();

	HTMLElement insertRow(long index) throws DOMException;

	void deleteRow(long index) throws DOMException;
}
