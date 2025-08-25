package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import world.Camera;
import world.World;

public class Player extends Entity{
	
	public boolean right,left;
	public int right_dir = 0, left_dir = 1;
	public int dir = right_dir;
	public double gravity = 2;
	
	public boolean jump = false, isJumping = false;
	public int jumpHeight = 60;
	public int jumpFrames = 0; 
	
	private int frames = 0, maxFrames = 15, indexIdle = 0, maxIndexIdle = 3;
	private int indexRun = 0, maxIndexRun = 3;
	private boolean moved = false;
	private BufferedImage[] RunRightPlayer, RunLeftPlayer, idlePlayer;

	public Player(int x, int y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		
		RunRightPlayer = new BufferedImage[4];	
		RunLeftPlayer = new BufferedImage[4];
		idlePlayer = new BufferedImage[4];
		for(int i = 0; i < 4; i++) {
			RunRightPlayer[i] = Game.spritesheet.getSprit(0 + (i*16), 0, 16, 24, "/PlayerRunRight.png");
			RunLeftPlayer[i] = Game.spritesheet.getSprit(0 + (i*16), 0, 16, 24, "/PlayerRunLeft.png");
		}
		for(int i = 0; i < 4; i++) {
			idlePlayer[i] = Game.spritesheet.getSprit(0 + (i*16), 0, 16, 24, "/PlayerIdle.png");
		}
	}
	
	public void tick() {
		depth = 2;
		moved = false;
		if(!isJumping && World.isFree(this.getX(), (int)(this.getY()+gravity))) {
			y += gravity;
		}
		
		if(right && World.isFree((int)(this.getX()+speed), this.getY())) {
			if(dir == left_dir) {
				indexRun = 0;
				frames = 0;
			}
			moved = true;
			indexIdle = 0;
			dir = right_dir;
			x += speed;
		} else if(left && World.isFree((int)(this.getX()-speed), this.getY())) {
			if(dir == right_dir) {
				indexRun = 0;
				frames = 0;
			}
			moved = true;
			indexIdle = 0;
			dir = left_dir;
			x -= speed;
		}
		if(jump && !World.isFree(this.getX(), this.getY()+1)) {
			isJumping = true;
		} else {
			jump = false;
		}
		
		if(isJumping) {
			if(World.isFree(this.getX(), this.getY()-2)) {
				y -= 1.2;
				jumpFrames += 1.2;
				if(jumpFrames == jumpHeight) {
					isJumping = false;
					jump = false;
					jumpFrames = 0;
				}
			} else {
				isJumping = false;
				jump = false;
				jumpFrames = 0;
			}
		}
		
		Camera.x = Camera.clamp(this.getX() - Game.WIDTH/2, 0, World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - Game.HEIGHT/2, 0, World.HEIGHT*16 - Game.HEIGHT);
	}
	
	public void render(Graphics g) {
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			if(moved) {
				indexRun++;
				if(indexRun > maxIndexRun) {
					indexRun = 0;	
				}
			} else {
				indexIdle++;
				if(indexIdle > maxIndexIdle) {
					indexIdle = 0;
				}
			}
		}
			
		if(dir == right_dir) {
			if(moved) {
				g.drawImage(RunRightPlayer[indexRun], this.getX() - Camera.x, this.getY() - Camera.y, null);
			} else {
				g.drawImage(idlePlayer[indexIdle], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
		} else if(dir == left_dir) {
			if(moved) {
				g.drawImage(RunLeftPlayer[indexRun], this.getX() - Camera.x, this.getY() - Camera.y, null);
			} else {
				g.drawImage(idlePlayer[indexIdle], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
		}
	}
}