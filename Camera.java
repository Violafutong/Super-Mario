package com.tutorial.mario;

import com.tutorial.mario.entity.Entity;

public class Camera {

	public int x, y;
	
	public void tick(Entity player) {
		setX(-player.getX() + Game.getFrameWidth()/2);//Game.getFrameWidth()
		setY(-player.getY() + Game.getFrameHeight()/2);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
