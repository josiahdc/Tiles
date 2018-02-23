package creatures.citizens;

import java.util.Random;

import game.control.Tile;

public class Farmer extends Citizen {
	private Tile home;
	private Random rand;
	
	// constructor
	public Farmer(Tile parent) {
		rand = new Random();
		super.setup(parent, "citizen_farmer");
	}
	
	// move farmer one step forward
	public void step() {
		move(rand.nextInt(4));
	}
}
