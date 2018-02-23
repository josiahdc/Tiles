package game.control;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
	
	public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		// start game
		UIManager.setLookAndFeel(
				UIManager.getSystemLookAndFeelClassName());
		Game game = new Game();
	}
}
