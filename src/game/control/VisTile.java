package game.control;

public class VisTile {
	private Tile tile;
	private int x;
	private int y;
	
	public VisTile(int x, int y, Tile tile) {
		this.x = x;
		this.y = y;
		this.tile = tile;
	}
	
	// getters -----
	
	// returns child tile
	public Tile getTile() {
		return tile;
	}
	
	// returns child tile
	public int getX() {
		return x;
	}
	
	// returns child tile
	public int getY() {
		return y;
	}
	
	// setters -----
	
	// sets child tile
	public void setTile(Tile tile) {
		this.tile = tile;
	}	
}
