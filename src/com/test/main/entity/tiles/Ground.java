package com.test.main.entity.tiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.test.main.util.base.Entity;
import com.test.main.util.handlers.EntityHandler;

public class Ground extends Entity {
	int width, height;
	
	public Ground(EntityHandler eh, int x, int y, int width, int height) {
		super(eh, x, y);
		solid = true;
		this.width = width;
		this.height = height;
	}

	@Override
	public void update() {
		
	}
	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
	}
	@Override
	public void collision() {
		
	}
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,width,height);
	}
}
