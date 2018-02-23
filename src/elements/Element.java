package elements;

import game.auxiliary.ConstructionException;
import game.control.Tile;

public abstract class Element {
	protected Tile parent;
	protected String img;
	
	// default constructor should not be called
	public Element() throws ConstructionException {
		throw new ConstructionException();
	}
	
	// constructor
	public Element(Tile parent, String img) {
		this.parent = parent;
		this.img = img;
		parent.getGame().placeElement(this, parent);
	}
	
	// returns img texture
	public String getImg() {
		return img;
	}
	
	// remove the elements from the game
	public void remove() {
		parent.getGame().removeElement(this, parent);
	}

	// default step operation
	public void step() {
		// do nothing
	}
	
}
