package game.enums;

public enum FurnishingDirectory {

	// NAME(String "image Name")
	WALL_WOOD("wall_wood"),
	BED("bed");
	
	// fields
	private final String img;
	
	// default constructor
	private FurnishingDirectory(String img) {
		this.img = "furnishing_" + img;
	}
	
	// returns img String
	public String getImg() {
		return img;
	}
}
