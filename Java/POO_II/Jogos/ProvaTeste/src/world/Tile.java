package world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class Tile {

	public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprit(0, 0, 16, 16, "/GrassTop2.png");
	public static BufferedImage BACKGROUND_TILE = Game.spritesheet.getSprit(16, 0, 16, 16, "/GrassTop2.png");


	private BufferedImage sprite;
	private int x,y;
		
	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
