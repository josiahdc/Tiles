package elements.features;

import elements.Element;
import game.control.Tile;
import game.enums.FeatureInterface;

public abstract class Feature extends Element {
	protected FeatureInterface type;
	
	// constructor
	public Feature(FeatureInterface type, Tile parent) {
		super(parent, type.getImg());
		this.type = type;
	}
}
