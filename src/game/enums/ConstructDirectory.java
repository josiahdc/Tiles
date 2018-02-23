package game.enums;

public enum ConstructDirectory {

	// remember to add new  sub-types to the construction utility
	// NAME(String "enum name", String "class name", String "buildings display name", 
	//			int x, int y)
	//ex:
	// FOO_BAR_BUILDING("FOO_BAR_BUILDING", "FooBarBuilding", "Foo Bar Building", 4, 5)
	FARM("FARM", "Farm", "Farm", 5, 5),
	HOUSE("HOUSE", "House", "House", 2, 2),
	STOCKPILE("STOCKPILE", "Stockpile", "Stockpile", 1, 1);
	
	// variables
	private final String enumName;
	private final String className;
	private final String displayName;
	private final int x;
	private final int y;
	
	private ConstructDirectory(String enumName, String className, String displayName,
									int x, int y) {
		this.enumName = enumName;
		this.className = className;
		this.displayName = displayName;
		this.x = x;
		this.y = y;
	}
	
	// getters -----
	
	// returns enum name
	public String getEnumName() {
		return enumName;
	}
	
	// returns buildings display name
	public String getDisplayName() {
		return displayName;
	}
	
	// returns buildings class name
	public String getClassName() {
		return className;
	}
	
	// returns buildings width
	public int getX() {
		return x;
	}
	
	// returns buildings height
	public int getY() {
		return y;
	}
}
