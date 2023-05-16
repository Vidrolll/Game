package com.test.main.util.handlers;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import com.test.main.util.base.Controller;
import com.test.main.util.base.Entity;

public class EntityHandler {
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	public void removeEntity(Entity e) {
		entities.remove(e);
	}
	public void removeAll() {
		entities.clear();
	}
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	public void update() {
		for(Entity e : entities) {
			e.update();
		}
	}
	public void render(Graphics2D g) {
		for(Entity e : entities) {
			e.render(g);
		}
	}
	
	public void input(KeyEvent e) {
		for(Entity en : entities) {
			if(en instanceof Controller) {
				((Controller) en).input(e);
			}
		}
	}
	public void input(MouseEvent e, int mouseX, int mouseY) {
		for(Entity en : entities) {
			if(en instanceof Controller) {
				((Controller) en).input(e,mouseX,mouseY);
			}
		}
	}
	public void input(MouseWheelEvent e) {
		for(Entity en : entities) {
			if(en instanceof Controller) {
				((Controller) en).input(e);
			}
		}
	}
}
