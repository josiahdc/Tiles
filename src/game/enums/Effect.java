package game.enums;

public enum Effect {
	
	// NAME(String "image name")
	BUILDABLE("buildable"),
	BLOCKED("blocked");

	private final String img;
	
	private Effect(String img) {
		this.img = "effect_" + img;
	}
	
	// getters -----
	
	// returns image
	public String getImg() {
		return img;
	}
}
