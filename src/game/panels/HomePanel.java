package game.panels;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import game.panels.sections.LogSection;
import game.panels.sections.NavSection;

public class HomePanel extends JPanel {
	private LogSection logSection;
	private NavSection navSection;
	private StatusPane parent;
	
	// constructor
	public HomePanel(StatusPane parent) {
		// init
		this.parent = parent;
		
		// prep title
		JLabel mainTitle = new JLabel("Home");
		JLabel logTitle = new JLabel("Log");
		
		// prep children
		logSection = new LogSection();
		navSection = new NavSection(parent);
		
		// add components
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(mainTitle);
		add(navSection);
		add(logTitle);
		add(logSection);
	}
	
	// returns the log, should only be called once
	public JTextArea getLog() {
		return logSection.getLog();
	}
}