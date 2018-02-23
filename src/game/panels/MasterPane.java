package game.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import game.auxiliary.Coord;
import game.control.Game;
import game.control.Tile;

public class MasterPane extends JPanel {
	// initialize panes
	private GamePane gamePane;
	private StatusPane statusPane;
	private Game game;
	
	public MasterPane(Game game, HashMap<Coord, Tile> tiles) {
		//initialize global
		this.game = game;
		
		// set up panes
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();
		statusPane = new StatusPane(game, screenWidth, screenHeight);
		gamePane = new GamePane(tiles, game, screenWidth, screenHeight);
		
		// place components
		this.add(gamePane, BorderLayout.WEST);
		this.add(statusPane, BorderLayout.EAST);
		
		// housekeeping
		game.setPanes(statusPane, gamePane);
		
		// set up actions
		NorthAction north = new NorthAction();
		EastAction east = new EastAction();
		SouthAction south = new SouthAction();
		WestAction west = new WestAction();
		PauseAction pause = new PauseAction();
		
		// set up key-bindings
		this.getInputMap().put(KeyStroke.getKeyStroke('w'), "north");
		this.getActionMap().put("north", north);
		this.getInputMap().put(KeyStroke.getKeyStroke('d'), "east");
		this.getActionMap().put("east", east);
		this.getInputMap().put(KeyStroke.getKeyStroke('s'), "south");
		this.getActionMap().put("south", south);
		this.getInputMap().put(KeyStroke.getKeyStroke('a'), "west");
		this.getActionMap().put("west", west);
		this.getInputMap().put(KeyStroke.getKeyStroke(' '), "pause");
		this.getActionMap().put("pause", pause);
		
		// debugging
		DebugAction debug = new DebugAction();
		this.getInputMap().put(KeyStroke.getKeyStroke('e'), "debug");
		this.getActionMap().put("debug", debug);
		
		// set up key listener
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	// action listeners -----
	
	// move pane north
	class NorthAction extends AbstractAction {

		public void actionPerformed(ActionEvent arg0) {
			gamePane.move(0);
		}
	}
	
	// move pane east
	class EastAction extends AbstractAction {

		public void actionPerformed(ActionEvent arg0) {
			gamePane.move(1);
		}
	}
	
	// move pane south
	class SouthAction extends AbstractAction {

		public void actionPerformed(ActionEvent arg0) {
			gamePane.move(2);
		}
	}
	
	// move pane west
	class WestAction extends AbstractAction {

		public void actionPerformed(ActionEvent arg0) {
			gamePane.move(3);
		}
	}
	
	// pause/unpause game
	class PauseAction extends AbstractAction {

		public void actionPerformed(ActionEvent arg0) {
			game.setPause();
		}
	}
	
	// debugging tool
	class DebugAction extends AbstractAction {
		
		public void actionPerformed(ActionEvent arg0) {
			game.getStatusPane().switchView(StatusPane.BUILD);
		}
	}
}
