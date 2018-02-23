package game.resource_sets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class BlueprintSet {
	// giant map of Map<String buildings type,
	//						Map<String buildings name,
	//								int[level, int[tile, keys]]]
	// buildings type > name > level > tile > key
	// floor = 0, furnishings = 1
	private HashMap<String, HashMap<String, int[][]>> floorPrints;
	private HashMap<String, HashMap<String, int[][]>> furnishingPrints;
	
	public BlueprintSet() {
		// init construct sets
		floorPrints = new HashMap<String, HashMap<String, int[][]>>();
		furnishingPrints = new HashMap<String, HashMap<String, int[][]>>();
		
		// piece reference construction
		HashMap<String, int[][]> floorTypeMap;
		HashMap<String, int[][]> furnishingTypeMap;
		String type;
		String name;
		int x = 0;
		int y = 0;
		int levels = 0;
		
		// buffer blueprints
		File blueprintFolder = new File("resources/building_blueprints/");
		BufferedReader reader;
		for(File dir : blueprintFolder.listFiles()) {
			// set up new typeMap
			type = dir.getName();
			floorTypeMap = new HashMap<String, int[][]>();
			furnishingTypeMap = new HashMap<String, int[][]>();
			floorPrints.put(type, floorTypeMap);
			furnishingPrints.put(type, furnishingTypeMap);
			for(File file : dir.listFiles()) {
				// iterate over each file in type folder
				try {
					// get info from blueprints
					reader = new BufferedReader(new FileReader(file));
					name = reader.readLine();
					// get x, y, levels
					String[] temp = reader.readLine().split("\\|");
					x = Integer.parseInt(temp[0]);
					y = Integer.parseInt(temp[1]);
					levels = Integer.parseInt(temp[2]);
					int[][] floorLevelArray = new int[levels][];
					int[][] furnishingLevelArray = new int[levels][];
					
					// get blueprint for each level
					for(int i = 0; i < levels; i++) {
						// eat level and f line
						reader.readLine();
						reader.readLine();
						
						// floors
						int[] floors = new int[x * y];
						int pointer = 0;
						// iterate over every y level
						for(int j = 0; j < y; j++) {
							String[] floorRow = reader.readLine().split("\\|");
							// add each floor in floorRow to the x 
							for(int t = 0; t < x; t++) {
								floors[pointer] = Integer.parseInt(floorRow[t]);
								pointer++;
							}
						}
						
						// eat divider line
						reader.readLine();
						
						// furnishings
						int[] furnishings = new int[x * y];
						pointer = 0;
						// iterate over every y level
						for(int j = 0; j < y; j++) {
							String[] furnishingRow = reader.readLine().split("\\|");
							// add each furnishing in floorRow to the x 
							for(int t = 0; t < x; t++) {
								furnishings[pointer] = Integer.parseInt(furnishingRow[t]);
								pointer++;
							}
						}
						
						// finalize level
						floorLevelArray[i] = floors;
						furnishingLevelArray[i] = furnishings;
					}
					// add buildings array to map
					floorTypeMap.put(name, floorLevelArray);
					furnishingTypeMap.put(name, furnishingLevelArray);
					
				// catch errors
				} catch(IOException ex) {
					System.out.println(ex + "ERROR: can't read input file" + file.getName());
				}
			}
		}
	}
	
	// returns the specified floor print
	public int[][] getFloors(String type, String name) {
		return floorPrints.get(type).get(name);
	}
	
	// returns the specified furnishings print
	public int[][] getFurnishings(String type, String name) {
		return furnishingPrints.get(type).get(name);
	}
}
