package world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game;

public class World {
	
	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	public static final int TILE_SIZE = 16;
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			int[] pixels = new int[WIDTH*HEIGHT];
			tiles = new Tile[WIDTH*HEIGHT];
			map.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
			for(int xx = 0; xx < WIDTH; xx++) {
				for(int yy = 0; yy < HEIGHT; yy++) {
					int posAtual = xx + (yy * WIDTH);
					int pixelAtual = pixels[posAtual];
					tiles[posAtual] = new BackgroundTile(xx*16, yy*16, Tile.BACKGROUND_TILE);
					switch (pixelAtual) {
					case 0xff3dd413:
						tiles[posAtual] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR);
						break;
					case 0xffffffff:
						Game.player.setX(xx*16);
						Game.player.setY(yy*16);
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isFree(int xnext, int ynext) {
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+16-1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
		
		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext+24-1) / TILE_SIZE;
		
		int x4 = (xnext+16) / TILE_SIZE;
		int y4 = (ynext+24-1) / TILE_SIZE;
		
		return !(tiles[x1 + (y1 * World.WIDTH)] instanceof FloorTile ||
				tiles[x2 + (y2 * World.WIDTH)] instanceof FloorTile ||
				tiles[x3 + (y3 * World.WIDTH)] instanceof FloorTile ||
				tiles[x4 + (y4 * World.WIDTH)] instanceof FloorTile);
	}
	
	public void render(Graphics g) {
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		
		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4) + 1;
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT) {
					continue;
				}
				Tile tile = tiles[xx + 	(yy * WIDTH)];
				tile.render(g);
			}
		}
	}
}
