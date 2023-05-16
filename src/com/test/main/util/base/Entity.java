package com.test.main.util.base;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.test.main.util.enums.EntityID;
import com.test.main.util.enums.EntityModifiers;
import com.test.main.util.handlers.EntityHandler;

public abstract class Entity {
	protected int x,y,velX,velY;
	protected EntityID id;
	protected EntityModifiers[] modifiers;
	protected boolean solid = false;
	protected EntityHandler eh;
	
	public Entity(EntityHandler eh, int x, int y) {
		this.x = x;
		this.y = y;
		this.eh = eh;
	}
	
	public abstract void update();
	public abstract void render(Graphics2D g);
	public abstract void collision();
	public void collisionXEvent(Entity en) {}
	public void collisionYEvent(Entity en) {}
	public abstract Rectangle getBounds();
	
	public void setModifiers(EntityModifiers...mods) {
		modifiers = mods;
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
	public int getVelX() {
		return velX;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public int getVelY() {
		return velY;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}
	public EntityID getID() {
		return id;
	}
	public boolean isSolid() {
		return solid;
	}
}
