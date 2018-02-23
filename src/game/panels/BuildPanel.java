package game.panels;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import game.control.Game;
import game.panels.sections.BuildSection;

public class BuildPanel extends JPanel {
	
	public BuildPanel(Game game) {
		// prep components
		BuildSection buildSection = new BuildSection(game);
		
		// add components
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(new JLabel("BUILDABLE!"));
		this.add(buildSection);
	}

}
