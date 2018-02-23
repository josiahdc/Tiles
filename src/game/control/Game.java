package game.control;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

import javax.swing.JFrame;

import buildings.Building;
import buildings.constructs.Construct;
import creatures.Creature;
import elements.Element;
import game.auxiliary.BuildingConstructor;
import game.auxiliary.ChunkGenerator;
import game.auxiliary.Coord;
import game.enums.ConstructDirectory;
import game.enums.Effect;
import game.enums.Floor;
import game.enums.FurnishingDirectory;
import game.panels.GamePane;
import game.panels.MasterPane;
import game.panels.StatusPane;
import game.resource_sets.BlueprintSet;

public class Game {
	private BlueprintSet blueprints;
	private JFrame frame;
	private MasterPane masterPane;
	private GamePane gamePane;
	private StatusPane statusPane;
	private HashMap<Coord, Tile> tiles;
	private Tile[] buildTiles;
	private HashSet<Coord> chunks;
	// creatures
	private HashSet<Creature> creatures;
	private Stack<Creature> killStack;
	private Stack<Creature> birthStack;
	// elements
	private HashSet<Element> elements;
	private Stack<Element> addElementStack;
	private Stack<Element> delElementStack;
	private Stack<Tile> addElementPosStack;
	private Stack<Tile> delElementPosStack;
	// buildings
	private HashSet<Building> buildings;
	private Floor[] floorKeySet;
	private FurnishingDirectory[] furnishingKeySet;
	private int size;
	private int biomeSize;
	private boolean paused;
	private boolean buildMode;
	private boolean buildable;
	private ConstructDirectory constructType;
	private Tile highlightedTile;
	
	public Game() {
		// initialize globals
		blueprints = new BlueprintSet();
		killStack = new Stack<Creature>();
		birthStack = new Stack<Creature>();
		creatures = new HashSet<Creature>();
		addElementStack = new Stack<Element>();
		delElementStack = new Stack<Element>();
		addElementPosStack = new Stack<Tile>();
		delElementPosStack = new Stack<Tile>();
		elements = new HashSet<Element>();
		buildings = new HashSet<Building>();
		chunks = new HashSet<Coord>();
		tiles = new HashMap<Coord, Tile>();
		paused = false;
		buildMode = false;
		buildable = true;
		floorKeySet = Floor.values();
		furnishingKeySet = FurnishingDirectory.values();
		
		// generate tiles
		size = 100;
		biomeSize = 150;
		testChunks(0, 0);

		// create Frame
		masterPane = new MasterPane(this, tiles);
		frame = new JFrame();
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.add(masterPane);
		frame.setVisible(true);
		
		// begin main loop
		gameLoop();
	}
	
	// main game loop
	private void gameLoop() {
		while(true) {
			// carry on loop
			tick();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {}
		}
	}
	
	// advances the game one tick forward
	public synchronized void tick() {
		// wait if paused
		while(paused) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		
		// step all elements/creatures/buildings
		for(Element e : elements) {
			e.step();
		}
		for(Creature c : creatures) {
			c.step();
		}
		for(Building b : buildings) {
			b.step();
		}
		
		// add/remove elements/creatures
		while(!killStack.isEmpty()) {
			creatures.remove(killStack.pop());
		}
		while(!birthStack.isEmpty()) {
			creatures.add(birthStack.pop());
		}
		while(!delElementStack.isEmpty()) {
			Element e = delElementStack.pop();
			Tile t = delElementPosStack.pop();
			elements.remove(e);
			t.setElement(null);
		}
		while(!addElementStack.isEmpty()) {
			Element e = addElementStack.pop();
			Tile t = addElementPosStack.pop();
			elements.add(e);
			t.setElement(e);
		}
		
		// refresh panel
		gamePane.repaint();
		//statusPane.repaint();
	}
	
	
	// tests to make sure all adjacent chunks are generated, and generates them if not
	public synchronized void testChunks(int currentX, int currentY) {
		// figure out current chunk coordinates
		Coord c = getChunk(currentX, currentY);
		int chunkX = c.getX();
		int chunkY = c.getY();
		
		// test each direction and generate chunk if it doesn't exist
		for(int x = chunkX - 1; x <= chunkX + 1; x++) {
			for(int y = chunkY - 1; y <= chunkY + 1; y++) {
				if(!chunks.contains(new Coord(x, y))) {
					ChunkGenerator.generateChunk(new Coord(x, y), size, this, tiles, biomeSize);
					chunks.add(new Coord(x, y));
				}
			}
		}
	}
	
	// starts build-mode
	public void startBuildMode(String enumName) {
		buildMode = true;
		setPause();
		constructType = ConstructDirectory.valueOf(enumName);
		statusPane.switchView(StatusPane.BUILD_INFO);
		gamePane.highlightCenter();
	}
	
	// end build-mode
	public void endBuildMode() {
		deHighlight();
		buildMode = false;
		statusPane.switchView(StatusPane.HOME);
		gamePane.repaint();
		setPause();
	}
	
	// places buildings
	public void placeConstruct() {
		// test if valid site
		if(buildable) {
			
			// create new instance of buildings
			Construct construct = BuildingConstructor.buildConstruct(
											buildTiles, this, constructType.getClassName());
			
			// set tiles equal to new buildings
			for(int i = 0; i < buildTiles.length; i++) {
				buildTiles[i].setBuilding(construct, i);
			}
			
			// finish
			buildings.add(construct);
			statusPane.switchView(StatusPane.HOME);
			endBuildMode();
			statusPane.logEvent("built!");
		}
	}
	
	// clears the highlighted tile
	public void deHighlight() {
		clearHighlights();
	}
	
	// sets the highlighted tile
	public void setHighlightedTile(Tile t) {
		clearHighlights();
		highlightedTile = t;
		// if in buildmode, shade build area
		if(buildMode) {
			highlightArea();
		} else {
			statusPane.switchView(StatusPane.TILE_INFO);
		}
		gamePane.repaint();
	}
	
	private void clearHighlights() {
		// de-highlight old tile
		if(highlightedTile != null) {
			highlightedTile.deHighlight();
		}
		// clear build tiles
		if(buildTiles != null) {
			for(int i = 0; i < buildTiles.length; i++) {
				buildTiles[i].setEffect(null);
			}
		}
		buildable = true;
		statusPane.setBuildable(buildable);
	}
	
	// shade build area with highlighted tile in bottom left 
	private void highlightArea() {
		int startX = highlightedTile.getX();
		int startY = highlightedTile.getY();
		int totalX = constructType.getX();
		int totalY = constructType.getY();
		int count = 0;
		int extra = 0;
		buildTiles = new Tile[totalX * totalY];
		for(int y = startY; y < startY + totalY; y++) {
			extra = 0;
			for(int x = startX; x < startX + totalX; x++) {
				Tile t = tiles.get(new Coord(x, y));
				buildTiles[(totalX * (totalY - count) - totalX) + extra] = t;
				// if the tile does not have an elements, light buildable
				if(t.getElement() == null && t.getBuilding() == null) {
					t.setEffect(Effect.BUILDABLE);
				} else {
					t.setEffect(Effect.BLOCKED);
					buildable = false;
					statusPane.setBuildable(buildable);
				}
				extra++;
			}
			count++;
		}
	}
	
	// utilities -----
	
	// logs the event
	public void logEvent(String s) {
		statusPane.logEvent(s);
	}
	
	// unpauses the game and notifies the main thread
	private synchronized void unpause() {
		paused = false;
		notifyAll();
	}
	
	// add creature to game
	public void addCreature(Creature c) {
		birthStack.add(c);
	}
	
	// remove creature from the game
	public void removeCreature(Creature c) {
		killStack.add(c);
	}
	
	// add elements to game
	public void placeElement(Element e, Tile t) {
		addElementStack.add(e);
		addElementPosStack.add(t);
	}
	
	// remove elements from the game
	public void removeElement(Element e, Tile t) {
		delElementStack.add(e);
		delElementPosStack.add(t);
	}
	
	// getters -----
	
	//returns the blueprintSet
	public BlueprintSet getBlueprintSet() {
		return blueprints;
	}
	
	// returns the highlighted tile
	public Tile getHighlightedTile() {
		return highlightedTile;
	}
	
	// returns statusPane
	public StatusPane getStatusPane() {
		return statusPane;
	}
	
	// returns tiles
	public HashMap<Coord, Tile> getTiles() {
		return tiles;
	}
	
	// return Floor based on passed key
	public Floor getFloorByKey(int key) {
		return floorKeySet[key - 1];
	}
	
	// return elements based on passed key
	public FurnishingDirectory getFurnishingByKey(int key) {
		return furnishingKeySet[key - 1];
	}
	
	// returns what chunk specified x / y coordinates are in
	public Coord getChunk(int currentX, int currentY) {
		int chunkX = 0;
		int chunkY = 0;
		if(currentX > 0) {
			chunkX = (currentX + (size / 2) - 1) / size;
		} else {
			chunkX = (currentX - (size / 2)) / size;
		}
		if(currentY > 0) {
			chunkY = (currentY + (size / 2)) / size;
		} else {
			chunkY = (currentY - (size / 2) + 1) / size;
		}
		return new Coord(chunkX, chunkY);
	}
	
	// setters -----
	
	// pauses/unpauses the game
	public void setPause() {
		if(buildMode) {
			paused = true;
		} else {
			if(paused) {
				unpause();
			} else {
				paused = true;
			}
		}
	}
	
	// sets panes, should only be called once
	public void setPanes(StatusPane statusPane, GamePane gamePane) {
		this.gamePane = gamePane;
		this.statusPane = statusPane;
	}
}