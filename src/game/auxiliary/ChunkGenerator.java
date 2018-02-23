package game.auxiliary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import elements.features.Deposit;
import elements.features.Plant;
import game.control.Game;
import game.control.Tile;
import game.enums.Biome;
import game.enums.Floor;
import game.enums.PlantDirectory;
import game.enums.DepositDirectory;

public class ChunkGenerator {
	
	// only public method -- generates all tiles in chunk
	// dir determines which direction to generate chunk in
	// rootX/Y are upper left hand corner of root chunk
	public static void generateChunk(Coord chunkCoord, int size, Game game, HashMap<Coord, Tile> tiles, 
										int biomeSize) {
		HashMap<Coord, Tile> newTiles = new HashMap<Coord, Tile>();
		Random rand = new Random();
		Coord start = getStart(chunkCoord, size);
		int rootX = start.getX();
		int rootY = start.getY();
		
		int endX = rootX + size;
		int endY = rootY - size;
		// upper left of tile is now starting coordinate
		
		// generate (size) tiles
		Floor[] floors = Biome.FOREST.getFloors();
		Tile temp;
		Coord coord;
		for(int x = rootX; x < endX; x++) {
			for(int y = rootY; y > endY; y--) {
				coord = new Coord(x, y);
				int floor = rand.nextInt(floors.length);
				temp = new Tile(coord, game, floors[floor], Biome.FOREST);
				tiles.put(coord, temp);
				newTiles.put(coord, temp);
			}
		}
		
		// place biomes on new chunk
		layBiomes(newTiles, size, rootX, rootY, biomeSize, rand);
		
		// add features
		layFeatures(newTiles, rand, game);
	}
	
	// lays all features on new biomes
	private static void layFeatures(HashMap<Coord, Tile> newTiles, Random rand, Game game) {
		// iterate over each tile and select random feature from biome
		for(Coord c : newTiles.keySet()) {
			Tile next = newTiles.get(c);
			int roll = rand.nextInt(next.getBiome().getFeatureChance());
			if(roll == 0) { 
				PlantDirectory[] flora = next.getBiome().getFlora();
				if(flora.length > 0) {
					next.setElement(new Plant(flora[rand.nextInt(flora.length)], next));
				}
			} else if(roll == 1) {
				DepositDirectory[] resources = next.getBiome().getResources();
				if(resources.length > 0) {
					next.setElement(new Deposit(resources[rand.nextInt(resources.length)], next));
				}
			}
		}
	}
	
	// places biomes on new chunk
	// takes set of tiles from new chunk, size, and upper left/right of chunk
	private static void layBiomes(HashMap<Coord, Tile> newTiles, int size, int x, int y, int biomeSize,
									Random rand) {
		// pick random biome centers and add them to startTiles
		HashSet<Tile> startTiles = new HashSet<Tile>();
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for(int i = 0; i < size / 25; i++) { // modify this value to increase/decrease number of biomes
			nums.add(rand.nextInt(size * size) + 1);
		}
		for(Coord c : newTiles.keySet()) {
			for(int i = 0; i < nums.size(); i++) {
				int temp = nums.get(i);
				temp--;
				nums.set(i, temp);
				if(temp == 0) {
					startTiles.add(newTiles.get(c));
					nums.remove(i);
				}
			}
		}
		
		// generate biome around each newTile
		Biome[] biomes = Biome.values();
		for(Tile start : startTiles) {
			int index = rand.nextInt(biomes.length);
			Biome biome = biomes[index];
			start.setBiome(biome);
			if(start.getNorth() != null) {
				spread(newTiles, start.getNorth(), biome, rand, biomeSize, biomeSize);
			} else {
				spread(newTiles, start.getSouth(), biome, rand, biomeSize, biomeSize);
			}
		}
		
		// generalize edges
		// top
		Biome parentBiome;
		Tile temp;
		// north side
		if(newTiles.get(new Coord(x, y)).getNorth() != null) {
			for(int i = x; i < x + size; i++) {
				temp = newTiles.get(new Coord(i, y));
				parentBiome = temp.getNorth().getBiome();
				spread(newTiles, temp, parentBiome, rand, 10, biomeSize);
			}
		}
		// east side
		int right = x + size - 1;
		if(newTiles.get(new Coord(right, y)).getEast() != null) {
			for(int i = y; i > y - size; i--) {
				temp = newTiles.get(new Coord(right, i));
				parentBiome = temp.getEast().getBiome();
				spread(newTiles, temp, parentBiome, rand, 10, biomeSize);
			}
		}
		// south side
		int bottom = y - size + 1;
		if(newTiles.get(new Coord(x, bottom)).getSouth() != null) {
			for(int i = x; i < x + size; i++) {
				temp = newTiles.get(new Coord(i, bottom));
				parentBiome = temp.getSouth().getBiome();
				spread(newTiles, temp, parentBiome, rand, 10, biomeSize);
			}
		}
		// west side
		if(newTiles.get(new Coord(x, y)).getWest() != null) {
			for(int i = y; i > y - size; i--) {
				temp = newTiles.get(new Coord(x, i));
				parentBiome = temp.getWest().getBiome();
				spread(newTiles, temp, parentBiome, rand, 10, biomeSize);
			}
		}
	}
	
	// spreads from start tile using diminishing recursion
	private static void spread(HashMap<Coord, Tile> newTiles, Tile tile, Biome biome,
								Random rand, int size, int initial) {
		// prepare tile
		if(tile.getBiome() != biome) {
			tile.setBiome(biome);
			tile.setFloor(biome.getFloors()[rand.nextInt(biome.getFloors().length)]);
			
			// recurse
			if(size > 0) {
				Tile[] next = {tile.getNorth(), tile.getEast(), tile.getSouth(), tile.getWest()};
				for(int i = 0; i < 4; i++) {
					if(next[i] != null && newTiles.containsKey(new Coord(next[i].getX(), next[i].getY()))
							&& (initial - size < initial / 10 || rand.nextInt(size) > 0)) {
						spread(newTiles, next[i], biome, rand, size - 1, initial);
					}
				}
			}
		}
	}
	
	// finds upper left hand corner of coordinate chunk
	private static Coord getStart(Coord coord, int size) {
		int zeroX = (size / -2) + 1;
		int zeroY = (size / 2) - 1;
		int resultX = zeroX + size * coord.getX();
		int resultY = zeroY + size * coord.getY();
		return new Coord(resultX, resultY);
	}
}
