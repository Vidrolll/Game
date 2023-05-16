package com.test.main.entity;

import java.awt.Point;
import java.awt.Rectangle;

import com.test.main.util.base.Entity;
import com.test.main.util.handlers.EntityHandler;

public class Collision {
	Entity en;
	EntityHandler eh;
	
	public Collision(Entity en, EntityHandler eh) {
		this.en = en;
		this.eh = eh;
	}
	
	public boolean groundCollide() {
		for(int i = 0; i < eh.getEntities().size(); i++) {
			Entity tileCheck = eh.getEntities().get(i);
			if (tileCheck != null) {
				if(!tileCheck.isSolid()||tileCheck==en) continue;
				if (isColliding(new Point(en.getX()+1-en.getVelX(), (int) (en.getY() +  en.getBounds().getHeight() + en.getVelY())), tileCheck)||
						isColliding(new Point(en.getX() + (int)en.getBounds().getWidth()-1-en.getVelX(), en.getY() +  (int)en.getBounds().getHeight() + en.getVelY()), tileCheck)||
						isColliding(new Point(en.getX() + (int)en.getBounds().getWidth()-1-en.getVelX(), en.getY() + (int)en.getBounds().getHeight() + en.getVelY()), tileCheck)||
						isColliding(new Point(en.getX()+1-en.getVelX(), (int)en.getY() + (int)en.getBounds().getHeight() + en.getVelY()), tileCheck)) {
					en.setVelY(0);
					en.setY((int)(tileCheck.getY() - en.getBounds().getHeight()));
					tileCheck.collisionYEvent(en);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean groundCheck() {
		for(int i = 0; i < eh.getEntities().size(); i++) {
			Entity tileCheck = eh.getEntities().get(i);
			if (tileCheck != null) {
				if(!tileCheck.isSolid()||tileCheck==en) continue;
				if (isColliding(new Point(en.getX()+1-en.getVelX(), (int) (en.getY() +  en.getBounds().getHeight() + en.getVelY())), tileCheck)||
						isColliding(new Point(en.getX() + (int)en.getBounds().getWidth()-1-en.getVelX(), en.getY() +  (int)en.getBounds().getHeight() + en.getVelY()), tileCheck)||
						isColliding(new Point(en.getX() + (int)en.getBounds().getWidth()-1-en.getVelX(), en.getY() + (int)en.getBounds().getHeight() + en.getVelY()), tileCheck)||
						isColliding(new Point(en.getX()+1-en.getVelX(), (int)en.getY() + (int)en.getBounds().getHeight() + en.getVelY()), tileCheck)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean collisionY() {
		for(int i = 0; i < eh.getEntities().size(); i++) {
			Entity tileCheck = eh.getEntities().get(i);
			if (tileCheck != null) {
				if(!tileCheck.isSolid()||tileCheck==en) continue;
				if (isColliding(new Point(en.getX()+1-en.getVelX(), en.getY() - Math.abs(en.getVelY())), tileCheck)||
						isColliding(new Point(en.getX()+1-en.getVelX(), en.getY() - Math.abs(en.getVelY())), tileCheck)||
						isColliding(new Point(en.getX() + (int)en.getBounds().getWidth()-1-en.getVelX(), en.getY() - Math.abs(en.getVelY())), tileCheck)||
						isColliding(new Point((int)(en.getX() + en.getBounds().getWidth()-1-en.getVelX()), en.getY() - Math.abs(en.getVelY())), tileCheck)) {
					en.setVelY((int)(en.getVelY()*-0.4));
					en.setY((int)(tileCheck.getY() + tileCheck.getBounds().getHeight()));
					tileCheck.collisionYEvent(en);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean collisionX() {
		for(int i = 0; i < eh.getEntities().size(); i++) {
			Entity tileCheck = eh.getEntities().get(i);
			if (tileCheck != null && tileCheck.isSolid() && tileCheck!=en && checkX(tileCheck))
				return true;
		}
		return false;
	}

	public boolean checkX() {
		for(int i = 0; i < eh.getEntities().size(); i++) {
			Entity tileCheck = eh.getEntities().get(i);
			if (tileCheck != null && tileCheck.isSolid() && tileCheck!=en) {
				if (isColliding(new Point(en.getX() + en.getVelX(), en.getY() + ((int)en.getBounds().getHeight() / 2)), tileCheck)
						|| isColliding(new Point(en.getX()+ en.getVelX(), en.getY() + (int)en.getBounds().getHeight() - 1), tileCheck)
						|| isColliding(new Point(en.getX()+ en.getVelX(), en.getY() + 1), tileCheck)) {
					return true;
				}
				if (isColliding(new Point(en.getX() + (int)en.getBounds().getWidth() + en.getVelX(), en.getY() + ((int)en.getBounds().getHeight() / 2)), tileCheck)
						|| isColliding(new Point(en.getX()+ (int)en.getBounds().getWidth() + en.getVelX(), en.getY() + (int)en.getBounds().getHeight() - 1), tileCheck)
						|| isColliding(new Point(en.getX()+ (int)en.getBounds().getWidth() + en.getVelX(), en.getY() + 1), tileCheck)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkX(Entity tileCheck) {
		if (isColliding(new Point(en.getX() + en.getVelX(), en.getY() + ((int)en.getBounds().getHeight() / 2)), tileCheck)
				|| isColliding(new Point(en.getX()+ en.getVelX(), en.getY() + (int)en.getBounds().getHeight() - 1), tileCheck)
				|| isColliding(new Point(en.getX()+ en.getVelX(), en.getY() + 1), tileCheck)) {
			en.setX(tileCheck.getX() + (int)tileCheck.getBounds().getWidth());
			tileCheck.collisionXEvent(en);
			return true;
		}
		if (isColliding(new Point(en.getX()+ (int)en.getBounds().getWidth() + en.getVelX(), en.getY() + ((int)en.getBounds().getHeight() / 2)), tileCheck)
				|| isColliding(new Point(en.getX()+ (int)en.getBounds().getWidth() + en.getVelX(), en.getY() + (int)en.getBounds().getHeight() - 1), tileCheck)
				|| isColliding(new Point(en.getX()+ (int)en.getBounds().getWidth() + en.getVelX(), en.getY() + 1), tileCheck)) {
			en.setX(tileCheck.getX() - (int)en.getBounds().getWidth());
			tileCheck.collisionXEvent(en);
			return true;
		}
		return false;
	}

	public boolean isColliding(Point p, Entity tile) {
		return tile.getBounds() != null && tile.getBounds().contains(p);
	}

	public boolean isColliding(Point p, Rectangle collider) {
		return collider != null && collider.contains(p);
	}
}