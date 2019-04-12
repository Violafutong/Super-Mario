package com.tutorial.entity.mob;

import java.awt.Color;
import java.awt.Graphics;

import com.tutorial.entity.Entity;
import com.tutorial.mario.Game;
import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;
import com.tutorial.mario.states.PlayerState;
import com.tutorial.mario.tile.Tile;

public class Player extends Entity{
	
	private PlayerState state;
	
	public int frame =0;
	public int frameDelay = 0;
	
	private boolean animate = false;
	
	public Player(int x, int y, int width, int height, Id id, Handler handler) {
		super(x, y, width, height, id, handler);
		state = PlayerState.SMALL;
	}

	public void render(Graphics g) {
//		g.setColor(Color.BLUE);
//		g.fillRect(x, y, width, height);
		if(facing == 0) {
		g.drawImage(Game.player[frame+5].getBufferedImage(), x, y, width, height, null);
		}else if(facing == 1) {
		g.drawImage(Game.player[frame].getBufferedImage(), x, y, width, height, null);
		}
	}
	
	public void tick() {
		x+=velX;
		y+=velY;
//		if(x<=0) x=0;
//		if(y<=0) y=0;
//		if(x+width>=1080) x = 1080-width;
		if(y+height>=900) y = 900-height;
		if(velX!=0) animate = true;
		else animate = false;
		for(Tile t:handler.tile) {
			if(!t.solid)break;
			if(t.getId()==Id.wall) {
				if(getBoundsTop().intersects(t.getBounds())) {
					setVelY(0);
					//y = t.getY()+t.height;
					if(jumping) {
						jumping = false;
						gravity = 0.8;
						falling = true;
					}
					if(t.getId() == Id.powerUp) {
						if(getBoundsTop().intersects(t.getBounds())) t.activated = true;
						
					}
				}
				if(getBoundsBottom().intersects(t.getBounds())) {
					setVelY(0);
					//y = t.getY()-t.height;
					if(falling) falling = false;
				}else {
					if(!falling&&!jumping) {
						gravity = 0.8;
						falling = true;
					}
				}
				if(getBoundsLeft().intersects(t.getBounds())) {
					setVelX(0);
					x = t.getX()+t.width;
				}
				if(getBoundsRight().intersects(t.getBounds())) {
					setVelX(0);
					x = t.getX()-t.width;
				}
			}
		}
		
		for(int i=0; i<handler.entity.size();i++) {
			Entity e = handler.entity.get(i);
			
			if(e.getId() == Id.mushroom) {
				if(getBounds().intersects(e.getBounds())) {
					int tpX = getX();
					int tpY = getY();
					width = (int) (width*1.5);
					height = (int) (height*1.5);
					setX(tpX-width);
					setY(tpY-height);
					if(state == PlayerState.SMALL) state = PlayerState.BIG;
					e.die();
				}
			}else if(e.getId() == Id.goomba) {
				if(getBoundsBottom().intersects(e.getBoundsTop())) {
					e.die();
				}
				else if(getBounds().intersects(e.getBounds())) {
					if(state == PlayerState.BIG) {
						state = PlayerState.SMALL;
						width/=2;
						height/=2;
					}
					else if(state == PlayerState.SMALL) {
					die();
					}
				}
			}
		}
		
		if(jumping) {
			gravity-=0.1;
			setVelY((int)-gravity);
			if(gravity<=0.8) {
				jumping = false;
				falling = true;
			}
		}
		if(falling) {
			gravity+=0.1;
			setVelY((int)gravity);
		}
//		if(animate) {
//			frameDelay++;
//			if(frameDelay>=3) {
//				frame++;
//				if(frame>=5) {
//					frame =0;
//				}
//				frameDelay = 0;
//			}
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
