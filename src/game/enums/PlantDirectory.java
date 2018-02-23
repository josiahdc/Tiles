package game.enums;

public enum PlantDirectory implements FeatureInterface {
	
	// NAME(base image name, spreadable, spread rate (smaller is faster))
	TREE("tree", true, 150),
	SHRUB("shrub", true, 120),
	CACTUS("cactus", false, 0);
	
	private final String img;
	private final boolean spreadable;
	private final int spreadRate;
	
	// constructor
	private PlantDirectory(String img, boolean spreadable, int spreadRate) {
		this.img = "flora_" + img;
		this.spreadable = spreadable;
		this.spreadRate = spreadRate;
	}
	
	// getters -----
	
	// returns image
	public String getImg() {
		return img;
	}
	
	// returns rate at which plant spreads
	public boolean getSpreadable() {
		return spreadable;
	}
	
	// returns whether the plant spreads or not
	public int getSpreadRate() {
		return spreadRate;
	}
}
