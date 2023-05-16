package com.test.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferStrategy;

import com.test.main.entity.Player;
import com.test.main.entity.tiles.Box;
import com.test.main.entity.tiles.Ground;
import com.test.main.ui.MainWindow;
import com.test.main.util.Camera;
import com.test.main.util.EnvironmentVariables;
import com.test.main.util.handlers.EntityHandler;
import com.test.main.util.input.KeyInput;
import com.test.main.util.input.MouseInput;

public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private boolean running = false;
	private Thread thread;
	
	private EntityHandler eh;
	private Camera cam;
	
	public Main(String gameName) {
		cam = new Camera(this);
		EnvironmentVariables.CAM = cam;
		eh = new EntityHandler();
		eh.addEntity(new Ground(eh,0,980,1920,100));
		eh.addEntity(new Ground(eh,0,1080*3-100,1920*3,100));
		eh.addEntity(new Ground(eh,0,0,1920,100));
//		eh.addEntity(new Ground(eh,1820,0,100,1080));
		eh.addEntity(new Ground(eh,0,0,100,1080));
		eh.addEntity(new Ground(eh,300,300,300,80));
		eh.addEntity(new Player(eh,960,540));
		eh.addEntity(new Ground(eh,810,610,300,80));
		eh.addEntity(new Box(eh,400,200));

		new MainWindow(gameName,this);
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput(this));
		this.addMouseMotionListener(new MouseInput(this));
		this.addMouseWheelListener(new MouseInput(this));
		
		start();
	}

	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double ticks = 60.0;
		double ns = 1000000000 / ticks;
		double delta = 0;
		while (running) {
			ns = 1000000000 / ticks;
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				delta--;
				render();
			}
		}
		stop();
	}
	
	public synchronized void start() {	
		thread = new Thread(this);
		running = true;
		thread.start();
	}
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		eh.update();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(2);
			return;
		}
		Graphics2D g = (Graphics2D)bs.getDrawGraphics();
		g.scale(1280.0/1920.0,720.0/1080.0);
		
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, 1920, 1080);
		
//		g.shear(-800.0/600.0, 600.0/800.0);
//		g.translate(100, -300);
		
		cam.render(g);
		eh.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public void input(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE&&e.getID()==KeyEvent.KEY_PRESSED) {
			System.exit(0);
		}
		eh.input(e);
	}
	public void input(MouseEvent e, int mouseX, int mouseY) {
		eh.input(e,mouseX,mouseY);
	}
	public void input(MouseWheelEvent e) {
		eh.input(e);
	}

	public static void main(String[] args) {
		new Main("Comp Sci Game");
	}
}


