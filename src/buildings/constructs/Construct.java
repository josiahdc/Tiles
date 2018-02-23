package buildings.constructs;

import buildings.Building;
import game.control.Game;
import game.control.Tile;
import game.enums.ConstructDirectory;

public abstract class Construct extends Building {
	
	// constructor
	protected Construct(Tile[] tiles, Game game, String name) {
		super(tiles, game, "construct", name);
	}
}
