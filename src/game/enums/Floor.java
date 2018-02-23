package game.enums;

public enum Floor {
	
	// NAME(String "base image name")
	DIRT("dirt", 1),
	GRASS("grass", 2),
	SAND("sand", 3),
	TUNDRA("tundra", 4),
	BLANK("blank", 5);
	
	private final String img;
	private final int refKey;
	
	// constructor
	private Floor(String img, int refKey) {
		this.img = "floor_" + img;
		this.refKey = refKey;
	}
	
	// getters -----
	
	// returns the floor's image
	public String getImg() {
		return img;
	}
}