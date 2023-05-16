package com.test.main.util.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.test.main.Main;

public class KeyInput implements KeyListener {
	Main main;
	
	public KeyInput(Main main) {
		this.main = main;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		main.input(e);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		main.input(e);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		main.input(e);
	}
}
