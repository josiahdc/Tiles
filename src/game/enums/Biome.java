package game.enums;

import java.util.HashSet;

public enum Biome {
	
	// NAME(floors found in biome, flora found in biome, resources found in biome,
	//			how likely features are to be generated (smaller is more))
	PLANE(new Floor[] {Floor.GRASS}, 
			new PlantDirectory[] {},
			new DepositDirectory[] {DepositDirectory.STONE},
			50),
	DESERT(new Floor[] {Floor.SAND}, 
			new PlantDirectory[] {PlantDirectory.CACTUS},
			new DepositDirectory[] {DepositDirectory.CLAY, DepositDirectory.IRON},
			50),
	TUNDRA(new Floor[] {Floor.TUNDRA}, 
			new PlantDirectory[] {PlantDirectory.SHRUB},
			new DepositDirectory[] {DepositDirectory.STONE, DepositDirectory.GOLD},
			75),
	FOREST(new Floor[] {Floor.DIRT}, 
			new PlantDirectory[] {PlantDirectory.TREE},
			new DepositDirectory[] {DepositDirectory.STONE},
			25);
	
	private final Floor[] floors;
	private final PlantDirectory[] flora;
	private final DepositDirectory[] resources;
	private final HashSet<PlantDirectory> floraSet;
	private final int featureChance;
	
	// constructor
	private Biome(Floor[] floors, PlantDirectory[] flora, DepositDirectory[] resources, int chance) {
		this.floors = floors;
		this.flora = flora;
		this.resources = resources;
		this.featureChance = chance;
		this.floraSet = new HashSet<PlantDirectory>();
		// fill flora set
		for(int i = 0; i < flora.length; i++) {
			floraSet.add(flora[i]);
		}
	}
	
	// getters -----
	
	// returns an array of all the floors the biome can contain
	public Floor[] getFloors() {
		return floors;
	}
	
	// returns an array of all the flora the biome can contain
	public PlantDirectory[] getFlora() {
		return flora;
	}
	
	// returns a set of all the flora the biome can contain
	public HashSet<PlantDirectory> getFloraSet() {
		return floraSet;
	}
	
	// returns an array of all the resources the biome can contain
	public DepositDirectory[] getResources() {
		return resources;
	}
	
	// returns the likely hood of feature generation
	public int getFeatureChance() {
		return featureChance;
	}
}
