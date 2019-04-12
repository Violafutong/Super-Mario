package com.tutorial.entity.mob;

import java.awt.Graphics;
import java.util.Random;

import com.tutorial.entity.Entity;
import com.tutorial.mario.Game;
import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;
import com.tutorial.mario.tile.Tile;

public class Goomba extends Entity{
	public int frame = 0;
	public int frameDelay = 0;
	private Random random = new Random();
	public Goomba(int x, int y, int width, int height, Id id, Handler handler) {
		super(x, y, width, height, id, handler);
		
		
		int dir = random.nextInt(2);
		
		switch (dir) {
		case 0:
			setVelX(-1);
			facing = 0;
			break;
		case 1:
			setVelX(1);
			facing = 1;
			break;
		}
		}

	@Override
	public void render(Graphics g) {
		if(facing == 0) {
			g.drawImage(Game.goomba[frame+4].getBufferedImage(), x, y, width, height, null);
			}else if(facing == 1) {
			g.drawImage(Game.goomba[frame].getBufferedImage(), x, y, width, height, null);
			}	
	}

	@Override
	public void tick() {
	   x += velX;
	   y += velY;
		for(Tile t:handler.tile) {
			if(!t.solid)break;
			if(t.getId()==Id.mushroom) {
				if(getBoundsBottom().intersects(t.getBounds())) {
					setVelY(0);
					//y = t.getY()-t.height;
					if(falling) falling = false;
				}else {
					if(!falling) {
						gravity = 0.8;
						falling = true;
					}
				}
				if(getBoundsLeft().intersects(t.getBounds())) {
					setVelX(1);
					facing = 1;
				}
				if(getBoundsRight().intersects(t.getBounds())) {
					setVelX(-1);
					facing = 0;
				}
			}
		}
//		if(falling) {
//			gravity+=0.1;
//			setVelY((int)gravity);
//		}
		if(velX!=0) {
			frameDelay++;
			if(frameDelay>=10) {
				frame++;
				if(frame>3) {
					frame = 0;
				}
				frameDelay = 0;
			}
		}
	}

}
