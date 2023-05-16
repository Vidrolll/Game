package com.test.main.util.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.test.main.Main;

public class MouseInput implements MouseListener, MouseMotionListener, MouseWheelListener {
	Main main;
	
	public MouseInput(Main main) {
		this.main = main;
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		main.input(e);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		main.input(e,e.getX(),e.getY());
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		main.input(e,e.getX(),e.getY());
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		main.input(e,e.getX(),e.getY());
	}
	@Override
	public void mousePressed(MouseEvent e) {
		main.input(e,e.getX(),e.getY());
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		main.input(e,e.getX(),e.getY());
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		main.input(e,e.getX(),e.getY());
	}
	@Override
	public void mouseExited(MouseEvent e) {
		main.input(e,e.getX(),e.getY());
	}
}
