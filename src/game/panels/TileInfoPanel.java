package game.panels;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.control.Game;
import game.panels.sections.BuildInfoButtonSection;
import game.panels.sections.TileInfoButtonSection;

public class TileInfoPanel extends JPanel {
	private TileInfoButtonSection buttons;

	public TileInfoPanel(Game game) {
		// prep sections
		JLabel title = new JLabel("Tile Info");
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		buttons = new TileInfoButtonSection(game);
		
		// add components
		add(title);
		add(buttons);
	}
	
	// sets the confirm button clickability
	public void refresh() {
		
	}
}
