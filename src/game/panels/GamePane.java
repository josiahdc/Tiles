package game.panels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.KeyStroke;

import creatures.citizens.Farmer;

import javax.swing.AbstractAction;
import javax.imageio.ImageIO;

import game.auxiliary.Coord;
import game.control.Game;
import game.control.Tile;
import game.control.VisTile;
import game.enums.Floor;
import game.resource_sets.ImageSet;

public class GamePane extends JComponent {
	private HashMap<Coord, VisTile> visTiles;
	private HashMap<Coord, Tile> tiles;
	private ImageSet img;
	private Game game;
	private VisTile center;
	private Coord currentChunk;
	
	// constructor
	public GamePane(HashMap<Coord, Tile> tiles, Game game, double screenWidth, double screenHeight) {
		// initialize globals
		img = new ImageSet();
		this.tiles = tiles;
		this.game = game;
		this.currentChunk = new Coord(0, 0);
		
		// set up vis tiles
		int visX;
		int visY;
		int screenX = 0;
		int screenY = 0;
		int visWidth = (int) (screenWidth * 3 / 4) / 40;
		int visHeight = (int) (screenHeight) / 40;
		visTiles = new HashMap<Coord, VisTile>();
		for(int x = -visWidth; x <= visWidth; x++) {
			for(int y = visHeight; y >= -visHeight; y--) {
				visX = (x + visWidth) * 20;
				visY = (y - visHeight) * -20;
				VisTile newTile = new VisTile(visX, visY, tiles.get(new Coord(x, y)));
				visTiles.put(new Coord(screenX, screenY), newTile);
				if(y == 0 && x == 0) {
					center = newTile;
				}
				screenY++;
			}
			screenY = 0;
			screenX++;
		}

		// set up Mouse Listener
		this.addMouseListener(new MouseEar());
		
		// set size
		this.setPreferredSize(new Dimension(visWidth * 40, visHeight * 40));
	}
	
	// repaint the pane with the images from each tile
	public void paint(Graphics g) {
		String[] imgs;
		for(Coord c : visTiles.keySet()) {
			VisTile tile = visTiles.get(c);
			imgs = tile.getTile().getImgs();
			for(int i = 0; i < imgs.length; i++) {
				if(imgs[i] != null) {
					g.drawImage(img.getImg(imgs[i]), tile.getX(),
											tile.getY(), null);
				}
			}
		}
	}
	
	// moves the pane in the specified direction
	public void move(int dir) {
		// if chunk has changed, make sure adjacent chunks are generated
		Coord centerCoord = game.getChunk(center.getTile().getX(), center.getTile().getY());
		if(!currentChunk.equals(centerCoord)) {
			game.testChunks(center.getTile().getX(), center.getTile().getY());
			currentChunk = centerCoord;
		}
		
		// move visTile screen
		for(Coord c : visTiles.keySet()) {
			VisTile t = visTiles.get(c);
			if(dir == 0) {
				t.setTile(t.getTile().getNorth());
			} else if(dir == 1) {
				t.setTile(t.getTile().getEast());
			}  else if(dir == 2) {
				t.setTile(t.getTile().getSouth());
			}  else if(dir == 3) {
				t.setTile(t.getTile().getWest());
			}
		}
		this.repaint();
	}
	
	//highlights center tile
	public void highlightCenter() {
		center.getTile().highlight();
	}
	
	// mouse listener
	class MouseEar implements MouseListener {

		// highlight the selected tile
		public void mouseClicked(MouseEvent arg0) {
			int x = arg0.getX() / 20;
			int y = arg0.getY() / 20;
			VisTile t = visTiles.get(new Coord(x, y));
			t.getTile().highlight();
		}

		public void mouseEntered(MouseEvent arg0) {
			// do nothing
		}

		public void mouseExited(MouseEvent arg0) {
			// do nothing	
		}

		public void mousePressed(MouseEvent arg0) {
			// do nothing
		}

		public void mouseReleased(MouseEvent arg0) {
			// do nothing
		}
	}
}