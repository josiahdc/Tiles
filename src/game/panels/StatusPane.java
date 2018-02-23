package game.panels;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JTextArea;

import game.control.Game;
import game.control.Tile;

public class StatusPane extends JComponent {
	public final static String HOME = "home panel";
	public final static String BUILD = "build panel";
	public final static String RESOURCE = "resource panel";
	public final static String BUILD_INFO = "build info panel";
	public final static String TILE_INFO = "tile info panel";
	
	private CardLayout layout;
	private JTextArea log;
	private BuildInfoPanel buildInfo;
	private TileInfoPanel tileInfo;
	private Game game;
	
	// constructor
	public StatusPane(Game game, double screenWidth, double screenHeight) {
		this.setPreferredSize(new Dimension((int) screenWidth / 4 - 10, (int) screenHeight));
		layout = new CardLayout();
		setLayout(layout);
		// create cards
		HomePanel homePanel = new HomePanel(this);
		BuildPanel buildPanel = new BuildPanel(game);
		buildInfo = new BuildInfoPanel(game);
		tileInfo = new TileInfoPanel(game);
		
		// add cards
		add(homePanel, HOME);
		add(buildPanel, BUILD);
		add(buildInfo, BUILD_INFO);
		add(tileInfo, TILE_INFO);
		
		// set view to home view
		switchView(HOME);
		
		// grab log
		log = homePanel.getLog();
	}
	
	// changes which panel is being displayed
	public void switchView(String name) {
		if(name == TILE_INFO) {
			tileInfo.refresh();
		}
		layout.show(this, name);
	}
	
	// logs the event
	public void logEvent(String s) {
		log.insert(s + "\n", 0);
		log.setCaretPosition(0);
	}
	
	// sets the confirm button clickability
	public void setBuildable(boolean buildable) {
		buildInfo.setBuildable(buildable);
	}
}
