package creatures.citizens;

import creatures.Creature;
import game.control.Tile;

public abstract class Citizen implements Creature {
	protected Tile parent;
	protected String img;
	
	// constructor to be called by subclasses after construction
	protected void setup(Tile parent, String img) {
		this.parent = parent;
		this.img = img;
		parent.setCreature(this);
		parent.getGame().addCreature(this);
	}
	
	// kills creature
	public void kill() {
		parent.setCreature(null);
		parent.getGame().removeCreature(this);
	}
	
	// returns image
	public String getImg() {
		return img;
	}
	
	// step
	public void step() {
		// determined by actual creature
	}
	
	// move in dir direction
	protected void move(int dir) {
		parent.setCreature(null);
		parent = parent.getDirTile(dir);
		parent.setCreature(this);
	}
}
