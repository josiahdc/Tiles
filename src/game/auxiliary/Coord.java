package game.auxiliary;

public class Coord {
	private int x;
	private int y;
	
	// constructor
	public Coord(int x, int y) {
		// initialize globals
		this.x = x;
		this.y = y;
	}
	
	// returns x value
	public int getX() {
		return x;
	}
	
	// returns y value
	public int getY() {
		return y;
	}
	
	// hash method
	public int hashCode() {
		return x * y + y;
	}
	
	// equality test
	public boolean equals(Object other) {
		if(this.x == ((Coord) other).getX() && this.y == ((Coord) other).getY()) {
			return true;
		} else {
			return false;
		}
	}
	
}
