package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import main.Game;

public class Player extends Entity{
	
	public boolean right,left;
	public int right_dir = 0, left_dir = 1;
	public int dir = right_dir;
	public double speed = 1;
	
	private int framesIdle = 0, maxFrames = 10, indexIdle = 0, maxIndexIdle = 12;
	private int framesRun = 0, indexRun = 0, maxIndexRun = 7;
	private boolean moved = false, idle = true;
	private BufferedImage[] RunRightPlayer, RunLeftPlayer, idlePlayer;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		RunRightPlayer = new BufferedImage[8];
		RunLeftPlayer = new BufferedImage[8];
		idlePlayer = new BufferedImage[13];
		for(int i = 0; i < 8; i++) {
			RunRightPlayer[i] = Game.spritesheet.getSprit(0 + (i*128), 0, 128, 128, "/PlayerRunRight.png");
			RunLeftPlayer[i] = Game.spritesheet.getSprit(0 + (i*128), 0, 128, 128, "/PlayerRunLeft.png");
		}
		for(int i = 0; i < 13; i++) {
			idlePlayer[i] = Game.spritesheet.getSprit(0 + (i*128), 0, 128, 128, "/PlayerIdle.png");
		}
	}
	
	public void tick() {
		idle = true;
		moved = false;
		if(right) {
			if(dir == left_dir) {
				indexRun = 0;
				framesRun = 0;
			}
			moved = true;
			idle = false;
			indexIdle = 0;
			dir = right_dir;
			x += speed;
		} else if(left) {
			if(dir == right_dir) {
				indexRun = 0;
				framesRun = 0;
			}
			moved = true;
			idle = false;
			indexIdle = 0;
			dir = left_dir;
			x -= speed;
		} 
		
		if(idle) {
			framesIdle++;
			if(framesIdle == maxFrames) {
				framesIdle = 0;
				indexIdle++;
				if(indexIdle > maxIndexIdle) {
					indexIdle = 0;
				}
			}
		}
		
		if(moved) {
			framesRun++;
			if(framesRun == maxFrames) {
				framesRun = 0;
				indexRun++;
				if(indexRun > maxIndexRun) {
					indexRun = 0;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		if(dir == right_dir) {
			if(idle) {
				g.drawImage(idlePlayer[indexIdle], this.getX(), this.getY(), null);
			} else if(moved) {
				g.drawImage(RunRightPlayer[indexRun], this.getX(), this.getY(), null);
			}
		} else if(dir == left_dir) {
			if(idle) {
				g.drawImage(idlePlayer[indexIdle], this.getX(), this.getY(), null);
			} else if(moved) {
				g.drawImage(RunLeftPlayer[indexRun], this.getX(), this.getY(), null);
			}
		}
	}

}