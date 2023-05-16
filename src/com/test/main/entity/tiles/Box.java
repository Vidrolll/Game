package com.test.main.entity.tiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.test.main.entity.Collision;
import com.test.main.util.EnvironmentVariables;
import com.test.main.util.base.Entity;
import com.test.main.util.enums.EntityID;
import com.test.main.util.handlers.EntityHandler;

public class Box extends Entity {
	Collision collision;

	public Box(EntityHandler eh, int x, int y) {
		super(eh, x, y);
		id = EntityID.BOX;
		solid = true;
		collision = new Collision(this,eh);
	}

	@Override
	public void update() {
		y+=velY;
		collision();
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, 50, 50);
	}

	@Override
	public void collision() {
		collision.collisionY();
		if (!collision.groundCollide() && velY < EnvironmentVariables.TERMINAL_VELOCITY)
			velY+=EnvironmentVariables.GRAVITY;
		collision.collisionX();
		setVelX(0);
	}
	
	@Override
	public void collisionXEvent(Entity en) {
		if(en.getID()==EntityID.PLAYER) {
			setVelX(en.getVelX());
			x+=en.getVelX();
		}
	}
	
	@Override
	public void collisionYEvent(Entity en) {
		if(en.getID()==EntityID.PLAYER) {
			//setY(9000);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,50,50);
	}

}
