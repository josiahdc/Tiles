package elements.furnishings;
import elements.Element;
import game.control.Tile;
import game.enums.FurnishingDirectory;

public class Furnishing extends Element {
	private FurnishingDirectory type;
	
	public Furnishing(FurnishingDirectory type, Tile parent) {
		super(parent, type.getImg());
		this.type = type;
	}

}