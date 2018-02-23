package game.control;

import buildings.Building;
import creatures.Creature;
import elements.Element;
import game.auxiliary.Coord;
import game.enums.Biome;
import game.enums.Effect;
import game.enums.Floor;

public class Tile {	
	private Game game;
	private Coord coord;
	private Biome biome;
	
	// neighbors
	private Tile north;
	private Tile east;
	private Tile south;
	private Tile west;
	
	// levels
	private Effect effect;
	private Creature creature;
	private Element element;
	private Building building;
	private int buildingPart;
	private Floor floor;
	
	// images 0 = floor, 1 = elements, 2 = creature, 3 = highlight, 4 = effect
	private String[] imgs;
	
	//private Item item;
	//private 
	
	// constructor
	public Tile(Coord coord, Game game, Floor floor, Biome biome) {
		// initialize globals
		this.coord = coord;
		this.game = game;
		this.biome = biome;
		this.buildingPart = 0;
		imgs = new String[5];
		
		// set up floor
		setFloor(floor);
	}
	
	// getters -----
	
	// return buildings
	public Building getBuilding() {
		return building;
	}
	
	// returns x coordinate
	public int getX() {
		return coord.getX();
	}

	// returns y coordinate
	public int getY() {
		return coord.getY();
	}
	
	// returns image name in order:
	// effect > highlight > creature > elements > buildings > floor
	public String[] getImgs() {
		return imgs;
	}
	
	// returns biome
	public Biome getBiome() {
		return biome;
	}
	
	// returns elements
	public Element getElement() {
		return element;
	}
	
	// returns north sibling
	public Tile getNorth() {
		if(north == null) {
			north = game.getTiles().get(new Coord(coord.getX(), coord.getY() + 1));
		}
		return north;
	}

	// returns east sibling
	public Tile getEast() {
		if(east == null) {
			east = game.getTiles().get(new Coord(coord.getX() + 1, coord.getY()));
		}
		return east;
	}
	
	// returns south sibling
	public Tile getSouth() {
		if(south == null) {
			south = game.getTiles().get(new Coord(coord.getX(), coord.getY() - 1));
		}
		return south;
	}
	
	// returns west sibling
	public Tile getWest() {
		if(west == null) {
			west = game.getTiles().get(new Coord(coord.getX() - 1, coord.getY()));
		}
		return west;
	}
	
	// returns sibling in dir direction
	public Tile getDirTile(int dir) {
		if(dir == 0) {
			return getNorth();
		} else if(dir == 1) {
			return getEast();
		} else if(dir == 2) {
			return getSouth();
		} else {
			return getWest();
		}
	}
	
	// returns game (usually to creature)
	public Game getGame() {
		return game;
	}
	
	// setters -----
	
	// sets biome
	public void setBiome(Biome biome) {
		this.biome = biome;
	}
	
	// sets buildings, int is which part of buildings sits on tile
	public void setBuilding(Building building, int part) {
		this.building = building;
		this.buildingPart = part;
	}

	// sets floor
	public void setFloor(Floor floor) {
		this.floor = floor;
		imgs[0] = floor.getImg();
	}
	
	// sets elements
	public void setElement(Element element) {
		this.element = element;
		if(element != null) {
			imgs[1] = element.getImg();
		} else {
			imgs[1] = null;
		}
	}

	// set creature
	public void setCreature(Creature creature) {
		this.creature = creature;
		if(creature != null) {
			imgs[2] = creature.getImg();
		} else {
			imgs[2] = null;
		}
	}
	
	// highlights the tile
	public void highlight() {
		imgs[3] = "misc_highlight";
		game.setHighlightedTile(this);
	}
	
	// de-highlights the tile
	public void deHighlight() {
		imgs[3] = null;
	}
	
	// sets the tile effect
	public void setEffect(Effect effect) {
		this.effect = effect;
		if(effect != null) {
			imgs[4] = effect.getImg();
		} else {
			imgs[4] = null;
		}
	}
}