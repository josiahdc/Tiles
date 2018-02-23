package game.panels.sections;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import game.panels.StatusPane;

public class LogSection extends JComponent {
	private JTextArea log;
	
	public LogSection() {
		// prep log
		log = new JTextArea("Welcome!");
		log.setEditable(false);
		log.setFocusable(false);
		JScrollPane scroll = new JScrollPane(log, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		// display
		this.setLayout(new BorderLayout());
		this.add(scroll);
	}

	// returns the log, should only be called once
	public JTextArea getLog() {
		return log;
	}
}
