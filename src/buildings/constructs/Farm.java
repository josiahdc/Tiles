package buildings.constructs;

import game.control.Game;
import game.control.Tile;
import game.enums.ConstructDirectory;

public class Farm extends Construct {
	
	// constructor
	public Farm(Tile[] tiles, Game game) {
		super(tiles, game, "farm");
	}
	
	// take a turn
	public void step() {
		// do nothing
	}
}