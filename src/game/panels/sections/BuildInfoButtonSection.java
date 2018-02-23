package game.panels.sections;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JComponent;

import game.control.Game;
import game.panels.sections.components.GButton;

public class BuildInfoButtonSection extends JComponent implements ActionListener {
	private Game game;
	private GButton confirm;
	
	public BuildInfoButtonSection(Game game) {
		this.setLayout(new GridLayout(1, 2));
		this.game = game;
		
		// prep buttons
		confirm = new GButton();
		confirm.setText("Confrm");
		confirm.setActionCommand("confirm");
		confirm.addActionListener(this);
		
		GButton cancel = new GButton();
		cancel.setText("Cancel");
		cancel.setActionCommand("cancel");
		cancel.addActionListener(this);
		
		// place buttons
		this.add(confirm);
		this.add(cancel);
		this.setFocusable(false);
	}

	// confirms or cancels the build request
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand() == "confirm") {
			game.placeConstruct();
		} else if(arg0.getActionCommand() == "cancel") {
			game.endBuildMode();
		}
	}
	
	// sets whether the confirm button is clickable or not
	public void setBuildable(boolean buildable) {
		confirm.setEnabled(buildable);
	}
}
