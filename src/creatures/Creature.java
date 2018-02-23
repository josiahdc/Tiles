package creatures;

public interface Creature {
	
	// kill creature
	public void kill();
	
	// returns image
	public String getImg();
	
	// step
	public void step();
}