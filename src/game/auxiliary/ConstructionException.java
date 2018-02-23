package game.auxiliary;

public class ConstructionException extends Exception {

	// thrown when object calls default constructor when it should have called one with
	// more parameters - check super classes for correct constructor
	public ConstructionException() {
		super("object not constructed correctly");
	}
}
