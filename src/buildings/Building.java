package buildings;

import elements.furnishings.Furnishing;
import game.auxiliary.ConstructionException;
import game.control.Game;
import game.control.Tile;
import game.enums.Floor;
import game.enums.FurnishingDirectory;

public abstract class Building {
	protected Game game;
	protected int level;
	protected Tile[] tiles;
	protected int[][] floors;
	protected int[][] furnishings;
	
	// default constructor throws exception to ensure complex one is called
	public Building() throws ConstructionException {
		throw new ConstructionException();
	}
	
	// constructor
	public Building(Tile[] tiles, Game game, String type, String name) {
		this.tiles = tiles;
		this.game = game;
		this.level = -1;
		
		// set up blueprints
		floors = game.getBlueprintSet().getFloors(type, name);
		furnishings = game.getBlueprintSet().getFurnishings(type, name);
		
		// prep tiles for level 0
		levelUp();
	}
	
	// levels the buildings up
	protected void levelUp() {
		level++;
		// set up floors for next level
		int[] nextFloors = floors[level];
		int[] nextFurnishing = furnishings[level];
		for(int i = 0; i < nextFloors.length; i++) {
			Floor newFloor = game.getFloorByKey(nextFloors[i]);
			tiles[i].setFloor(newFloor);
			if(nextFurnishing[i] != 0) {
				FurnishingDirectory newFurn = game.getFurnishingByKey(nextFurnishing[i]);
				new Furnishing(newFurn, tiles[i]);
			}
		}
	}
	
	// default step operation
	public void step() {
		// do nothing
	}
}
