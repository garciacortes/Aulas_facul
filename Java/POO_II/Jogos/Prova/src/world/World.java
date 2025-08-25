package world;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class World {

	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int WIDTH = map.getWidth();
			int HEIGHT = map.getHeight();
			int[] pixels = new int[WIDTH*HEIGHT];
			map.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
			for(int i = 0; i < pixels.length; i++) {
				if(pixels[i] == 0xffffffff) {
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
