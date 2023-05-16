package com.test.main.util.base;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public interface Controller {
	public void input(KeyEvent e);
	public void input(MouseEvent e, int mouseX, int mouseY);
	public void input(MouseWheelEvent e);
}
