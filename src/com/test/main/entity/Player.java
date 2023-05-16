package com.test.main.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import com.test.main.effects.Trail;
import com.test.main.util.EnvironmentVariables;
import com.test.main.util.base.Controller;
import com.test.main.util.base.Entity;
import com.test.main.util.base.PlayerController;
import com.test.main.util.enums.EntityID;
import com.test.main.util.handlers.EntityHandler;

public class Player extends Entity implements Controller {
	PlayerController plr;
	
	Trail t;
	
	public Player(EntityHandler eh, int x, int y) {
		super(eh, x, y);
		id = EntityID.PLAYER;
		plr = new PlayerController(this);
		t = new Trail(this,20,Color.RED);
	}

	@Override
	public void update() {
		plr.update();
		t.update();
	}

	@Override
	public void render(Graphics2D g) {
//		g.setColor(Color.RED);
//		g.fillRect(x+velX, y+velY, 50, 50);
		//t.render(g);
		g.setColor(Color.WHITE);
		g.fillRect(x,y,100,100);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,100,100);
	}

	@Override
	public void collision() {}
	
	@Override
	public void input(KeyEvent e) {
		plr.input(e);
	}
	@Override
	public void input(MouseEvent e, int mouseX, int mouseY) {
		
	}
	@Override
	public void input(MouseWheelEvent e) {
		
	}
}
