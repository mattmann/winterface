package com.github.snoblind.winterface.nashorn;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.script.ScriptEngine;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import static org.apache.commons.lang.Validate.notNull;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.apache.commons.lang.StringUtils.isNotBlank;

public class NashornConsole extends JComponent {

	private static final long serialVersionUID = -4800026719341735054L;

	private final ScriptEngine scriptEngine;
	private final JPanel panel;
	private final JTextField textField;

	public NashornConsole(ScriptEngine scriptEngine) {
		notNull(scriptEngine);
		this.scriptEngine = scriptEngine;
		this.panel = new JPanel();
		this.textField = new JTextField(EMPTY);
		setLayout(new BorderLayout());
		add(panel, NORTH);
		add(textField, SOUTH);
		final Container container = this;
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent event) {
				final String text = textField.getText();
				if (isNotBlank(text)) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							Entry entry;
							try {
								entry = new Entry(text, scriptEngine.eval(text));
							}
							catch (Exception exception) {
								exception.printStackTrace();
								entry = new Entry(text, exception);
							}
							panel.add(entry);
							panel.invalidate();
							container.invalidate();
							container.getParent().revalidate();
							container.getParent().doLayout();
						}
					});
					textField.setText(EMPTY);
				}
			}
		});
	}

	public ScriptEngine getScriptEngine() {
		return scriptEngine;
	}

	private static class Entry extends JComponent {

		private static final long serialVersionUID = 765883946551551656L;

		public Entry(final String script, final Object result) {
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			add(new JLabel(script));
			add(new JLabel(String.valueOf(result)));
		}
		
		public Entry(final String script, final Exception exception) {
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			add(new JLabel(script));
			add(new JLabel(String.valueOf(exception)));
		}
	}
}
