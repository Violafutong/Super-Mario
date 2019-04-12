package com.tutorial.mario;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.plaf.basic.BasicButtonListener;

import com.tutorial.entity.Entity;
import com.tutorial.entity.mob.Goomba;
import com.tutorial.entity.mob.Player;
import com.tutorial.entity.powerup.Mushroom;
import com.tutorial.mario.tile.PowerUpBlock;
import com.tutorial.mario.tile.Tile;
import com.tutorial.mario.tile.Wall;


public class Handler {
	
	public LinkedList<Entity> entity = new LinkedList<Entity>();
	public LinkedList<Tile> tile = new LinkedList<Tile>();
//	
//	public Handler() {
//		createLevel();
//	}
	public void render(Graphics g) {
		for(Entity en: entity) {
			en.render(g);
		}
		for(Tile ti: tile) {
			ti.render(g);
		}
	}
	
	public void tick() {
		for(Entity en: entity) {
			en.tick();
		}
		for(Tile ti: tile) {
			ti.tick();
		}	
	}
	
	public void addEntity(Entity en) {
		entity.add(en);
	}
	public void removeEntity(Entity en) {
		entity.remove(en);
	}
	public void addTile(Tile ti) {
		tile.add(ti);
	}
	public void removeTile(Tile ti) {
		tile.remove(ti);
	}
	public void createLevel(BufferedImage level) {
		int width = level.getWidth();
		int height = level.getHeight();
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				int pixel = level.getRGB(x, y);
				
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 0 && green == 0 && blue == 0) addTile(new Wall(x*32, y*32, 64, 64, true, Id.wall, this));
				if(red == 0 && green == 0 && blue ==255) addEntity(new Player(x*32, y*32, 64, 64, Id.player, this));
				if(red == 255 && green == 0 && blue == 0) addEntity(new Mushroom(x*32, y*32, 64, 64, Id.mushroom, this));
				if(red == 255 && green == 119 && blue == 0) addEntity(new Goomba(x*32, y*32, 64, 64, Id.goomba, this));
				if(red == 255 && green == 255 && blue == 0) addTile(new PowerUpBlock(x*32, y*32, 64, 64, true, Id.powerUp, this, Game.mushroom));
				
			}
		}
	}

}
