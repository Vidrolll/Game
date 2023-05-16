package com.test.main.effects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.test.main.util.base.Entity;

public class Trail {
	ArrayList<int[]> trail = new ArrayList<int[]>();
	
	int trailLength;
	
	Entity en;
	Color color;
	
	public Trail(Entity en, int trailLength, Color color) {
		this.en = en;
		this.trailLength = trailLength;
		this.color = color;
	}
	
	public void update() {
		int[] coords = new int[2];
		coords[0] = en.getX();
		coords[1] = en.getY();
		trail.add(coords);
		if(trail.size()==trailLength)
			trail.remove(0);
	}
	
	public void render(Graphics2D g) {
		g.setColor(color);
		Composite c = g.getComposite();
		for(int i = 0; i < trail.size(); i++) {
			float alpha = (float)i/trailLength;
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
			g.fillRect(trail.get(i)[0], trail.get(i)[1], (int)en.getBounds().getWidth(), (int)en.getBounds().getHeight());
		}
		g.setComposite(c);
	}
}
