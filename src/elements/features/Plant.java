package elements.features;

import java.util.Random;

import game.control.Tile;
import game.enums.PlantDirectory;

public class Plant extends Feature {
	private boolean spreadable;
	private int spreadRate;
	private Random rand;
	//private Item drops;
	
	// constructor
	public Plant(PlantDirectory type, Tile parent) {
		super(type, parent);
		// initialize globals from flora enum
		this.spreadable = type.getSpreadable();
		this.spreadRate = type.getSpreadRate();
		if(spreadable) {
			rand = new Random();
		}
	}
	
	// grow plant
	public void step() {
		if(spreadable) {
			if(rand.nextInt(spreadRate) == 0) {
				Tile next = parent.getDirTile(rand.nextInt(4));
				if(next != null && next.getElement() == null &&
						next.getBiome().getFloraSet().contains(type)) {
					next.setElement(new Plant(((PlantDirectory) type), next));
				}
			}
		}
	}
}
