package game.panels.sections;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import game.control.Game;
import game.enums.ConstructDirectory;
import game.panels.sections.components.GButton;

public class BuildSection extends JComponent implements ActionListener {
	private Game game;
	
	public BuildSection(Game game) {
		ConstructDirectory[] buildings = ConstructDirectory.values();
		this.setLayout(new GridLayout(buildings.length / 2, 2));
		this.game = game;
		
		// create buttons
		for(ConstructDirectory b : buildings) {
			GButton button = new GButton();
			button.setText(b.getDisplayName());
			button.setActionCommand(b.getEnumName());
			button.addActionListener(this);
			this.add(button);
		}
		
		// housekeeping
		this.setFocusable(false);
	}

	public void actionPerformed(ActionEvent arg0) {
		game.startBuildMode(arg0.getActionCommand());
	}
}
