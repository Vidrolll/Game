package com.test.main.ui;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import com.test.main.util.base.Controller;

public class GUIHandler {
	private ArrayList<UIElement> elements = new ArrayList<UIElement>();
	
	public void addElement(UIElement element) {
		elements.add(element);
	}
	public void removeElement(UIElement element) {
		elements.remove(element);
	}
	public void clearElements() {
		elements.clear();
	}
	
	public void update() {
		for(UIElement e : elements) {
			e.update();
		}
	}
	public void render(Graphics2D g) {
		for(UIElement e : elements) {
			e.render(g);
		}
	}
	
	public void input(KeyEvent e) {
		for(UIElement el : elements) {
			if(el instanceof Controller) {
				((Controller) el).input(e);
			}
		}
	}
	public void input(MouseEvent e, int mouseX, int mouseY) {
		for(UIElement el : elements) {
			if(el instanceof Controller) {
				((Controller) el).input(e,mouseX,mouseY);
			}
		}
	}
	public void input(MouseWheelEvent e) {
		for(UIElement el : elements) {
			if(el instanceof Controller) {
				((Controller) el).input(e);
			}
		}
	}
}
