package game.resource_sets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageSet {
	private HashMap<String, BufferedImage> images;
	
	// constructor
	public ImageSet() {
		// initialize globals
		images = new HashMap<String, BufferedImage>();
		
		// buffer images
		File imageFolder = new File("resources/images/");
		BufferedImage img = null;
		for(File dir : imageFolder.listFiles()) {
			for(File file : dir.listFiles()) {
				try {
					img = ImageIO.read(file);
					images.put(file.getName().replace(".png", ""), img);
				} catch(IOException ex) {
					System.out.println(ex + "ERROR: can't read input file" + file.getName());
				}
			}
		}
	}
	
	// returns the requested image
	public BufferedImage getImg(String name) {
		if(name != null) {
			return images.get(name);
		} else {
			return images.get("misc_missing");
		}
	}
}
