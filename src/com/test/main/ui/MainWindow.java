package com.test.main.ui;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.test.main.Main;

public class MainWindow {
	JFrame frame;
	
	public MainWindow(String name, Main main) {
		frame = new JFrame(name);
		frame.setSize(new Dimension(1920,1080));
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(main);
		frame.setUndecorated(true);
		frame.setVisible(true);
	}
}
