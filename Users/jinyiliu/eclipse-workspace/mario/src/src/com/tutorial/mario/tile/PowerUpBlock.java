package com.tutorial.mario.tile;

import java.awt.Graphics;

import com.tutorial.entity.powerup.Mushroom;
import com.tutorial.mario.Game;
import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;
import com.tutorial.mario.gfx.Sprite;

public class PowerUpBlock extends Tile {
	
		private Sprite powerUp;
		
		private boolean poppedUp =false;
		
		private int spriteY = getY();

	public PowerUpBlock(int x, int y, int width, int height, boolean solid, Id id, Handler handler, Sprite powerUp) {
		super(x, y, width, height, solid, id, handler);
		this.powerUp = powerUp;
		}

	@Override
	public void render(Graphics g) {
		if(!poppedUp) g.drawImage(powerUp.getBufferedImage(), x, spriteY, width, height, null);
		if(!activated) g.drawImage(Game.powerUp.getBufferedImage(), x, y, width, height, null);
		else g.drawImage(Game.usedPowerUp.getBufferedImage(), x, y, width, height, null);
	}

	@Override
	public void tick() {
		if(activated&&!poppedUp) {
			spriteY--;
			if(spriteY<=y-height) {
				handler.addEntity(new Mushroom(x, spriteY, width, height, Id.mushroom, handler));
				poppedUp = true;
			}
		}
	}

}
