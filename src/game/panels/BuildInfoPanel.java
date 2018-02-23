package game.panels;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.control.Game;
import game.panels.sections.BuildInfoButtonSection;

public class BuildInfoPanel extends JPanel {
	private BuildInfoButtonSection buttons;

	public BuildInfoPanel(Game game) {
		// prep sections
		JLabel title = new JLabel("Build Info");
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		buttons = new BuildInfoButtonSection(game);
		
		// add components
		add(title);
		add(buttons);
	}
	
	// sets the confirm button clickability
	public void setBuildable(boolean buildable) {
		buttons.setBuildable(buildable);
	}
}
