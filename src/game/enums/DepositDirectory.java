package game.enums;

public enum DepositDirectory implements FeatureInterface {

	// NAME(image name)
	STONE("stone"),
	CLAY("clay"),
	IRON("iron"),
	GOLD("gold");
	
	private final String img;
	
	// constructor
	private DepositDirectory(String img) {
		this.img = "resource_" + img;
	}
	
	// getters -----
	
	// returns image
	public String getImg() {
		return img;
	}
}
